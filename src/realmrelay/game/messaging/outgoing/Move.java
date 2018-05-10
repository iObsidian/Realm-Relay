package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.game.messaging.data.MoveRecord;
import realmrelay.game.messaging.data.WorldPosData;

public class Move extends OutgoingMessage {

	public int tickId;
	public int time;
	public WorldPosData newPosition;
	public MoveRecord[] records;
	
	public Move(int param1, Consumer param2) {
		super(param1, param2);
		newPosition = new WorldPosData();
		records = new MoveRecord[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tickId = in.readInt();
		time = in.readInt();
		newPosition.parseFromInput(in);
		records = new MoveRecord[in.readShort()];
		for (int i = 0; i < records.length; i++) {
			MoveRecord record = new MoveRecord();
			record.parseFromInput(in);
			records[i] = record;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tickId);
		out.writeInt(time);
		newPosition.writeToOutput(out);
		out.writeShort(records.length);
		for (MoveRecord record : records) {
			record.writeToOutput(out);
		}
	}

}
