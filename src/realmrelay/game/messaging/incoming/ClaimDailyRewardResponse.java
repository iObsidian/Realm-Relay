package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.IOException;
import java.util.function.Consumer;

public class ClaimDailyRewardResponse extends IncomingMessage {

	public int itemId;
	public int quantity;
	public int gold;

	public ClaimDailyRewardResponse(int param1, Consumer param2) {
		super(param1, param2);
	}

	@Override
	public void parseFromInput(DataInput param1) throws IOException {
		this.itemId = param1.readInt();
		this.quantity = param1.readInt();
		this.gold = param1.readInt();
	}

}
