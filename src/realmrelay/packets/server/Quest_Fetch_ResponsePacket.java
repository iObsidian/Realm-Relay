package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class Quest_Fetch_ResponsePacket extends Packet {

	private int tier;
	private String goal;
	private String description;
	private String image;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		tier = in.readInt();
		goal = in.readUTF();
		description = in.readUTF();
		image = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(tier);
		out.writeUTF(goal);
		out.writeUTF(description);
		out.writeUTF(image);
	}

}
