package rotmg.particles;

import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import flash.display.BitmapData;
import rotmg.game.objects.BasicObject;
import rotmg.game.objects.Square;
import rotmg.game._as3.BitmapData;

public class XMLParticle extends BasicObject {

	public BitmapData texture = null;

	public Animations animations = null;

	public int size;

	public double durationLeft;

	public Vector3D moveVec;

	protected GraphicsBitmapFill bitmapFill;

	protected GraphicsPath path;

	protected double[] vS;

	protected  double[] uvt;

	protected Matrix fillMatrix;

	public XMLParticle(ParticleProperties props)
	{
		this.bitmapFill = new GraphicsBitmapFill(null,null,false,false);
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS,null);
		this.vS = new List<double>();
		this.uvt = new List<double>();
		this.fillMatrix = new Matrix();
		super();
		objectId = getNextFakeObjectId();
		this.size = props.size;
		z = props.z;
		this.durationLeft = props.duration;
		this.texture = props.textureData.getTexture(objectId);
		if(props.animationsData != null)
		{
			this.animations = new Animations(props.animationsData);
		}
		this.moveVec = new Vector3D();
		double moveAngle = Math.PI * 2 * Math.random();
		this.moveVec_.x = Math.cos(moveAngle) * 0.1 * 5;
		this.moveVec_.y = Math.sin(moveAngle) * 0.1 * 5;
	}

	public boolean moveTo(double x,double y)
	{
		Square square = null;
		square = map.getSquare(x,y);
		if(square == null)
		{
			return false;
		}
		x = x;
		y = y;
		square = square;
		return true;
	}

	 public boolean update(int time, int dt)
	{
		double fdt = dt / 1000;
		this.durationLeft = this.durationLeft - fdt;
		if(this.durationLeft <= 0)
		{
			return false;
		}
		x = x + this.moveVec.x * fdt;
		y = y + this.moveVec.y * fdt;
		return true;
	}

	@Override
	 public void draw(List<IGraphicsData> graphicsData, Camera camera, int time)
	{
		BitmapData animTexture = null;
		BitmapData texture = this.texture;
		if(this.animations != null)
		{
			animTexture = this.animations.getTexture(time);
			if(animTexture != null)
			{
				texture = animTexture;
			}
		}
		texture = TextureRedrawer.redraw(texture,this.size,true,0,0);
		int w = texture.width;
		int h = texture.height;
		this.vS.length = 0;
		this.vS.add(posS_[3] - w / 2,posS_[4] - h,posS_[3] + w / 2,posS_[4] - h,posS_[3] + w / 2,posS_[4],posS_[3] - w / 2,posS_[4]);
		this.path_.data = this.vS_;
		this.bitmapFill.bitmapData = texture;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS_[0],this.vS_[1]);
		this.bitmapFill.matrix = this.fillMatrix_;
		graphicsData.add(this.bitmapFill);
		graphicsData.add(this.path);
		graphicsData.add(GraphicsUtil.END_FILL);
	}


}
