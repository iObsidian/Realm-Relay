package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class TradeDonePacket extends Packet {

	public static final int TRADE_SUCCESSFUL = 0;
	public static final int PLAYER_CANCELED = 1;

	private int code;
	private String description;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		code = in.readInt();
		description = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(code);
		out.writeUTF(description);
	}

}
