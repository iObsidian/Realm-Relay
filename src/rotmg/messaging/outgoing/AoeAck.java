package rotmg.messaging.outgoing;

import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class AoeAck extends OutgoingMessage {

	public int time;
	public WorldPosData position;
	
	public AoeAck(int id, Consumer callback) {
		super(id, callback);
		position = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		position.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		position.writeToOutput(out);
	}

}
