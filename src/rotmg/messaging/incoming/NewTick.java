package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.ObjectStatusData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NewTick extends IncomingMessage {

	public int tickId;
	public int tickTime;
	public ObjectStatusData[] statuses = new ObjectStatusData[0];

	public NewTick(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		tickTime = in.readInt();
		statuses = new ObjectStatusData[in.readShort()];
		for (int i = 0; i < statuses.length; i++) {
			ObjectStatusData ObjectStatusData = new ObjectStatusData();
			ObjectStatusData.parseFromInput(in);
			statuses[i] = ObjectStatusData;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(tickTime);
		out.writeShort(statuses.length);
		for (ObjectStatusData ObjectStatusData : statuses) {
			ObjectStatusData.writeToOutput(out);
		}
	}

}
