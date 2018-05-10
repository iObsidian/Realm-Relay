package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class UpdateAck extends Packet {

	/**
	 * The client maps this to a Message (empty Packet)
	 * This class is meant as a replacement of an empty packet
	 */

	@Override
	public void parseFromInput(DataInput in) throws IOException {

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

	}

}
