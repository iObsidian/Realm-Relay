package realmrelay.game.messaging.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.data.unused.IData;

public class MoveRecord implements IData {

	public MoveRecord() {
	}
	
	public MoveRecord(int time, float x, float y) {
		super();
		this.time = time;
		this.x = x;
		this.y = y;
	}

	public int time;
	public float x;
	public float y;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		x = in.readFloat();
		y = in.readFloat();

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		out.writeFloat(x);
		out.writeFloat(y);
	}

}
