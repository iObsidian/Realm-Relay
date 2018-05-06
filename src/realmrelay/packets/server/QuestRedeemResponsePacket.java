package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class QuestRedeemResponsePacket extends Packet {

	private boolean success;
	private String message;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		success = in.readBoolean();
		message = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(success);
		out.writeUTF(message);
	}

}
