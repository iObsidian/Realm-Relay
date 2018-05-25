package rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.game.messaging.data.SlotObjectData;

public class InvDrop extends OutgoingMessage {

	public SlotObjectData slotObject;

	public InvDrop(int id, Consumer callback) {
		super(id, callback);
		slotObject = new SlotObjectData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		slotObject.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		slotObject.writeToOutput(out);
	}

}
