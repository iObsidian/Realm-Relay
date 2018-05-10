package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EditAccountListPacket extends Packet {

	public int accountListId;
	public boolean add;
	public int objectId;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		accountListId = in.readInt();
		add = in.readBoolean();
		objectId = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(accountListId);
		out.writeBoolean(add);
		out.writeInt(objectId);
	}

}
