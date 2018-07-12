package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class Hello extends OutgoingMessage {

	public String buildVersion = "";
	public int gameId = 0;
	public String guid = "";
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

	public Hello(int id, MessageConsumer callback) {
		super(id, callback);

		this.buildVersion = "";
		this.guid = "";
		this.password = "";
		this.secret = "";
		this.key = new byte[0];
		this.mapJSON = new byte[0];
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.buildVersion);
		out.writeInt(this.gameId);
		out.writeUTF(this.guid);
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

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.buildVersion = in.readUTF();
		this.gameId = in.readInt();
		this.guid = in.readUTF();
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
	public String toString() {
		return "Hello{" +
				"buildVersion='" + buildVersion + '\'' +
				", gameId=" + gameId +
				", guid='" + guid + '\'' +
				", random1=" + random1 +
				", password='" + password + '\'' +
				", random2=" + random2 +
				", secret='" + secret + '\'' +
				", keyTime=" + keyTime +
				", key=" + Arrays.toString(key) +
				", mapJSON=" + Arrays.toString(mapJSON) +
				", entrytag='" + entrytag + '\'' +
				", gameNet='" + gameNet + '\'' +
				", gameNetUserId='" + gameNetUserId + '\'' +
				", playPlatform='" + playPlatform + '\'' +
				", platformToken='" + platformToken + '\'' +
				", userToken='" + userToken + '\'' +
				'}';
	}
}
