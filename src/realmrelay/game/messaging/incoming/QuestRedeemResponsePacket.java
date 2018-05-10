package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class QuestRedeemResponsePacket extends Packet {

	private boolean ok;
	private String message;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		ok = in.readBoolean();
		message = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(ok);
		out.writeUTF(message);
	}

}
