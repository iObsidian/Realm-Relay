package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Global_NotificationPacket extends Packet {

	private int typeId;
	private String text;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		typeId = in.readInt();
		text = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(typeId);
		out.writeUTF(text);
	}

}
