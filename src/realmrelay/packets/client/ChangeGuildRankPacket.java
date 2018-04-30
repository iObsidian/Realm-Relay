package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ChangeGuildRankPacket extends Packet {

	private String name;
	private int guildRank;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
		guildRank = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(guildRank);
	}

}
