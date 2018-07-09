package rotmg.particles;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.display.GraphicsBitmapFill;
import flash.display.GraphicsPath;
import flash.display.IGraphicsData;
import flash.geom.Matrix;
import flash.geom.Vector3D;
import rotmg.map.Camera;
import rotmg.objects.BasicObject;
import rotmg.objects.Square;
import rotmg.objects.animation.Animations;
import rotmg.util.GraphicsUtil;
import rotmg.util.TextureRedrawer;

/**
 * 100% match
 */
public class XMLParticle extends BasicObject {

	public BitmapData texture = null;

	public Animations animations = null;

	public int size;

	public double durationLeft;

	public Vector3D moveVec;

	protected GraphicsBitmapFill bitmapFill;

	protected GraphicsPath path;

	protected Vector<Double> vS;

	protected Vector<Double> uvt;

	protected Matrix fillMatrix;

	public XMLParticle(ParticleProperties param1) {

		super();

		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.vS = new Vector<Double>();
		this.uvt = new Vector<Double>();
		this.fillMatrix = new Matrix();
		objectId = getNextFakeObjectId();
		this.size = param1.size;
		z = param1.z;
		this.durationLeft = param1.duration;
		this.texture = param1.textureData.getTexture(objectId);
		if (param1.animationsData != null) {
			this.animations = new Animations(param1.animationsData);
		}
		this.moveVec = new Vector3D();
		double loc2 = Math.PI * 2 * Math.random();
		this.moveVec.x = Math.cos(loc2) * 0.1 * 5;
		this.moveVec.y = Math.sin(loc2) * 0.1 * 5;
	}

	public boolean moveTo(double param1, double param2) {
		Square loc3 = map.getSquare(param1, param2);
		if (loc3 == null) {
			return false;
		}
		x = param1;
		y = param2;
		square = loc3;
		return true;
	}

	public boolean update(int param1, int param2) {
		double loc3 = 0;
		loc3 = param2 / 1000;
		this.durationLeft = this.durationLeft - loc3;
		if (this.durationLeft <= 0) {
			return false;
		}
		x = x + this.moveVec.x * loc3;
		y = y + this.moveVec.y * loc3;
		return true;
	}

	public void draw(Vector<IGraphicsData> param1, Camera param2, int param3) {
		BitmapData loc7 = null;
		BitmapData loc4 = this.texture;
		if (this.animations != null) {
			loc7 = this.animations.getTexture(param3);
			if (loc7 != null) {
				loc4 = loc7;
			}
		}
		loc4 = TextureRedrawer.redraw(loc4, this.size, true, 0);
		int loc5 = loc4.width;
		int loc6 = loc4.height;
		this.vS.length = 0;
		this.vS.add(posS.get(3) - loc5 / 2, posS.get(4) - loc6, posS.get(3) + loc5 / 2, posS.get(4) - loc6, posS.get(3) + loc5 / 2, posS.get(4), posS.get(3) - loc5 / 2, posS.get(4));
		this.path.data = this.vS;
		this.bitmapFill.bitmapData = loc4;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS.get(0), this.vS.get(1));
		this.bitmapFill.matrix = this.fillMatrix;
		param1.add(this.bitmapFill);
		param1.add(this.path);
		param1.add(GraphicsUtil.END_FILL);
	}


}
