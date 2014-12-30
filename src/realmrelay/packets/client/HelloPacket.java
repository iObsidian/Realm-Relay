package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import realmrelay.packets.Packet;

/* Thanks to me, Maat, Faintmako, Gratin and pieoewieoe, for their contribution. */

public class HelloPacket extends Packet {
	
	public int Random1 = 0;
	public int Random2 = 0;
	public String buildVersion = "";
	//public int obf0;
	public int gameId = 0;
	public String guid = "";
	public String password = "";
	public String secret = "";
	public int keyTime;
	public byte[] key = new byte[0];
	public byte[] obf1 = new byte[0];
	public String obf2 = "";
	public String obf3 = "";
	public String obf4 = "";
	public String obf5 = "";
	public String obf6 = "";
	public int obf7 = 0;
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.buildVersion = in.readUTF();
		//this.obf0 = in.readInt();
		this.gameId = in.readInt();
		this.guid = in.readUTF();
		this.Random1 = in.readInt();
		this.password = in.readUTF();
		this.Random2 = in.readInt();
		this.secret = in.readUTF();
		this.keyTime = in.readInt();
		this.key = new byte[in.readShort()];
		in.readFully(this.key);
		this.obf1 = new byte[in.readInt()];
		in.readFully(this.obf1);
		this.obf2 = in.readUTF();
		this.obf3 = in.readUTF();
		this.obf4 = in.readUTF();
		this.obf5 = in.readUTF();
		this.obf6 = in.readUTF();
		//this.obf7 = in.readInt();
	}
	
	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.buildVersion);
		//out.writeInt(this.obf0);
		out.writeInt(this.gameId);
		out.writeUTF(this.guid);
		out.writeInt(Random1);
		out.writeUTF(this.password);
		out.writeInt(Random2);
		out.writeUTF(this.secret);
		out.writeInt(this.keyTime);
		out.writeShort(this.key.length);
		out.write(this.key);
		out.writeInt(this.obf1.length);
		out.write(this.obf1);
		out.writeUTF(this.obf2);
		out.writeUTF(this.obf3);
		out.writeUTF(this.obf4);
		out.writeUTF(this.obf5);
		out.writeUTF(this.obf6);
		//out.writeInt(this.obf7);
	}
	
}
