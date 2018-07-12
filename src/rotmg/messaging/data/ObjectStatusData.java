package rotmg.messaging.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ObjectStatusData implements IData {

	public int objectId;
	public WorldPosData pos = new WorldPosData();
	public StatData[] stats = new StatData[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectId = in.readInt();
		pos.parseFromInput(in);

		stats = new StatData[in.readShort()];
		for (int i = 0; i < stats.length; i++) {
			StatData statData = new StatData();
			statData.parseFromInput(in);
			stats[i] = statData;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(objectId);
		pos.writeToOutput(out);
		out.writeShort(stats.length);
		for (StatData statData : stats) {
			statData.writeToOutput(out);
		}
	}

}
