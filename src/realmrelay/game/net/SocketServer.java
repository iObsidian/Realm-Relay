package realmrelay.game.net;

import realmrelay.crypto.ICipher;
import realmrelay.game._as3.SCry;
import realmrelay.game.api.MessageProvider;
import realmrelay.game.messaging.GameServerConnectionConcrete;
import realmrelay.game.messaging.outgoing.Hello;
import realmrelay.game.net.impl.Message;
import realmrelay.game.parameters.Parameters;

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

	public static SocketServer getInstance() {
		if (instance == null) {
			instance = new SocketServer();
		}

		return instance;
	}

	public static int MESSAGE_LENGTH_SIZE_IN_BYTES = 4;

	public MessageProvider messages = MessageCenter.getInstance();

	public Socket socket = null;


	private int bufferIndex = 0;


	private boolean write = false;
	private boolean read = false;

	private long startTime = 0;
	public long lastTimePacketReceived = 0;
	public long lastPingTime = 0;


	private ICipher outgoingCipher; //Renamed from 'ICipher'.

	private ICipher incomingCipher;

	private String server; // host

	private int port;


	private DataInputStream inputStream = null;
	private DataOutputStream outputStream = null;


	private byte[] buffer = new byte[100000];
	BlockingDeque<Message> packetQueue = new LinkedBlockingDeque<Message>();


	public static void main(String[] args) {

		
		SocketServer s = new SocketServer();

		s.setOutgoingCipher(new ICipher("6a39570cc9de4ec71d64821894"));
		s.setIncomingCipher(new ICipher("c79332b197f92ba85ed281a023"));

		s.connect("54.153.32.11", Parameters.PORT);

	}


	public SocketServer setOutgoingCipher(ICipher param1) {
		this.outgoingCipher = param1;
		return this;
	}

	public SocketServer setIncomingCipher(ICipher param1) {
		this.incomingCipher = param1;
		return this;
	}


	public void connect(String server, int host) {

		bufferIndex = 0;
		buffer = new byte[100000];
		packetQueue.clear();

		this.server = server;
		this.port = port;

		Hello h = (Hello) messages.require(GameServerConnectionConcrete.HELLO);

		h.buildVersion = Parameters.BUILD_VERSION + "." + Parameters.MINOR_VERSION;
		h.gameId = -2; // nexus
		h.guid = SCry.encrypt("rotmgiobsidian@gmail.com");
		h.random1 = 0;
		h.password = SCry.encrypt("JtiTdzTP");
		h.random2 = 0;
		h.secret = "";
		h.keyTime = -1;
		h.key = new byte[0];
		h.mapJSON = new byte[0];
		h.entrytag = "";
		h.gameNet = "";
		h.gameNetUserId = "";
		h.playPlatform = "";
		h.platformToken = "";
		h.userToken = "";

		packetQueue.addFirst(h);

		start();


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
								m.parseFromInput(new DataInputStream(new ByteArrayInputStream(packetBytes)));
								handlePacket(m);

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

	protected void handlePacket(Message packet) {
		System.out.println("New packet : " + packet);
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


	protected int currentTime() {
		return (int) (System.currentTimeMillis() - startTime);
	}


	public void start() {
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
	}


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
