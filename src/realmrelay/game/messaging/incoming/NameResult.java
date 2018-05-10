package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class NameResult extends Packet {

	private boolean success;
	private String errorText;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		success = in.readBoolean();
		errorText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(success);
		out.writeUTF(errorText);
	}

}
