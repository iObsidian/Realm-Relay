package rotmg.net;

import com.hurlant.crypto.symmetric.ICipher;
import rotmg.AGameSprite;
import rotmg.messaging.GameServerConnectionConcrete;
import rotmg.net.impl.Message;
import rotmg.net.impl.MessageCenter;
import rotmg.parameters.Parameters;
import rotmg.util.AssetLoader;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * This class is a very loose implementation of WildShadow's SocketServer,
 * it is more closely related to The Force 2477's RealmClient
 */
public class SocketServer {

	public static SocketServer instance;
	public static int MESSAGE_LENGTH_SIZE_IN_BYTES = 4;
	public MessageCenter messages = MessageCenter.getInstance();
	public Socket socket = null;
	public long lastTimePacketReceived = 0;
	public long lastPingTime = 0;
	private int bufferIndex = 0;
	private boolean write = false;
	private boolean read = false;
	private long startTime = 0;
	private ICipher outgoingCipher; //Renamed from 'ICipher'.
	private ICipher incomingCipher;
	private String server; // host
	private int port;
	private DataInputStream inputStream = null;
	private DataOutputStream outputStream = null;
	private byte[] buffer = new byte[100000];
	private BlockingDeque<Message> packetQueue = new LinkedBlockingDeque<Message>();

	public static SocketServer getInstance() {
		if (instance == null) {
			instance = new SocketServer();
		}

		return instance;
	}

	public static void main(String[] args) {

		new AssetLoader().load();

		Server s = new Server().setAddress("54.67.119.179").setPort(Parameters.PORT);

		AGameSprite a = new AGameSprite();

		GameServerConnectionConcrete g = new GameServerConnectionConcrete(a, s, -1, false, 404, -1, new byte[0], null,
				false);

		g.connect();

	}

	public SocketServer setOutgoingCipher(ICipher param1) {
		this.outgoingCipher = param1;
		return this;
	}

	public SocketServer setIncomingCipher(ICipher param1) {
		this.incomingCipher = param1;
		return this;
	}

	public void connect(String server, int port) {
		bufferIndex = 0;
		buffer = new byte[100000];
		packetQueue.clear();

		this.server = server;
		this.port = port;

		System.out.println("Connecting to " + server + ":" + port + ".");

		try {
			socket = new Socket(server, port);
			socket.setReuseAddress(true);

			inputStream = new DataInputStream(socket.getInputStream());
			outputStream = new DataOutputStream(socket.getOutputStream());
			startTime = System.currentTimeMillis();
			startThreadedListener();
			startThreadedWriter();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		///

	}

	private void startThreadedListener() {

		read = true;
		new Thread("threadedListenner") {

			@Override
			public void run() {
				Socket sock = socket;
				try {
					while (sock != null && sock.isConnected() && read && !sock.isClosed()) {
						Thread.sleep(20);
						int bytesRead = inputStream.read(buffer, bufferIndex, buffer.length - bufferIndex);
						if (bytesRead == -1) {
							if (packetQueue != null && packetQueue.size() > 0) {
								packetQueue.clear();
								System.err.println("EOF");
							}
							break;
						} else if (bytesRead > 0) {

							lastTimePacketReceived = System.currentTimeMillis();
							bufferIndex += bytesRead;
							while (bufferIndex >= 5) {
								int packetLength = ((ByteBuffer) ByteBuffer.allocate(4).put(buffer[0]).put(buffer[1])
										.put(buffer[2]).put(buffer[3]).rewind()).getInt();
								if (buffer.length < packetLength) {
									buffer = Arrays.copyOf(buffer, packetLength);
								}
								if (bufferIndex < packetLength) {
									break;
								}
								byte packetId = buffer[4];
								byte[] packetBytes = new byte[packetLength - 5];
								System.arraycopy(buffer, 5, packetBytes, 0, packetLength - 5);
								if (bufferIndex > packetLength) {
									System.arraycopy(buffer, packetLength, buffer, 0, bufferIndex - packetLength);
								}
								bufferIndex -= packetLength;
								incomingCipher.cipher(packetBytes);

								Message m = messages.require(packetId);

								if (m == null) {
									System.err.println("FATAL: Null packet... Id : " + packetId);
								} else {
									//System.out.println("Received a " + m.getClass().getSimpleName() + " packet.");
									m.parseFromInput(new DataInputStream(new ByteArrayInputStream(packetBytes)));
									m.consume();
								}


							}
						}

						int lastPacket = (int) ((System.currentTimeMillis() - lastTimePacketReceived));

						/* if (lastPacket > 2000 && !packetQueue.isEmpty()) {
						    reconnect(
						        "Timeout, nothing received in " + lastPacket + "ms.");
						}**/
					}

				} catch (Exception ex) {

					ex.printStackTrace();

				}
			}
		}.start();
	}

	private void startThreadedWriter() {
		write = true;
		new Thread("threadedWriter") {
			@Override
			public void run() {
				while (socket != null && socket.isConnected() && write) {
					long start = System.currentTimeMillis();
					while (!packetQueue.isEmpty()) {
						if (packetQueue != null && packetQueue.size() > 0) {
							Message p = null;
							p = packetQueue.peekLast();
							if (p != null) {
								sendMessage(p);
								packetQueue.removeLast();
							} else
								break;
						}
					}
					int time = (int) (System.currentTimeMillis() - start);
					try {
						Thread.sleep(20 - (time > 20 ? 0 : time));
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
				}

			}
		}.start();
	}

	/**
	 * Send directly the message
	 */
	public void sendMessage(Message packet) {
		try {
			if (write) {
				byte[] packetBytes = packet.getBytes();
				outgoingCipher.cipher(packetBytes);
				int packetLength = packetBytes.length + 5;
				outputStream.writeInt(packetLength);
				outputStream.writeByte(packet.id);
				outputStream.write(packetBytes);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
