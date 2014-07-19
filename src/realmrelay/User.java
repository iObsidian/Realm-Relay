package realmrelay;

import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;

import realmrelay.crypto.RC4;
import realmrelay.packets.Packet;
import realmrelay.script.PacketScriptEvent;
import realmrelay.script.ScriptManager;


public class User {
	
	private static final int bufferLength = 65536 * 10;
	
	public final byte[] localBuffer = new byte[bufferLength];
	public int localBufferIndex = 0;
	public final RC4 localRecvRC4;
	public final RC4 localSendRC4;
	public byte[] remoteBuffer = new byte[bufferLength];
	public int remoteBufferIndex = 0;
	public final RC4 remoteRecvRC4;
	public final RC4 remoteSendRC4;
	public final Socket localSocket;
	public Socket remoteSocket = null;
	public final ScriptManager scriptManager = new ScriptManager(this);
	public long localNoDataTime = System.currentTimeMillis();
	public long remoteNoDataTime = System.currentTimeMillis();
	
	public User(Socket localSocket) throws Exception {
		if (localSocket == null) {
			throw new NullPointerException();
		}
		this.localSocket = localSocket;
		this.localRecvRC4 = new RC4(ROTMGRelay.instance.key0);
		this.localSendRC4 = new RC4(ROTMGRelay.instance.key1);
		this.remoteRecvRC4 = new RC4(ROTMGRelay.instance.key1);
		this.remoteSendRC4 = new RC4(ROTMGRelay.instance.key0);
	}
	
	public void disconnect() {
		if (this.remoteSocket != null) {
			try {
				this.remoteSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			this.remoteSocket = null;
			this.scriptManager.trigger("onDisconnect");
		}
	}
	
	public void kick() {
		this.disconnect();
		try {
			this.localSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void process() {
		try {
			this.scriptManager.fireExpiredEvents();
			if (this.remoteSocket != null) {
				try {
					InputStream in = this.remoteSocket.getInputStream();
					if (in.available() > 0) {
						int bytesRead = this.remoteSocket.getInputStream().read(this.remoteBuffer, this.remoteBufferIndex, this.remoteBuffer.length - this.remoteBufferIndex);
						if (bytesRead == -1) {
							throw new SocketException("end of stream");
						} else if (bytesRead > 0) {
							this.remoteBufferIndex += bytesRead;
							while (this.remoteBufferIndex >= 5) {
								int packetLength = ((ByteBuffer) ByteBuffer.allocate(4).put(this.remoteBuffer[0]).put(this.remoteBuffer[1]).put(this.remoteBuffer[2]).put(this.remoteBuffer[3]).rewind()).getInt();
								if (this.remoteBufferIndex < packetLength) {
									break;
								}
								byte packetId = this.remoteBuffer[4];
								byte[] packetBytes = new byte[packetLength - 5];
								System.arraycopy(this.remoteBuffer, 5, packetBytes, 0, packetLength - 5);
								if (this.remoteBufferIndex > packetLength) {
									System.arraycopy(this.remoteBuffer, packetLength, this.remoteBuffer, 0, this.remoteBufferIndex - packetLength);
								}
								this.remoteBufferIndex -= packetLength;
								this.remoteRecvRC4.cipher(packetBytes);
								Packet packet = Packet.create(packetId, packetBytes);
								PacketScriptEvent event = this.scriptManager.serverPacketEvent(packet);
								if (!event.isCancelled()) {
									event.sendToClient(packet);
								}
							}
						}
						this.remoteNoDataTime = System.currentTimeMillis();
					} else if (System.currentTimeMillis() - this.remoteNoDataTime >= 10000) {
						throw new SocketException("remote data timeout");
					}
				} catch (Exception e) {
					if (!(e instanceof SocketException)) {
						e.printStackTrace();
					}
					this.disconnect();
				}
			}
			InputStream in = this.localSocket.getInputStream();
			if (in.available() > 0) {
				int bytesRead = in.read(this.localBuffer, this.localBufferIndex, this.localBuffer.length - this.localBufferIndex);
				if (bytesRead == -1) {
					throw new SocketException("eof");
				} else if (bytesRead > 0) {
					this.localBufferIndex += bytesRead;
					while (this.localBufferIndex >= 5) {
						int packetLength = ((ByteBuffer) ByteBuffer.allocate(4).put(this.localBuffer[0]).put(this.localBuffer[1]).put(this.localBuffer[2]).put(this.localBuffer[3]).rewind()).getInt();
						if (this.localBufferIndex < packetLength) {
							break;
						}
						byte packetId = this.localBuffer[4];
						byte[] packetBytes = new byte[packetLength - 5];
						System.arraycopy(this.localBuffer, 5, packetBytes, 0, packetLength - 5);
						if (this.localBufferIndex > packetLength) {
							System.arraycopy(this.localBuffer, packetLength, this.localBuffer, 0, this.localBufferIndex - packetLength);
						}
						this.localBufferIndex -= packetLength;
						this.localRecvRC4.cipher(packetBytes);
						Packet packet = Packet.create(packetId, packetBytes);
						PacketScriptEvent event = this.scriptManager.clientPacketEvent(packet);
						if (!event.isCancelled() && event.isConnected()) {
							event.sendToServer(packet);
						}
					}
				}
				this.localNoDataTime = System.currentTimeMillis();
			} else if (System.currentTimeMillis() - this.localNoDataTime >= 10000) {
				throw new SocketException("local data timeout");
			}
		} catch (Exception e) {
			if (!(e instanceof SocketException)) {
				e.printStackTrace();
			}
			this.kick();
			ROTMGRelay.echo("Disconnected " + this.localSocket);
		}
	}

}
