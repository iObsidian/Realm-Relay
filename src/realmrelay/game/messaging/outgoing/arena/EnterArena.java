package realmrelay.game.messaging.outgoing.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EnterArena extends Packet {

	private int currency;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		currency = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(currency);
	}

}
