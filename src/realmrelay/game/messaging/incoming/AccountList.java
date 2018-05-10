package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class AccountList extends Packet {

	public int accountListId;
	private String[] accountIds;
	private int lockAction = -1;

	public AccountList() {
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
