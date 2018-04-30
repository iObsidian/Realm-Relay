package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class DeathPacket extends Packet {

	private String accountId;
	private int charId;
	public String killedBy;
	public int zombieType;
	public int zombieId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		accountId = in.readUTF();
		charId = in.readInt();
		killedBy = in.readUTF();
		zombieType = in.readInt();
		zombieId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(accountId);
		out.writeInt(charId);
		out.writeUTF(killedBy);
		out.writeInt(zombieType);
		out.writeInt(zombieId);
	}

}
