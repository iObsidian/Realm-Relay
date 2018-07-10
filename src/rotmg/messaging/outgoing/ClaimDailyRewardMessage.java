package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ClaimDailyRewardMessage extends OutgoingMessage {
	public String claimKey;
	public String type;

	public ClaimDailyRewardMessage(int id, MessageConsumer callback) {
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
