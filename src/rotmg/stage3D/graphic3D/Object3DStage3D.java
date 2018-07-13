package rotmg.stage3D.graphic3D;

import flash.display.BitmapData;
import flash.display3D.Context3D;
import flash.display3D.textures.Texture;
import flash.geom.Matrix3D;
import flash.geom.Vector3D;
import rotmg.stage3D.Model3D_stage3d;

public class Object3DStage3D {
	public static final BitmapData missingTextureBitmap = new BitmapData(1, 1, true, 2290649343.0);

	public Model3D_stage3d model = null;
	public Matrix3D modelMatrix;
	public Matrix3D modelView;
	public Matrix3D modelViewProjection;
	public Vector3D position;
	private BitmapData bitmapData;
	private double zRotation;

	private Texture texture;

	public Object3DStage3D(Model3D_stage3d param1) {
		super();
		this.model = param1;
		this.modelMatrix = new Matrix3D();
		this.modelView = new Matrix3D();
		this.modelViewProjection = new Matrix3D();
	}

	public void setBitMapData(BitmapData param1) {
		this.bitmapData = TextureFactory.GetFlippedBitmapData(param1);
	}

	public void setPosition(double param1, double param2, double param3, double param4) {
		this.position = new Vector3D(param1, -param2, param3);
		this.zRotation = param4;
	}

	public void dispose() {
		if (this.texture != null) {
			this.texture.dispose();
			this.texture = null;
		}
		this.bitmapData = null;
		this.modelMatrix = null;
		this.modelView = null;
		this.modelViewProjection = null;
		this.position = null;
	}

	public void UpdateModelMatrix(double param1, double param2) {
		this.modelMatrix.identity();
		this.modelMatrix.appendRotation(-90, Vector3D.Z_AXIS);
		this.modelMatrix.appendRotation(-this.zRotation, Vector3D.Z_AXIS);
		this.modelMatrix.appendTranslation(this.position.x, this.position.y, 0);
		this.modelMatrix.appendTranslation(param1, param2, 0);
	}

	public Matrix3D GetModelMatrix() {
		return this.modelMatrix;
	}

	public void draw(Context3D param1) {
		/**OBJGroup loc2 = null;
		 param1.setVertexBufferAt(0, this.model.vertexBuffer, 0, Context3DVertexBufferFormat.FLOAT_3);
		 param1.setVertexBufferAt(1, this.model.vertexBuffer, 3, Context3DVertexBufferFormat.FLOAT_3);
		 param1.setVertexBufferAt(2, this.model.vertexBuffer, 6, Context3DVertexBufferFormat.FLOAT_2);
		 if (this.texture == null && this.bitmapData != null) {
		 this.texture = param1.createTexture(this.bitmapData.width, this.bitmapData.height, Context3DTextureFormat.BGRA, false);
		 this.texture.uploadFromBitmapData(this.bitmapData);
		 } else if (this.texture == null) {
		 this.bitmapData = missingTextureBitmap;
		 this.texture = param1.createTexture(this.bitmapData.width, this.bitmapData.height, Context3DTextureFormat.BGRA, false);
		 this.texture.uploadFromBitmapData(this.bitmapData);
		 }
		 param1.setTextureAt(0, this.texture);
		 for (loc2:
		 this.model.groups) {
		 if (loc2.indexBuffer != null) {
		 param1.drawTriangles(loc2.indexBuffer);
		 }
		 }*/
	}

}
