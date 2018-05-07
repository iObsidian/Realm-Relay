package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class LoadPacket extends Packet {

	public int charId;
	private boolean isFromArena;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		charId = in.readInt();
		isFromArena = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(charId);
		out.writeBoolean(isFromArena);
	}

}
