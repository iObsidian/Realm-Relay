package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class PlaySound extends Packet {

	private int ownerId;
	private int soundId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		ownerId = in.readInt();
		soundId = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(ownerId);
		out.writeByte(soundId);
	}

}
