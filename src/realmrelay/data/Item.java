package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Item implements IData {

	private int item;
	private int slotType;
	private boolean tradeable;
	private boolean included;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		item = in.readInt();
		slotType = in.readInt();
		tradeable = in.readBoolean();
		included = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(item);
		out.writeInt(slotType);
		out.writeBoolean(tradeable);
		out.writeBoolean(included);
	}


}
