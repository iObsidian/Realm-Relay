package rotmg.stage3d.proxies;


import flash.display.BitmapData;
import flash.display3D.textures.Texture;
import flash.display3D.textures.TextureBase;

public class TextureProxy {

	private Texture texture;

	protected int width;

	protected int height;

	public TextureProxy(Texture param1) {
		super();
		this.texture = param1;
	}

	public void uploadFromBitmapData(BitmapData param1) {
		this.width = param1.width();
		this.height = param1.height();
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
