package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ReconnectPacket extends Packet {

	private String name;
	public String host;
	public String stats;
	public int port;
	public int gameId;
	public int keyTime;
	private boolean isFromArena;
	public byte[] key = new byte[0];

	/**
	 * this.name_ = param1.readUTF();
         this.host_ = param1.readUTF();
         this.stats_ = param1.readUTF();
         this.port_ = param1.readInt();
         this.gameId_ = param1.readInt();
         this.keyTime_ = param1.readInt();
         this.isFromArena_ = param1.readBoolean();
         var _loc2_:int = param1.readShort();
         this.key_.length = 0;
         param1.readBytes(this.key_,0,_loc2_);
	 */
	
	
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
