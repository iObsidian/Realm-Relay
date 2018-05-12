package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class SetCondition extends OutgoingMessage {

	public int conditionEffect;
	public float conditionDuration;

	public SetCondition(int id, Consumer callback) {
		super(id, callback);
	}

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
