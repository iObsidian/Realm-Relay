package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Status implements IData {

	public int objectId;
	public Location pos = new Location();
	public StatData[] data = new StatData[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectId = in.readInt();
		pos.parseFromInput(in);
		data = new StatData[in.readShort()];
		for (int i = 0; i < data.length; i++) {
			StatData statData = new StatData();
			statData.parseFromInput(in);
			data[i] = statData;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(objectId);
		pos.writeToOutput(out);
		out.writeShort(data.length);
		for (StatData statData : data) {
			statData.writeToOutput(out);
		}
	}

}
