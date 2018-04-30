package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class BuyPacket extends Packet {

	private int objectId;
	private int quantity;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		quantity = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		out.writeInt(quantity);
	}

}
