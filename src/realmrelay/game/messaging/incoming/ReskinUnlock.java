package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ReskinUnlock extends Packet {

	private int skinID;

	public void parseFromInput(DataInput in) throws IOException {
		this.skinID = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.skinID);
	}
}
