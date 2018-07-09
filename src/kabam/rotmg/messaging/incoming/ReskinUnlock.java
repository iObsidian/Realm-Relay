package kabam.rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ReskinUnlock extends IncomingMessage {

	private int skinID;

	public ReskinUnlock(int id, Consumer callback) {
		super(id, callback);
	}

	public void parseFromInput(DataInput in) throws IOException {
		this.skinID = in.readInt();
	}

	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.skinID);
	}
}
