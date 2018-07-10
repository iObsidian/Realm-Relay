package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Death extends IncomingMessage {

	public String killedBy;
	public int zombieId;
	public int zombieType;
	public boolean isZombie;
	private String accountId;
	private int charId;

	public Death(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		accountId = in.readUTF();
		charId = in.readInt();
		killedBy = in.readUTF();
		zombieId = in.readInt();
		zombieType = in.readInt();
		this.isZombie = zombieType != -1;
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(accountId);
		out.writeInt(charId);
		out.writeUTF(killedBy);
		out.writeInt(zombieId);
		out.writeInt(zombieType);
	}

}
