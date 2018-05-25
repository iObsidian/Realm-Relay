package rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class EnemyHit extends OutgoingMessage {

	public int time;
	public int bulletId;
	public int targetId;
	public boolean kill;
	
	public EnemyHit(int id, Consumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.time = in.readInt();
		this.bulletId = in.readByte();
		this.targetId = in.readInt();
		this.kill = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.time);
		out.writeByte(this.bulletId);
		out.writeInt(this.targetId);
		out.writeBoolean(this.kill);
	}
}
