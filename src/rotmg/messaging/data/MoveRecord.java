package rotmg.messaging.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class MoveRecord implements IData {

	public int time;
	public double x;
	public double y;

	public MoveRecord() {
	}

	public MoveRecord(int time, double x, double y) {
		super();
		this.time = time;
		this.x = x;
		this.y = y;
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		x = in.readDouble();
		y = in.readDouble();

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeDouble(x);
		out.writeDouble(y);
	}

}
