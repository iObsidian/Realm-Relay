package rotmg.objects;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import flash.display.BitmapData;
import flash.display.IGraphicsData;
import rotmg.engine3d.Face3D;
import rotmg.map.Camera;
import rotmg.util.BitmapUtil;

public class Wall extends GameObject {

	private static final Vector<Double> UVT = new Vector<Double>(0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0);

	private static final Vector<Double> sqX = new Vector<Double>(0.0, 1.0, 0.0, -1.0);

	private static final Vector<Double> sqY = new Vector<Double>(-1.0, 0.0, 1.0, 0.0);

	public Vector<Face3D> faces;

	private Face3D topFace = null;

	private BitmapData topTexture = null;

	public Wall(XML param1) {
		super(param1);
		this.faces = new Vector<Face3D>();
		hasShadow = false;
		TextureData loc2 = ObjectLibrary.typeToTopTextureData.get(objectType);
		this.topTexture = loc2.getTexture(0);
	}

	public void setObjectId(int param1) {
		super.setObjectId(param1);
		TextureData loc2 = ObjectLibrary.typeToTopTextureData.get(objectType);
		this.topTexture = loc2.getTexture(param1);
	}

	public int getColor() {
		return BitmapUtil.mostCommonColor(this.topTexture);
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData loc6 = null;
		Face3D loc7 = null;
		Square loc8 = null;
		if (texture == null) {
			return;
		}
		if (this.faces.length == 0) {
			this.rebuild3D();
		}
		BitmapData loc4 = texture;
		if (animations != null) {
			loc6 = animations.getTexture(param3);
			if (loc6 != null) {
				loc4 = loc6;
			}
		}
		int loc5 = 0;
		while (loc5 < this.faces.length) {
			loc7 = this.faces.get(loc5);
			loc8 = map.lookupSquare((x + sqX.get(loc5)), (y + sqY.get(loc5)));
			if (loc8 == null || loc8.texture == null || loc8 != null && loc8.obj instanceof Wall && !loc8.obj.dead) {
				loc7.blackOut = true;
			} else {
				loc7.blackOut = false;
				if (animations != null) {
					loc7.setTexture(loc4);
				}
			}
			loc7.draw(param1, param2);
			loc5++;
		}
		this.topFace.draw(param1, param2);
	}

	public void rebuild3D() {
		this.faces.length = 0;
		int loc1 = (int) x;
		int loc2 = (int) y;
		Vector loc3 = new Vector<>(loc1, loc2, 1, loc1 + 1, loc2, 1, loc1 + 1, loc2 + 1, 1, loc1, loc2 + 1, 1);
		this.topFace = new Face3D(this.topTexture, loc3, UVT, false, true);
		this.topFace.bitmapFill.repeat = true;
		this.addWall(loc1, loc2, 1, loc1 + 1, loc2, 1);
		this.addWall(loc1 + 1, loc2, 1, loc1 + 1, loc2 + 1, 1);
		this.addWall(loc1 + 1, loc2 + 1, 1, loc1, loc2 + 1, 1);
		this.addWall(loc1, loc2 + 1, 1, loc1, loc2, 1);
	}

	private void addWall(double param1, double param2, double param3, double param4, double param5, double param6) {
		Vector<Double> loc7 = new Vector<Double>(param1, param2, param3, param4, param5, param6, param4, param5, param6 - 1, param1, param2, param3 - 1);
		Face3D loc8 = new Face3D(texture, loc7, UVT, true, true);
		loc8.bitmapFill.repeat = true;
		this.faces.add(loc8);
	}
}
