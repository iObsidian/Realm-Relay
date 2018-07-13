package rotmg.stage3D.proxies;


import flash.display.BitmapData;
import flash.display3D.textures.Texture;
import flash.display3D.textures.TextureBase;

public class TextureProxy {

	protected int width;
	protected int height;
	private Texture texture;

	public TextureProxy(Texture param1) {
		super();
		this.texture = param1;
	}

	public void uploadFromBitmapData(BitmapData param1) {
		this.width = param1.width;
		this.height = param1.height;
		this.texture.uploadFromBitmapData(param1);
	}

	public TextureBase getTexture() {
		return this.texture;
	}

	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public void dispose() {
		this.texture.dispose();
	}

}
