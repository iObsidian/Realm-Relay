package rotmg.objects;
//import com.company.assembleegameclient.engine3d.TextureMatrix;
//import com.company.assembleegameclient.objects.GameObject;
//import com.company.assembleegameclient.util.TileRedrawer;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.IGraphicsData;
import flash.geom.Vector3D;
import oryx2D.graphics.Screen;
import rotmg.engine3d.TextureMatrix;
import rotmg.map.*;
import rotmg.util.TileRedrawer;

//import flash.display.BitmapData;
//import flash.display.IGraphicsData;
//import flash.geom.Vector3D;

public class Square {

	public static final Vector<Double> UVT = new Vector<>(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0);

	private static final int[] LOOKUP = new int[]{26171, 44789, 20333, 70429, 98257, 59393, 33961};

	public Map map;

	public int x;

	public int y;

	public int tileType = 255;

	public Vector3D center;

	public Vector<Double> vin;

	public GameObject obj = null;

	public GroundProperties props;

	public BitmapData texture = null;

	public int sink = 0;

	public int lastDamage = 0;

	public Vector<SquareFace> faces;

	public SquareFace topFace = null;

	public TextureMatrix baseTexMatrix = null;

	public int lastVisible;

	public Square(Map param1, int param2, int param3) {
		super();
		this.props = GroundLibrary.defaultProps;
		this.faces = new Vector<>();
		this.map = param1;
		this.x = param2;
		this.y = param3;
		this.center = new Vector3D(this.x + 0.5, this.y + 0.5, 0);
		this.vin = new Vector<Double>((double) this.x, (double) this.y, (double) 0, (double) this.x + 1, (double) this.y, (double) 0, (double) this.x + 1, (double) this.y + 1, (double) 0, (double) this.x, (double) this.y + 1, (double) 0);
	}

	// Not sure this is a good implementation
	private int hash(double p1, double p2) {
		return (int) ((int) p1 * 2949 + (int)p2);
	}

	public void dispose() {
		this.map = null;
		this.center = null;
		this.vin = null;
		this.obj = null;
		this.texture = null;
		for (SquareFace loc1 : this.faces) {
			loc1.dispose();
		}
		this.faces.length = 0;
		if (this.topFace != null) {
			this.topFace.dispose();
			this.topFace = null;
		}
		this.faces = null;
		this.baseTexMatrix = null;
	}

	public Square setTileType(int tileType) {
		this.tileType = tileType;
		this.props = GroundLibrary.propsLibrary.get(this.tileType);
		this.texture = GroundLibrary.getBitmapData(this.tileType, hash(this.x, this.y));
		this.baseTexMatrix = new TextureMatrix(this.texture, UVT);
		this.faces.length = 0;

		return this;
	}

	public boolean isWalkable() {
		return !this.props.noWalk && (this.obj == null || !this.obj.props.occupySquare);
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		if (this.texture == null) {
			return;
		}
		if (this.faces.length == 0) {
			this.rebuild3D();
		}
		for (SquareFace loc4 : this.faces) {
			if (!loc4.draw(param1, param2, param3)) {
				if (loc4.face.vout.get(1) < param2.clipRect.bottom) {
					this.lastVisible = 0;
				}
				return;
			}
		}
	}

	public void drawTop(Vector<IGraphicsData> param1, Camera param2, int param3) {
		this.topFace.draw(param1, param2, param3);
	}

	private void rebuild3D() {
		double loc2 = 0;
		double loc3 = 0;
		BitmapData loc4 = null;
		Vector<Double> loc5 = null;
		int loc6 = 0;
		this.faces.length = 0;
		this.topFace = null;
		BitmapData loc1 = null;
		if (this.props.animate.type != AnimateProperties.NO_ANIMATE) {
			this.faces.add(new SquareFace(this.texture, this.vin, this.props.xOffset, this.props.yOffset, this.props.animate.type, this.props.animate.dx, this.props.animate.dy));
			loc1 = TileRedrawer.redraw(this, false);
			if (loc1 != null) {
				this.faces.add(new SquareFace(loc1, this.vin, 0, 0, AnimateProperties.NO_ANIMATE, 0, 0));
			}
		} else {
			loc1 = TileRedrawer.redraw(this, true);
			loc2 = 0;
			loc3 = 0;
			if (loc1 == null) {
				if (this.props.randomOffset) {
					loc2 = this.texture.width * Math.random() / this.texture.width;
					loc3 = this.texture.height * Math.random() / this.texture.height;
				} else {
					loc2 = this.props.xOffset;
					loc3 = this.props.yOffset;
				}
			}
			this.faces.add(new SquareFace(loc1 != null ? loc1 : this.texture, this.vin, loc2, loc3, AnimateProperties.NO_ANIMATE, 0, 0));
		}
		if (this.props.sink) {
			this.sink = loc1 == null ? 12 : 6;
		} else {
			this.sink = 0;
		}
		if (this.props.topTD != null) {
			loc4 = this.props.topTD.getTexture();
			loc5 = this.vin.concat();
			loc6 = 2;
			while (loc6 < loc5.length) {
				loc5.put(loc6, 1.0);
				loc6 = loc6 + 3;
			}
			this.topFace = new SquareFace(loc4, loc5, 0, 0, this.props.topAnimate.type, this.props.topAnimate.dx, this.props.topAnimate.dy);
		}
	}

	/**
	 * Every tiles render themselves
	 */
	public void render(int x, int y, Screen screen) {

		if (texture != null) {
			screen.render(x * texture.height, y * texture.width, texture);
		}


	}

	public boolean isSolid() {
		return this.props.noWalk;
	}

}
