package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.SlotObjectData;
import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UseItem extends OutgoingMessage {

	public int time;
	public SlotObjectData slotObject;
	public WorldPosData itemUsePos;
	public int useType;

	public UseItem(int id, MessageConsumer callback) {
		super(id, callback);
		slotObject = new SlotObjectData();
		itemUsePos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		slotObject.parseFromInput(in);
		itemUsePos.parseFromInput(in);
		useType = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		slotObject.writeToOutput(out);
		itemUsePos.writeToOutput(out);
		out.writeByte(useType);
	}

}
