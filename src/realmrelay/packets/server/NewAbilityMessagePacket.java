package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class NewAbilityMessagePacket extends Packet {

	private int abilityType;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		abilityType = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(abilityType);
	}

}
