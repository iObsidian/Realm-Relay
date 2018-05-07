package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class InvitedToGuildPacket extends Packet {

	public String name;
	public String guildName;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.guildName = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(guildName);
	}

}
