package rotmg.objects;
//import com.company.assembleegameclient.engine3d.TextureMatrix;
//import com.company.assembleegameclient.objects.GameObject;
//import com.company.assembleegameclient.util.TileRedrawer;

//import flash.display.BitmapData;
//import flash.display.IGraphicsData;
//import flash.geom.Vector3D;

import flash.display.BitmapData;
import flash.display.IGraphicsData;
import flash.geom.Vector3D;
import rotmg.map.*;
import rotmg.objects.GameObject;

import java.util.List;

public class Square {

	public static final double[] UVT = new double[]{0, 0, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0};

	private static final int[] LOOKUP = new int[]{26171, 44789, 20333, 70429, 98257, 59393, 33961};

	public Map map;

	public int x;

	public int y;

	public int tileType = 255;

	public Vector3D center;

	public double[] vin;

	public GameObject obj = null;

	public GroundProperties props;

	public BitmapData texture = null;

	public int sink = 0;

	public int lastDamage = 0;

	public List<SquareFace> faces;

	public SquareFace topFace = null;

	public TextureMatrix baseTexMatrix = null;

	public int lastVisible;

	public Square(Map param1, int param2, int param3) {
		this.props = GroundLibrary.defaultProps;
		this.faces = new List<SquareFace>();
		super();
		this.map = param1;
		this.x = param2;
		this.y = param3;
		this.center = new Vector3D(this.x + 0.5, this.y + 0.5, 0);
		this.vin = new double[]{this.x, this.y, 0, this.x + 1, this.y, 0, this.x + 1, this.y + 1, 0, this.x, this.y + 1, 0};
	}

	private int hash(int param1, int param2) {
		int loc3 = LOOKUP[(param1 + param2) % 7];
         *loc4 = (param1 << 16 | param2) ^ 81397550;
		loc4 = int(loc4 * loc3 % 65535);
		return loc4;
	}

	public void dispose() {
		SquareFace loc1 = null;
		this.map = null;
		this.center = null;
		this.vin = null;
		this.obj = null;
		this.texture = null;
		for (loc1: this.faces) {
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

	public void setTileType(int param1) {
		this.tileType = param1;
		this.props = GroundLibrary.propsLibrary.get(this.tileType);
		this.texture = GroundLibrary.getBitmapData(this.tileType, hash(this.x, this.y));
		this.baseTexMatrix = new TextureMatrix(this.texture, UVT);
		this.faces.length = 0;
	}

	public boolean isWalkable() {
		return !this.props.noWalk && (this.obj == null || !this.obj.props.occupySquare);
	}

	public void draw(List<IGraphicsData> param1, Camera param2, int param3) {
		SquareFace loc4 = null;
		if (this.texture == null) {
			return;
		}
		if (this.faces.length == 0) {
			this.rebuild3D();
		}
		for (loc4:
		     this.faces) {
			if (!loc4.draw(param1, param2, param3)) {
				if (loc4.face.vout[1] < param2.clipRect.bottom) {
					this.lastVisible = 0;
				}
				return;
			}
		}
	}

	public void drawTop(List<IGraphicsData> param1, Camera param2, int param3) {
		this.topFace.draw(param1, param2, param3);
	}

	private void rebuild3D() {
		double loc2 = 0;
		double loc3 = 0;
		BitmapData loc4 = null;
		List<Double> loc5 = null;
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
					loc2 = this.texture.width() * Math.random() / this.texture.width();
					loc3 = this.texture.height() * Math.random() / this.texture.height();
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
		if (this.props.topTD) {
			loc4 = this.props.topTD.getTexture();
			loc5 = this.vin.concat();
			loc6 = 2;
			while (loc6 < loc5.length) {
				loc5[loc6] = 1;
				loc6 = loc6 + 3;
			}
			this.topFace = new SquareFace(loc4, loc5, 0, 0, this.props.topAnimate.type, this.props.topAnimate.dx, this.props.topAnimate.dy);
		}
	}
}
}
