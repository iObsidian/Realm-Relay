package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class File extends IncomingMessage {

	private String filename;
	private byte[] bytes = new byte[0];

	public File(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		filename = in.readUTF();
		bytes = new byte[in.readInt()];
		in.readFully(bytes);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(filename);
		out.writeInt(bytes.length);
		out.write(bytes);
	}

}
