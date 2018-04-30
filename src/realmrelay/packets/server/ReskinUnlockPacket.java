package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ReskinUnlockPacket extends Packet {

	private int skinId;

	public void parseFromInput(DataInput in) throws IOException {
		this.skinId = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.skinId);
	}
}
