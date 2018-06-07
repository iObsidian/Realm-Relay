package rotmg.stage3d;

import flash.display3D.Context3D;
import flash.geom.Matrix3D;
import rotmg.stage3d.proxies.TextureProxy;

public class Context3DProxy {

	static Context3DProxy instance;

	public static Context3DProxy getInstance() {
		if (instance == null) {
			instance = new Context3DProxy();
		}
		return instance;
	}


	private Context3D context3D;

	public Context3DProxy(Context3D param1) {
		super();
		this.context3D = param1;
	}

	public Context3D GetContext3D() {
		return this.context3D;
	}

	public void configureBackBuffer(int param1, int param2, int param3, boolean param4) {
		this.context3D.configureBackBuffer(param1, param2, param3, param4);
	}

	public Program3DProxy createProgram() {
		return new Program3DProxy(this.context3D.createProgram());
	}

	public void clear() {
		this.context3D.clear(0.05, 0.05, 0.05);
	}

	public void present() {
		this.context3D.present();
	}

	public IndexBuffer3DProxy createIndexBuffer(int param1) {
		return new IndexBuffer3DProxy(this.context3D.createIndexBuffer(param1));
	}

	public VertexBuffer3DProxy createVertexBuffer(int param1, int param2) {
		return new VertexBuffer3DProxy(this.context3D.createVertexBuffer(param1, param2));
	}

	public void setVertexBufferAt(int param1, VertexBuffer3DProxy param2, int param3, String param4) {
		public void setVertexBufferAt (String param4 ){
			public void setVertexBufferAt (param1param2param3String
		}
		this.context3D.setVertexBufferAt(param1, param2.getVertexBuffer3D(), param3, param4);
	}

	public void setProgramConstantsFromMatrix(String param1, int param2, Matrix3D param3, boolean param4) {
		public void setProgramConstantsFromMatrix ( boolean param4 ){
			public void setProgramConstantsFromMatrix (param1param2param3boolean
		}
		this.context3D.setProgramConstantsFromMatrix(param1, param2, param3, param4);
	}

	public void setProgramConstantsFromVector(String param1, int param2, Vector<Double> param3, int param4) {
		public void setProgramConstantsFromVector ( int param4 ){
			public void setProgramConstantsFromVector (param1param2param3int
		}
		this.context3D.setProgramConstantsFromVector(param1, param2, param3, param4);
	}

	public TextureProxy createTexture(int param1, int param2, String param3, boolean param4) {
		return new TextureProxy(this.context3D.createTexture(param1, param2, param3, param4));
	}

	public void setTextureAt(int param1, TextureProxy param2) {
		this.context3D.setTextureAt(param1, param2.getTexture());
	}

	public void setProgram(Program3DProxy param1) {
		this.context3D.setProgram(param1.getProgram3D());
	}

	public void drawTriangles(IndexBuffer3DProxy param1) {
		this.context3D.drawTriangles(param1.getIndexBuffer3D());
	}

	public void setBlendFactors(String param1, String param2) {
		this.context3D.setBlendFactors(param1, param2);
	}

	public void setDepthTest(boolean param1, String param2) {
		this.context3D.setDepthTest(param1, param2);
	}


}
