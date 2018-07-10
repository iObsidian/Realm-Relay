package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class EvolvedPetMessage extends IncomingMessage {

	int petID;
	int initialSkin;
	int finalSkin;

	public EvolvedPetMessage(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		petID = in.readInt();
		initialSkin = in.readInt();
		finalSkin = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(petID);
		out.writeInt(initialSkin);
		out.writeInt(finalSkin);
	}

}
