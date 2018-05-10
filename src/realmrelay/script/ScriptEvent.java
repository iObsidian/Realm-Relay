package realmrelay.script;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

import realmrelay.ROTMGRelay;
import realmrelay.User;
import realmrelay.game.Old_ObjectLibrary;
import realmrelay.game.messaging.data.GroundTileData;
import realmrelay.game.messaging.data.MoveRecord;
import realmrelay.game.messaging.data.ObjectStatusData;
import realmrelay.game.messaging.data.SlotObjectData;
import realmrelay.game.messaging.data.StatData;
import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.game.objects.GameObject;
import realmrelay.packets.Packet;
import realmrelay.packets.data.unused.BitmapData;
import realmrelay.packets.data.unused.GroundData;
import realmrelay.packets.data.unused.Item;
import realmrelay.packets.data.unused.ItemData;

public class ScriptEvent {

	protected final User user;

	/**
	 * Creates event to be handled by user scripts
	 * @param user
	 */
	public ScriptEvent(User user) {
		this.user = user;
	}

	public boolean connect(int gameId) {
		if (this.user.remoteSocket != null) {
			return false;
		}
		final InetSocketAddress socketAddress = ROTMGRelay.instance.getSocketAddress(gameId);
		new Thread(new Runnable() {

			@Override
			public void run() {
				Socket remoteSocket;
				if (ROTMGRelay.instance.bUseProxy) {
					SocketAddress proxyAddress = new InetSocketAddress(ROTMGRelay.instance.proxyHost,
							ROTMGRelay.instance.proxyPort);
					Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
					remoteSocket = new Socket(proxy);
				} else {
					remoteSocket = new Socket();
				}
				try {
					SocketAddress remoteAddress;
					if (InetAddress.getByName(socketAddress.getHostString()).isLoopbackAddress()) {
						remoteAddress = new InetSocketAddress(ROTMGRelay.instance.remoteHost,
								socketAddress.getPort() == -1 ? ROTMGRelay.instance.remotePort
										: socketAddress.getPort());
					} else {
						remoteAddress = new InetSocketAddress(socketAddress.getHostString(),
								socketAddress.getPort() == -1 ? ROTMGRelay.instance.remotePort
										: socketAddress.getPort());
					}
					remoteSocket.connect(remoteAddress, 10000);
					user.remoteNoDataTime = System.currentTimeMillis();
					user.remoteSocket = remoteSocket;
					user.scriptManager.trigger("onConnect");
				} catch (IOException e) {
					user.scriptManager.trigger("onConnectFail");
				}
			}

		}).start();
		return true;
	}

	/**public BitmapData createBitmapData() {
		return new BitmapData();
	}*/

	public Item createItem() {
		return new Item();
	}

	public WorldPosData createWorldPosData() {
		return new WorldPosData();
	}

	public MoveRecord createWorldPosDataRecord() {
		return new MoveRecord();
	}

	public GameObject createEntity() {
		return new GameObject();
	}

	/**
	 * Creates new packet from packet id
	 * @param id
	 * @return
	 */
	public Packet createPacket(byte id) throws Exception {
		return Packet.create(id);
	}

	public SlotObjectData createSlotObject() {
		return new SlotObjectData();
	}

	public StatData createStatData() {
		return new StatData();
	}

	public ObjectStatusData createStatus() {
		return new ObjectStatusData();
	}

	public GroundTileData createTile() {
		return new GroundTileData();
	}

	/**
	 * Disconnects from the server
	 */
	public void disconnect() {
		this.user.disconnect();
	}

	/**
	 * echo message
	 * @param message
	 */
	public void echo(String message) {
		ROTMGRelay.echo(message);
	}

	public GroundData findGround(Number searchterm) {
		return Old_ObjectLibrary.tileMap.get(searchterm);
	}

	public ItemData findItem(Object searchterm) {
		return Old_ObjectLibrary.itemMap.get(searchterm);
	}

	public GameObject findObject(Object searchterm) {
		return Old_ObjectLibrary.objectMap.get(searchterm);
	}

	public byte findPacketId(String name) {
		Integer id = (Integer) Old_ObjectLibrary.packetMap.get(name.toUpperCase());
		if (id == null) {
			return -1;
		}
		return id.byteValue();
	}

	public Object getGlobal(String var) {
		return ROTMGRelay.instance.getGlobal(var);
	}

	public String getRemoteHost() {
		return this.user.remoteSocket.getInetAddress().getHostName();
	}

	public int getRemotePort() {
		return this.user.remoteSocket.getPort();
	}

	/**
	 * Returns true if connected to remote host
	 * @return
	 */
	public boolean isConnected() {
		return this.user.remoteSocket != null;
	}

	public void kickUser() {
		this.user.kick();
	}

	/**
	 * Fires eventMethod after ticks passed; passes arguments
	 * @param ticks
	 * @param eventMethod
	 * @param objects
	 */
	public void scheduleEvent(double seconds, String eventMethod, Object... objects) {
		this.user.scriptManager.scheduleEvent(seconds, eventMethod, objects);
	}

	/**
	 * Sends packet to client
	 * @param packet
	 * @throws IOException
	 */
	public void sendToClient(Packet packet) throws IOException {
		byte[] packetBytes = packet.getBytes();
		this.user.localSendRC4.cipher(packetBytes);
		byte packetId = packet.id();
		int packetLength = packetBytes.length + 5;
		DataOutputStream out = new DataOutputStream(user.localSocket.getOutputStream());
		out.writeInt(packetLength);
		out.writeByte(packetId);
		out.write(packetBytes);
	}

	/**
	 * Sends packet to server
	 * @param packet
	 * @throws IOException
	 */
	public void sendToServer(Packet packet) throws IOException {
		byte[] packetBytes = packet.getBytes();
		this.user.remoteSendRC4.cipher(packetBytes);
		byte packetId = packet.id();
		int packetLength = packetBytes.length + 5;
		DataOutputStream out = new DataOutputStream(user.remoteSocket.getOutputStream());
		out.writeInt(packetLength);
		out.writeByte(packetId);
		out.write(packetBytes);
	}

	public void setGameIdSocketAddress(int gameId, String host, int port) {
		ROTMGRelay.instance.setSocketAddress(gameId, host, port);
	}

	public void setGlobal(String var, Object value) {
		ROTMGRelay.instance.setGlobal(var, value);
	}

}
