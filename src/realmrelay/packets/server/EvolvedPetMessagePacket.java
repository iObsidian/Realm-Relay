package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EvolvedPetMessagePacket extends Packet {

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		int petID = in.readInt();
		int initialSkin = in.readInt();
		int finalSkin = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
	}

}
