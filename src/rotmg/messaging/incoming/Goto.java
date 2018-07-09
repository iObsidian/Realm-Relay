package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.messaging.data.WorldPosData;

public class Goto extends IncomingMessage {

	public int objectId;
	public WorldPosData pos;
	public Goto(int id, Consumer callback) {
		super(id, callback);
		pos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		pos.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		pos.writeToOutput(out);
	}

}
