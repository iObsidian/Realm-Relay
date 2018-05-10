package realmrelay.game.messaging.incoming.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ArenaDeathPacket extends Packet {

	private int cost;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		cost = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(cost);
	}

}
