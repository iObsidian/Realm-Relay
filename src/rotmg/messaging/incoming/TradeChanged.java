package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TradeChanged extends IncomingMessage {

	private boolean[] offers = new boolean[0];

	public TradeChanged(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		offers = new boolean[in.readShort()];
		for (int i = 0; i < offers.length; i++) {
			offers[i] = in.readBoolean();
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(offers.length);
		for (boolean b : offers) {
			out.writeBoolean(b);
		}
	}

}
