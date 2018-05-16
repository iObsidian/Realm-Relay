package realmrelay.game.messaging.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game._as3.IData;

public class MoveRecord implements IData {

	public MoveRecord() {
	}
	
	public MoveRecord(int time, double x, double y) {
		super();
		this.time = time;
		this.x = x;
		this.y = y;
	}

	public int time;
	public double x;
	public double y;

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
