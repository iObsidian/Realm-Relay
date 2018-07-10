package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Reconnect extends IncomingMessage {

	public String name;
	public String host;
	public int port;
	public int gameId;
	public int keyTime;
	public byte[] key = new byte[0];
	public boolean isFromArena;
	public String stats;

	public Reconnect(int id, MessageConsumer callback) {
		super(id, callback);
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.host = in.readUTF();
		this.stats = in.readUTF();
		this.port = in.readInt();
		this.gameId = in.readInt();
		this.keyTime = in.readInt();
		this.isFromArena = in.readBoolean();
		this.key = new byte[in.readShort()];
		in.readFully(key);
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.name);
		out.writeUTF(this.host);
		out.writeUTF(this.stats);
		out.writeInt(this.port);
		out.writeInt(this.gameId);
		out.writeInt(this.keyTime);
		out.writeBoolean(this.isFromArena);
		out.writeShort(this.key.length);
		out.write(this.key);
	}
}
