package realmrelay.game.messaging.impl;

import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.data.SlotObjectData;
import realmrelay.game.messaging.outgoing.OutgoingMessage;

public class PetUpgradeRequest extends OutgoingMessage {

	public static final int GOLD_PAYMENT_TYPE = 0;
	public static final int FAME_PAYMENT_TYPE = 1;

	public PetUpgradeRequest(int param1, Consumer param2) {
		super(param1, param2);
		this.slotObject = new SlotObjectData();
	}

	public int petTransType;
	public int PIDOne;
	public int PIDTwo;
	public int objectId;
	public SlotObjectData slotObject;
	public int paymentTransType;

	@Override
	public void writeToOutput(DataOutput param1) throws IOException {
		param1.writeByte(this.petTransType);
		param1.writeInt(this.PIDOne);
		param1.writeInt(this.PIDTwo);
		param1.writeInt(this.objectId);
		this.slotObject.writeToOutput(param1);
		param1.writeByte(this.paymentTransType);
	}

}
