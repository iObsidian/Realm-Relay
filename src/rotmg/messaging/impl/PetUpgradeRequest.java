package rotmg.messaging.impl;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.SlotObjectData;
import rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class PetUpgradeRequest extends OutgoingMessage {

	public static final int GOLD_PAYMENT_TYPE = 0;
	public static final int FAME_PAYMENT_TYPE = 1;
	public int petTransType;
	public int PIDOne;
	public int PIDTwo;
	public int objectId;
	public SlotObjectData slotObject;
	public int paymentTransType;

	public PetUpgradeRequest(int param1, MessageConsumer param2) {
		super(param1, param2);
		this.slotObject = new SlotObjectData();
	}

	@Override
	public void writeToOutput(DataOutput in) throws IOException {
		in.writeByte(this.petTransType);
		in.writeInt(this.PIDOne);
		in.writeInt(this.PIDTwo);
		in.writeInt(this.objectId);
		this.slotObject.writeToOutput(in);
		in.writeByte(this.paymentTransType);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.petTransType = in.readByte();
		this.PIDOne = in.readInt();
		this.PIDTwo = in.readInt();
		this.objectId = in.readInt();
		this.slotObject.parseFromInput(in);
		this.paymentTransType = in.readByte();
	}

}
