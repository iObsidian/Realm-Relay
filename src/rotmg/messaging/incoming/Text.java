package rotmg.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class Text extends IncomingMessage {

	public Text(int id, Consumer callback) {
		super(id, callback);
	}

	public String name;
	public int objectId;
	public int numStars;
	public int bubbleTime;
	public String recipient;
	public String text;
	public String cleanText;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
		objectId = in.readInt();
		numStars = in.readInt();
		bubbleTime = in.readUnsignedByte();
		recipient = in.readUTF();
		text = in.readUTF();
		cleanText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(objectId);
		out.writeInt(numStars);
		out.writeByte(bubbleTime);
		out.writeUTF(recipient);
		out.writeUTF(text);
		out.writeUTF(cleanText);
	}

	@Override
	public String toString() {
		return "Text{" +
				"name='" + name + '\'' +
				", objectId=" + objectId +
				", numStars=" + numStars +
				", bubbleTime=" + bubbleTime +
				", recipient='" + recipient + '\'' +
				", text='" + text + '\'' +
				", cleanText='" + cleanText + '\'' +
				'}';
	}
}
