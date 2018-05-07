package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ChangeTradePacket extends Packet {

	private boolean[] offer;

	public ChangeTradePacket() {
		offer = new boolean[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		offer = new boolean[in.readShort()];
		for (int i = 0; i < offer.length; i++) {
			offer[i] = in.readBoolean();
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(offer.length);
		for (boolean b : offer) {
			out.writeBoolean(b);
		}
	}

}
