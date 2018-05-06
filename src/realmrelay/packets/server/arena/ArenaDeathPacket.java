package realmrelay.packets.server.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;



public class ArenaDeathPacket extends Packet {

	private int restartPrice;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		restartPrice = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(restartPrice);
	}

}
