package realmrelay.game.messaging.incoming.arena;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class ImminentArenaWavePacket extends Packet {

	private int currentRuntime;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		currentRuntime = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(currentRuntime);
	}

}
