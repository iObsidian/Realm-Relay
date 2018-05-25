package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class EvolvedPetMessage extends IncomingMessage {

	public EvolvedPetMessage(int id, Consumer callback) {
		super(id, callback);
	}

	int petID;
	int initialSkin;
	int finalSkin;

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