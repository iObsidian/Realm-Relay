package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class EnemyHitPacket extends Packet {

	public int time;

	public int bulletId;

	public int targetId;

	public boolean kill;

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
