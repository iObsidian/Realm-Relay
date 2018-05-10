package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EvolvedPetMessagePacket extends Packet {
	int petID;
	int initialSkin;
	int finalSkin;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petID = in.readInt();
		initialSkin = in.readInt();
		finalSkin = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petID);
		out.writeInt(initialSkin);
		out.writeInt(finalSkin);
	}

}
