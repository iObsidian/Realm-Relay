package realmrelay.game.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.SlotObjectData;
import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.packets.Packet;

public class UseItemPacket extends Packet {

	private int time;
	private SlotObjectData slotObject;
	private WorldPosData itemUsePos;
	private int useType;

	public UseItemPacket() {
		slotObject = new SlotObjectData();
		itemUsePos = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		time = in.readInt();
		slotObject.parseFromInput(in);
		itemUsePos.parseFromInput(in);
		useType = in.readUnsignedByte();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(time);
		slotObject.writeToOutput(out);
		itemUsePos.writeToOutput(out);
		out.writeByte(useType);
	}

}
