package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class NotificationPacket extends Packet {

	private int objectId;
	private String message;
	private int color;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		objectId = in.readInt();
		message = in.readUTF();
		color = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(objectId);
		out.writeUTF(message);
		out.writeInt(color);
	}

}
