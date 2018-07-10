package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.SlotObjectData;
import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class InvSwap extends OutgoingMessage {

	public int time;
	public WorldPosData position;
	public SlotObjectData slotObject1;
	public SlotObjectData slotObject2;

	public InvSwap(int id, MessageConsumer callback) {
		super(id, callback);
		position = new WorldPosData();
		slotObject1 = new SlotObjectData();
		slotObject2 = new SlotObjectData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		position.parseFromInput(in);
		slotObject1.parseFromInput(in);
		slotObject2.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		position.writeToOutput(out);
		slotObject1.writeToOutput(out);
		slotObject2.writeToOutput(out);
	}

}
