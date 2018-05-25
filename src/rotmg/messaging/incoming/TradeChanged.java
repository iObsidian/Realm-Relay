package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class TradeChanged extends IncomingMessage {

	public TradeChanged(int id, Consumer callback) {
		super(id, callback);
	}

	private boolean[] offers = new boolean[0];

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
