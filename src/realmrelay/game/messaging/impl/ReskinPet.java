package realmrelay.game.messaging.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.data.SlotObjectData;
import realmrelay.game.messaging.outgoing.OutgoingMessage;

public class ReskinPet extends OutgoingMessage {

	public ReskinPet(int param1, Consumer param2) {
		super(param1, param2);
		this.item = new SlotObjectData();
	}

	public int petInstanceId;
	public int pickedNewPetType;
	public SlotObjectData item;

	@Override
	public void writeToOutput(DataOutput param1) throws IOException {
		param1.writeInt(this.petInstanceId);
		param1.writeInt(this.pickedNewPetType);
		this.item.writeToOutput(param1);
	}
}
