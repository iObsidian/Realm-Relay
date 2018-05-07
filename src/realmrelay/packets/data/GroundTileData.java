package realmrelay.packets.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.data.unused.IData;

public class GroundTileData implements IData {

	private short x;
	private short y;
	private int type;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		x = in.readShort();
		y = in.readShort();
		type = in.readUnsignedShort();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(x);
		out.writeShort(y);
		out.writeShort(type);
	}

}
