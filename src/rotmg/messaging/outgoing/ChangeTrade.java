package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ChangeTrade extends OutgoingMessage {

	public boolean[] offer;

	public ChangeTrade(int id, MessageConsumer callback) {
		super(id, callback);
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
