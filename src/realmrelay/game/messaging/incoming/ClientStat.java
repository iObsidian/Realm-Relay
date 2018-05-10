package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ClientStat extends Packet {

	private String name;
	private int value;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		name = in.readUTF();
		value = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(name);
		out.writeInt(value);
	}

}
