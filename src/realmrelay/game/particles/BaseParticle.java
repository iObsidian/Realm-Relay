package realmrelay.game.particles;

import javafx.scene.Camera;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.game.objects.BasicObject;
import realmrelay.packets.data.unused.BitmapData;

public class BaseParticle extends BasicObject {

	public float timeLeft = 0;

	public float spdX;

	public float spdY;

	public float spdZ;

	protected float[] vS;

	protected Matrix fillMatrix;

	protected GraphicsPath path;

	protected GraphicsBitmapFill bitmapFill;

	public function BaseParticle(BitmapData bitmapData) {
		this.vS = new float[8];
		this.fillMatrix = new Matrix();
		this.path = new GraphicsPath(GraphicsUtil.QUAD_COMMANDS, null);
		this.bitmapFill = new GraphicsBitmapFill(null, null, false, false);
		this.bitmapFill.bitmapData = bitmapData;
		objectId = getNextFakeObjectId();
	}

	public void initialize(float totalTime, float speedX, float speedY, float speedZ, int zPos) {
		this.timeLeft = totalTime;
		this.spdX = speedX;
		this.spdY = speedY;
		this.spdZ = speedZ;
		z = zPos;
	}

	@Override
	public void draw(List<IGraphicsData> graphicsData, Camera camera, int time) {
		float halfW = this.bitmapFill.bitmapData.width / 2;
		float halfH = this.bitmapFill.bitmapData.height / 2;
		this.vS[6] = this.vS[0] = posS[3] - halfW;
		this.vS[3] = this.vS[1] = posS[4] - halfH;
		this.vS[4] = this.vS[2] = posS[3] + halfW;
		this.vS[7] = this.vS[5] = posS[4] + halfH;
		this.path.data = this.vS;
		this.fillMatrix.identity();
		this.fillMatrix.translate(this.vS[0], this.vS_[1]);
		this.bitmapFill.matrix = this.fillMatrix;
		graphicsData.add(this.bitmapFill_);
		graphicsData.add(this.path_);
		graphicsData.add(GraphicsUtil.END_FILL);
	}

	@Override
	public void removeFromMap() {
		map = null;
		square = null;
	}


}
