package realmrelay.packets.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import realmrelay.packets.data.unused.IData;

public class ObjectStatusData implements IData {

	public String file;
	public int index;

	public int objectId;

	public WorldPosData pos;

	public Map<Integer, StatData> stats;

	public ObjectStatusData() {
		pos = new WorldPosData();
		stats = new HashMap<Integer, StatData>();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectId = in.readInt();
		pos.parseFromInput(in);
		int size = in.readShort();

		for (int i = 0; i < size; i++) {
			StatData newStatData = new StatData();
			newStatData.parseFromInput(in);
			stats.put(newStatData.statType, newStatData);
		}

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(this.objectId);
		this.pos.writeToOutput(out);
		out.writeShort(this.stats.size());
		for (Integer s : stats.keySet()) {
			stats.get(s).writeToOutput(out);
		}

	}

}
