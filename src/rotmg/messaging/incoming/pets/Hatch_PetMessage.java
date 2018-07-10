package rotmg.messaging.incoming.pets;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.outgoing.OutgoingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Hatch_PetMessage extends OutgoingMessage {

	private String petName;
	private int petSkinId;

	public Hatch_PetMessage(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petName = in.readUTF();
		petSkinId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(petName);
		out.writeInt(petSkinId);

	}

}
