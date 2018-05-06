package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class Entity implements IData {

	public short objectType;
	public Status status = new Status();

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		objectType = in.readShort();
		status.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeShort(objectType);
		status.writeToOutput(out);
	}

}
