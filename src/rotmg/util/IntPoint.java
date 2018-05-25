package rotmg.util;

import java.awt.*;

public class IntPoint {

	public int x;

	public int y;

	public IntPoint() {
		this(0, 0);
	}

	public IntPoint(int param1, int param2) {
		this.x = param1;
		this.y = param2;
	}


	public static IntPoint fromPoint(Point param1) {
		return new IntPoint(Math.round(param1.x), Math.round(param1.y));
	}

	public int x() {
		return this.x;
	}

	public int y() {
		return this.y;
	}

	public void setX(int param1) {
		this.x = param1;
	}

	public void setY(int param1) {
		this.y = param1;
	}

	public IntPoint clone() {
		return new IntPoint(this.x, this.y);
	}

	public boolean same(IntPoint param1) {
		return this.x == param1.x && this.y == param1.y;
	}

	public int distanceAsInt(IntPoint param1) {
		int loc2 = param1.x - this.x;
		int loc3 = param1.y - this.y;
		return (int) Math.round(Math.sqrt(loc2 * loc2 + loc3 * loc3));
	}

	public double distanceAsNumber(IntPoint param1) {
		int loc2 = param1.x - this.x;
		int loc3 = param1.y - this.y;
		return Math.sqrt(loc2 * loc2 + loc3 * loc3);
	}

	public double distanceToPoint(Point param1) {
		int loc2 = param1.x - this.x;
		int loc3 = param1.y - this.y;
		return Math.sqrt(loc2 * loc2 + loc3 * loc3);
	}

	public IntPoint trunc1000() {
		return new IntPoint((int) (this.x / 1000) * 1000, (int) (this.y / 1000) * 1000);
	}

	public IntPoint round1000() {
		return new IntPoint(Math.round(this.x / 1000) * 1000, Math.round(this.y / 1000) * 1000);
	}

	public int distanceSquared(IntPoint param1) {
		int loc2 = param1.x() - this.x;
		int loc3 = param1.y() - this.y;
		return loc2 * loc2 + loc3 * loc3;
	}

	public Point toPoint() {
		return new Point(this.x, this.y);
	}

	/**
	 * public IntPoint transform(Matrix param1) {
	 * Point loc2 = param1.transformPoint(this.toPoint());
	 * return new IntPoint(Math.round(loc2.x), Math.round(loc2.y));
	 * }
	 */

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}


}
