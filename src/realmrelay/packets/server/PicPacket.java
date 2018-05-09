package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.unused.BitmapData;

/** This implementation was dropped due to the removed of IData from BitmapData
public class PicPacket extends Packet {

	private BitmapData bitmapData;

	public PicPacket() {
		this.bitmapData = new BitmapData(0, 0);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bitmapData.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		bitmapData.writeToOutput(out);
	}

}
*/