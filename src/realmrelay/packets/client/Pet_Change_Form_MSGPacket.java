package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.SlotObject;
import realmrelay.packets.Packet;

public class Pet_Change_Form_MSGPacket extends Packet {

	//is named internaly 'ReskinPetPacket' but is mapped from packet PET_CHANGE_FORM_MSG

	private int petInstanceId;
	private int pickedNewPetType;
	private SlotObject item;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petInstanceId = in.readInt();
		pickedNewPetType = in.readInt();

		SlotObject e = new SlotObject();
		e.parseFromInput(in);
		item = e;
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petInstanceId);
		out.writeInt(pickedNewPetType);
		item.writeToOutput(out);
	}

}
