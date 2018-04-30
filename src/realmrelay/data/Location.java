package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class Location implements IData {

	// - x = up
	// + x = down
	//
	// - y = left
	// + y = right

	public float x;
	public float y;

	public Location() {
		this.x = 0;
		this.y = 0;
	}

	public Location(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Location(Location loc) {
		x = loc.x;
		y = loc.y;
	}

	@Override
	public Location clone() {
		return new Location(x, y);
	}

	public float distanceSquaredTo(Location location) {
		float dx = location.x - x;
		float dy = location.y - y;
		return dx * dx + dy * dy;
	}

	public float distanceTo(Location location) {
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
