package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Create extends Packet {

	public int classType;
	public int skinType;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		classType = in.readUnsignedShort();
		skinType = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(classType);
		out.writeShort(skinType);
	}

}
