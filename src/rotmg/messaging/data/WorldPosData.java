package rotmg.messaging.data;

import alde.flash.utils.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Formelly known as 'Location' in RealmRelay 1.0
 */

public class WorldPosData implements IData {

	// - x = up
	// + x = down
	//
	// - y = left
	// + y = right

	public double x;
	public double y;

	public WorldPosData() {
		this.x = 0;
		this.y = 0;
	}

	public WorldPosData(double x, double y) {
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

	public double distanceSquaredTo(WorldPosData location) {
		double dx = location.x - x;
		double dy = location.y - y;
		return dx * dx + dy * dy;
	}

	public double distanceTo(WorldPosData location) {
		return (double) Math.sqrt(distanceSquaredTo(location));
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
		out.writeFloat((float) x);
		out.writeFloat((float) y);
	}

}
