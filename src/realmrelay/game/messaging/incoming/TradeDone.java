package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class TradeDone extends IncomingMessage {

	public static final int TRADE_SUCCESSFUL = 0;
	public static final int PLAYER_CANCELED = 1;

	public TradeDone(int id, Consumer callback) {
		super(id, callback);
	}

	private int code;
	private String description;

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
