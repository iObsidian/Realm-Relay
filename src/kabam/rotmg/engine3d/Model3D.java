package kabam.rotmg.engine3d;

import kabam.rotmg.stage3d.graphic3D.Object3DStage3D;

public class Model3D {
	public static Object3D getObject3D(String model) {
		return new Object3D();
	}

	public static Object3DStage3D getStage3dObject3D(String model) {
		return new Object3DStage3D(null);
	}
}
