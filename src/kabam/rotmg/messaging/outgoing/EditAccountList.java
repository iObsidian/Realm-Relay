package kabam.rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class EditAccountList extends OutgoingMessage {

	public int accountListId;
	public boolean add;
	public int objectId;

	public EditAccountList(int id, Consumer callback) {
		super(id, callback);
	}

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
