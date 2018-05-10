package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class SetCondition extends Packet {

	private int conditionEffect;
	private float conditionDuration;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		conditionEffect = in.readUnsignedByte();
		conditionDuration = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(conditionEffect);
		out.writeFloat(conditionDuration);
	}

}
