package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Enter_ArenaPacket extends Packet {

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
