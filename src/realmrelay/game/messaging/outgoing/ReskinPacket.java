package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ReskinPacket extends Packet {

	private int skinID;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		skinID = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(skinID);
	}

}
