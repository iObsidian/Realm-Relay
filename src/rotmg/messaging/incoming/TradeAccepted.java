package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TradeAccepted extends IncomingMessage {

	private boolean[] myOffer = new boolean[0];
	private boolean[] yourOffer = new boolean[0];

	public TradeAccepted(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		myOffer = new boolean[in.readShort()];
		for (int i = 0; i < myOffer.length; i++) {
			myOffer[i] = in.readBoolean();
		}
		yourOffer = new boolean[in.readShort()];
		for (int i = 0; i < yourOffer.length; i++) {
			yourOffer[i] = in.readBoolean();
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(myOffer.length);
		for (boolean b : myOffer) {
			out.writeBoolean(b);
		}
		out.writeShort(yourOffer.length);
		for (boolean b : yourOffer) {
			out.writeBoolean(b);
		}
	}

}
