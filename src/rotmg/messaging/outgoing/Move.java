package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.MoveRecord;
import rotmg.messaging.data.WorldPosData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Move extends OutgoingMessage {

	public int tickId;
	public int time;
	public WorldPosData newPosition;
	public List<MoveRecord> records;

	public Move(int param1, MessageConsumer param2) {
		super(param1, param2);
		newPosition = new WorldPosData();
		records = new ArrayList<MoveRecord>();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		time = in.readInt();
		newPosition.parseFromInput(in);
		for (int i = 0; i < in.readShort(); i++) {
			MoveRecord record = new MoveRecord();
			record.parseFromInput(in);
			records.add(record);
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(time);
		newPosition.writeToOutput(out);
		out.writeShort(records.size());
		for (MoveRecord record : records) {
			record.writeToOutput(out);
		}
	}

}
