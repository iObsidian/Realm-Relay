package realmrelay.game.messaging.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.data.unused.IData;

/**
 * Formelly known as 'Location' in RealmRelay 1.0
 */

public class WorldPosData implements IData {

	// - x = up
	// + x = down
	//
	// - y = left
	// + y = right

	public float x;
	public float y;

	public WorldPosData() {
		this.x = 0;
		this.y = 0;
	}

	public WorldPosData(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public WorldPosData(WorldPosData loc) {
		x = loc.x;
		y = loc.y;
	}

	@Override
	public WorldPosData clone() {
		return new WorldPosData(x, y);
	}

	public float distanceSquaredTo(WorldPosData location) {
		float dx = location.x - x;
		float dy = location.y - y;
		return dx * dx + dy * dy;
	}

	public float distanceTo(WorldPosData location) {
		return (float) Math.sqrt(distanceSquaredTo(location));
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		x = in.readFloat();
		y = in.readFloat();
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeFloat(x);
		out.writeFloat(y);
	}

}
