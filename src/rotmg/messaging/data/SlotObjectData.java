package rotmg.messaging.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class SlotObjectData implements IData {

	public int objectId; // the name of what holds it
	public int slotId; // the slot of the item
	public int objectType; // the ID of the item

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectId = in.readInt();
		slotId = in.readUnsignedByte();
		objectType = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(objectId);
		out.writeByte(slotId);
		out.writeInt(objectType);
	}

}
