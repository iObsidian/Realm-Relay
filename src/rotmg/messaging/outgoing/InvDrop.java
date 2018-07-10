package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.SlotObjectData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class InvDrop extends OutgoingMessage {

	public SlotObjectData slotObject;

	public InvDrop(int id, MessageConsumer callback) {
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
