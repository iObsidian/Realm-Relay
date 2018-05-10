package realmrelay.game.messaging.outgoing;

import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ClaimDailyRewardMessage extends OutgoingMessage {
	public String claimKey;
	public String type;

	public ClaimDailyRewardMessage(int param1, Consumer param2) {
		super(param1, param2);
	}

	@Override
	public void writeToOutput(DataOutput param1) throws IOException {
		param1.writeUTF(this.claimKey);
		param1.writeUTF(this.type);
	}

}
