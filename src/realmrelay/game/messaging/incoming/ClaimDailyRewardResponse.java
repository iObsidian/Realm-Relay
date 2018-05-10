package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ClaimDailyRewardResponse extends IncomingMessage {

	public ClaimDailyRewardResponse(int id, Consumer callback) {
		super(id, callback);
	}

	public int itemId;
	public int quantity;
	public int gold;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.itemId = in.readInt();
		this.quantity = in.readInt();
		this.gold = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.itemId);
		out.writeInt(this.quantity);
		out.writeInt(this.gold);
	}

}
