package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class KeyInfoResponse extends IncomingMessage {

	public String name;
	public String description;
	public String creator;

	public KeyInfoResponse(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
		this.description = in.readUTF();
		this.creator = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(description);
		out.writeUTF(creator);
	}

}
