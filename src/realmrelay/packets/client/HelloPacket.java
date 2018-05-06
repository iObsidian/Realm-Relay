package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class HelloPacket extends Packet {

	public String buildVersion = "";
	public int gameId = 0;
	public String GUID = "";
	public int random1 = 0;
	public String password = "";
	public int random2 = 0;
	public String secret = "";
	public int keyTime;
	public byte[] key = new byte[0];
	public byte[] mapJSON = new byte[0];
	public String entrytag = "";
	public String gameNet = "";
	public String gameNetUserId = "";
	public String playPlatform = "";
	public String platformToken = "";
	public String userToken = "";

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.buildVersion = in.readUTF();
		this.gameId = in.readInt();
		this.GUID = in.readUTF();
		this.random1 = in.readInt();
		this.password = in.readUTF();
		this.random2 = in.readInt();
		this.secret = in.readUTF();
		this.keyTime = in.readInt();
		this.key = new byte[in.readShort()];
		in.readFully(this.key);
		this.mapJSON = new byte[in.readShort()];
		in.readFully(this.mapJSON);
		this.entrytag = in.readUTF();
		this.gameNet = in.readUTF();
		this.gameNetUserId = in.readUTF();
		this.playPlatform = in.readUTF();
		this.platformToken = in.readUTF();
		this.userToken = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.buildVersion);
		out.writeInt(this.gameId);
		out.writeUTF(this.GUID);
		out.writeInt(random1);
		out.writeUTF(this.password);
		out.writeInt(random2);
		out.writeUTF(this.secret);
		out.writeInt(this.keyTime);
		out.writeShort(this.key.length);
		out.write(this.key);
		out.writeInt(this.mapJSON.length);
		out.write(this.mapJSON);
		out.writeUTF(this.entrytag);
		out.writeUTF(this.gameNet);
		out.writeUTF(this.gameNetUserId);
		out.writeUTF(this.playPlatform);
		out.writeUTF(this.platformToken);
		out.writeUTF(this.userToken);
	}

}
