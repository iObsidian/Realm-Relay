package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class PasswordPromptPacket extends Packet {

	/**public final int SIGN_IN = 2;
	public final int SEND_EMAIL_AND_SIGN_IN = 3;
	public final int REGISTER = 4;**/

	private int cleanPasswordStatus;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		cleanPasswordStatus = in.readInt();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(cleanPasswordStatus);
	}

}
