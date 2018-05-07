package realmrelay.packets.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class MoveRecord extends WorldPosData {

	public int time;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		super.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		super.writeToOutput(out);
	}

}
