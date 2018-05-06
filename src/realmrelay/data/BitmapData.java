package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class BitmapData implements IData {

	private int width;
	private int height;
	private byte[] bytes = new byte[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		width = in.readInt();
		height = in.readInt();
		bytes = new byte[width * height * 4];
		in.readFully(bytes);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

		out.writeInt(width);
		out.writeInt(height);
		out.write(bytes);
	}

}
