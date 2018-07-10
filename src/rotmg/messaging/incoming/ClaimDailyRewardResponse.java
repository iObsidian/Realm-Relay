package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ClaimDailyRewardResponse extends IncomingMessage {

	public int itemId;
	public int quantity;
	public int gold;

	public ClaimDailyRewardResponse(int id, MessageConsumer callback) {
		super(id, callback);
	}

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
