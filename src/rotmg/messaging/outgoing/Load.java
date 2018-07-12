package rotmg.messaging.outgoing;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Load extends OutgoingMessage {

	public int charId;
	public boolean isFromArena;

	public Load(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		charId = in.readInt();
		isFromArena = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(charId);
		out.writeBoolean(isFromArena);
	}

	@Override
	public String toString() {
		return "Load{" +
				"charId=" + charId +
				", isFromArena=" + isFromArena +
				'}';
	}
}
