package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ArenaNextWavePacket extends Packet {

	private int typeId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		typeId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(typeId);
	}

}
