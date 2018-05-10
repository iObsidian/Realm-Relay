package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.WorldPosData;
import realmrelay.packets.Packet;

public class ShowEffectPacket extends Packet {

	public static final int UNKNOWN_EFFECT_TYPE = 0;
	public static final int HEAL_EFFECT_TYPE = 1;
	public static final int TELEPORT_EFFECT_TYPE = 2;
	public static final int STREAM_EFFECT_TYPE = 3;
	public static final int THROW_EFFECT_TYPE = 4;
	public static final int NOVA_EFFECT_TYPE = 5;
	public static final int POISON_EFFECT_TYPE = 6;
	public static final int LINE_EFFECT_TYPE = 7;
	public static final int BURST_EFFECT_TYPE = 8;
	public static final int FLOW_EFFECT_TYPE = 9;
	public static final int RING_EFFECT_TYPE = 10;
	public static final int LIGHTNING_EFFECT_TYPE = 11;
	public static final int COLLAPSE_EFFECT_TYPE = 12;
	public static final int CONEBLAST_EFFECT_TYPE = 13;
	public static final int JITTER_EFFECT_TYPE = 14;
	public static final int FLASH_EFFECT_TYPE = 15;
	public static final int THROW_PROJECTILE_EFFECT_TYPE = 16;
	public static final int SHOCKER_EFFECT_TYPE = 17;
	public static final int SHOCKEE_EFFECT_TYPE = 18;
	public static final int RISING_FURY_EFFECT_TYPE = 19;
	public static final int NOVA_NO_AOE_EFFECT_TYPE = 20;

	private int effectType;
	private int targetObjectId;
	private WorldPosData pos1;
	private WorldPosData pos2;
	private int color;
	private float duration;


	public ShowEffectPacket() {
		this.pos1 = new WorldPosData();
		this.pos2 = new WorldPosData();
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		effectType = in.readUnsignedByte();
		targetObjectId = in.readInt();
		pos1.parseFromInput(in);
		pos2.parseFromInput(in);
		color = in.readInt();
		duration = in.readFloat();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeByte(effectType);
		out.writeInt(targetObjectId);
		pos1.writeToOutput(out);
		pos2.writeToOutput(out);
		out.writeInt(color);
		out.writeFloat(duration);
	}

}
