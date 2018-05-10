package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class JoinGuildPacket extends Packet {

	private String guildName;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		guildName = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(guildName);
	}

}
