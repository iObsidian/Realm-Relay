package rotmg.dailyQuests;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.incoming.IncomingMessage;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class QuestRedeemResponse extends IncomingMessage {

	private boolean ok;
	private String message;

	public QuestRedeemResponse(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		ok = in.readBoolean();
		message = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(ok);
		out.writeUTF(message);
	}

}
