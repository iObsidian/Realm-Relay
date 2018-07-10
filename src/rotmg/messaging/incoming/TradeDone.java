package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TradeDone extends IncomingMessage {

	public static final int TRADE_SUCCESSFUL = 0;
	public static final int PLAYER_CANCELED = 1;
	public int code;
	public String description;

	public TradeDone(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		code = in.readInt();
		description = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(code);
		out.writeUTF(description);
	}

}
