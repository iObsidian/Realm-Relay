package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class GlobalNotification extends Packet {

	private int type;
	private String text;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		type = in.readInt();
		text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(type);
		out.writeUTF(text);
	}

}
