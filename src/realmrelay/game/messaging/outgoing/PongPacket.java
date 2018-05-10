package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class PongPacket extends Packet {

	public int serial;
	public int time;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		serial = in.readInt();
		time = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(serial);
		out.writeInt(time);
	}

}