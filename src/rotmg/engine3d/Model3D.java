package rotmg.engine3d;

<<<<<<< HEAD:src/kabam/rotmg/engine3d/Model3D.java
import kabam.rotmg.stage3d.graphic3D.Object3DStage3D;
<<<<<<< HEAD:src/rotmg/engine3d/Model3D.java
=======
import rotmg.stage3d.graphic3D.Object3DStage3D;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/engine3d/Model3D.java
=======
import rotmg.stage3d.graphic3D.Object3DStage3D;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/engine3d/Model3D.java

public class Model3D {
	public static Object3D getObject3D(String model) {
		return new Object3D();
	}

	public static Object3DStage3D getStage3dObject3D(String model) {
		return new Object3DStage3D(null);
	}
}
