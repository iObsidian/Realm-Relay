package rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class ClaimDailyRewardMessage extends OutgoingMessage {
	public String claimKey;
	public String type;

	public ClaimDailyRewardMessage(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void writeToOutput(DataOutput param1) throws IOException {
		param1.writeUTF(this.claimKey);
		param1.writeUTF(this.type);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.claimKey = in.readUTF();
		this.type = in.readUTF();
	}

}
