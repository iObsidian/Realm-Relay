package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class NewAbilityMessagePacket extends Packet {

	private int type;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		type = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
	}

}
