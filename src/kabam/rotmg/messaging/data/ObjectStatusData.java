package kabam.rotmg.messaging.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ObjectStatusData implements IData {

	public int objectId;
	public WorldPosData pos;
	public StatData[] stats;

	public ObjectStatusData() {
		pos = new WorldPosData();
		stats = new StatData[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectId = in.readInt();
		pos.parseFromInput(in);

		stats = new StatData[in.readShort()];

		for (int i = 0; i < stats.length; i++) {
			StatData newStatData = new StatData();
			newStatData.parseFromInput(in);
			stats[i] = newStatData;
		}

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(this.objectId);
		this.pos.writeToOutput(out);
		out.writeShort(this.stats.length);

		for (StatData s : stats) {
			s.writeToOutput(out);
		}

	}

}
