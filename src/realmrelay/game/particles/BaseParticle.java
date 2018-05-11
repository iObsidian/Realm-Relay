package realmrelay.game.particles;

import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.packets.data.unused.BitmapData;

public class BaseParticle {

	public float timeLeft = 0;

	public float spdX;

	public float spdY;

	public float spdZ;

	protected List<float> vS;

	protected Matrix fillMatrix;

	protected GraphicsPath path;

	protected GraphicsBitmapFill bitmapFill;

	public function BaseParticle(bitmapData:BitmapData)
	{
		this.vS = new List<float>(8);
		this.fillMatrix = new Matrix();
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS,null);
		this.bitmapFill = new GraphicsBitmapFill(null,null,false,false);
		super();
		this.bitmapFill_.bitmapData = bitmapData;
		objectId = getNextFakeObjectId();
	}

	public function initialize(totalTime:float, speedX:float, speedY:float, speedZ:float, zPos:int) : void
	{
		this.timeLeft = totalTime;
		this.spdX = speedX;
		this.spdY = speedY;
		this.spdZ = speedZ;
		z = zPos;
	}

	override public function draw(graphicsData:List<IGraphicsData>, camera:Camera, time:int) : void
	{
		float halfW = this.bitmapFill_.bitmapData.width / 2;
		float halfH = this.bitmapFill_.bitmapData.height / 2;
		this.vS_[6] = this.vS_[0] = posS_[3] - halfW;
		this.vS_[3] = this.vS_[1] = posS_[4] - halfH;
		this.vS_[4] = this.vS_[2] = posS_[3] + halfW;
		this.vS_[7] = this.vS_[5] = posS_[4] + halfH;
		this.path_.data = this.vS_;
		this.fillMatrix_.identity();
		this.fillMatrix_.translate(this.vS_[0],this.vS_[1]);
		this.bitmapFill_.matrix = this.fillMatrix_;
		graphicsData.add(this.bitmapFill_);
		graphicsData.add(this.path_);
		graphicsData.add(GraphicsUtil.END_FILL);
	}

	override public function removeFromMap() : void
	{
		map = null;
		square = null;
	}


}
