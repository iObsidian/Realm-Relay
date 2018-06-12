package rotmg.messaging.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.messaging.data.SlotObjectData;
import rotmg.messaging.outgoing.OutgoingMessage;

public class ReskinPet extends OutgoingMessage {

	public int petInstanceId;
	public int pickedNewPetType;
	public SlotObjectData item;
	public ReskinPet(int param1, Consumer param2) {
		super(param1, param2);
		this.item = new SlotObjectData();
	}

	@Override
	public void writeToOutput(DataOutput in) throws IOException {
		in.writeInt(this.petInstanceId);
		in.writeInt(this.pickedNewPetType);
		item.writeToOutput(in);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.petInstanceId = in.readInt();
		this.pickedNewPetType = in.readInt();
		item.parseFromInput(in);
	}
}
