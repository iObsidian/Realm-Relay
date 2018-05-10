package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class File extends IncomingMessage {

	public File(int id, Consumer callback) {
		super(id, callback);
	}

	private String filename;
	private byte[] bytes = new byte[0];

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
