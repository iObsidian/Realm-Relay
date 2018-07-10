package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class AccountList extends IncomingMessage {

	public int accountListId;
	public String[] accountIds;
	private int lockAction = -1;

	public AccountList(int param1, MessageConsumer param2) {
		super(param1, param2);
		accountIds = new String[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		accountListId = in.readInt();
		accountIds = new String[in.readShort()];
		for (int i = 0; i < accountIds.length; i++) {
			accountIds[i] = in.readUTF();
		}
		lockAction = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(accountListId);
		out.writeShort(accountIds.length);
		for (String accountId : accountIds) {
			out.writeUTF(accountId);
		}
		out.writeInt(lockAction);
	}

}
