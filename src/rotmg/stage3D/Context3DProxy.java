package rotmg.stage3D;

import flash.display3D.Context3D;

public class Context3DProxy {

	static Context3DProxy instance;
	private Context3D context3D;


	public Context3DProxy(Context3D param1) {
		this.context3D = param1;
	}

	public static Context3DProxy getInstance() {
		if (instance == null) {
			instance = new Context3DProxy(null);
		}
		return instance;
	}

	public Context3D GetContext3D() {
		return this.context3D;
	}

	public void configureBackBuffer(int param1, int param2, int param3, boolean param4) {
		this.context3D.configureBackBuffer(param1, param2, param3, param4);
	}


}
