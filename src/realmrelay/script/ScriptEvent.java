package realmrelay.script;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

import realmrelay.GETXmlParse;
import realmrelay.ROTMGRelay;
import realmrelay.User;
import realmrelay.data.BitmapData;
import realmrelay.data.Entity;
import realmrelay.data.GroundData;
import realmrelay.data.Item;
import realmrelay.data.ItemData;
import realmrelay.data.Location;
import realmrelay.data.LocationRecord;
import realmrelay.data.ObjectData;
import realmrelay.data.SlotObject;
import realmrelay.data.StatData;
import realmrelay.data.Status;
import realmrelay.data.Tile;
import realmrelay.packets.Packet;


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
					SocketAddress proxyAddress = new InetSocketAddress(ROTMGRelay.instance.proxyHost, ROTMGRelay.instance.proxyPort);
					Proxy proxy = new Proxy(Proxy.Type.SOCKS, proxyAddress);
					remoteSocket = new Socket(proxy);
				} else {
					remoteSocket = new Socket();
				}
				try {
					SocketAddress remoteAddress;
					if (InetAddress.getByName(socketAddress.getHostString()).isLoopbackAddress()) {
						remoteAddress = new InetSocketAddress(ROTMGRelay.instance.remoteHost, socketAddress.getPort() == -1 ? ROTMGRelay.instance.remotePort : socketAddress.getPort());
					} else {
						remoteAddress = new InetSocketAddress(socketAddress.getHostString(), socketAddress.getPort() == -1 ? ROTMGRelay.instance.remotePort : socketAddress.getPort());
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
	
	public BitmapData createBitmapData() {
		return new BitmapData();
	}
	
	public Item createItem() {
		return new Item();
	}
	
	public Location createLocation() {
		return new Location();
	}
	
	public LocationRecord createLocationRecord() {
		return new LocationRecord();
	}
	
	public Entity createEntity() {
		return new Entity();
	}
	
	/**
	 * Creates new packet from packet id
	 * @param id
	 * @return
	 */
	public Packet createPacket(byte id) throws Exception {
		return Packet.create(id);
	}
	
	public SlotObject createSlotObject() {
		return new SlotObject();
	}
	
	public StatData createStatData() {
		return new StatData();
	}
	
	public Status createStatus() {
		return new Status();
	}
	
	public Tile createTile() {
		return new Tile();
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
	
	public GroundData findGround(Object searchterm) {
		if (searchterm instanceof Number) {
			int type = (int)((double) searchterm);
			return GETXmlParse.tileMap2.get(type);
		}
		return GETXmlParse.tileMap.get(searchterm.toString().toUpperCase());
	}
	
	public ItemData findItem(Object searchterm) {
		if (searchterm instanceof Number) {
			int type = (int)((double) searchterm);
			return GETXmlParse.itemMap2.get(type);
		}
		return GETXmlParse.itemMap.get(searchterm.toString().toUpperCase());
	}
	
	public ObjectData findObject(Object searchterm) {
		if (searchterm instanceof Number) {
			int type = (int)((double) searchterm);
			return GETXmlParse.objectMap2.get(type);
		}
		return GETXmlParse.objectMap.get(searchterm.toString().toUpperCase());
	}
	
	public byte findPacketId(String name) {
		Integer id = (Integer) GETXmlParse.packetMap.get(name.toUpperCase());
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
