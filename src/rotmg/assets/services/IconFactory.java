package rotmg.assets.services;

import flash.display.Bitmap;
import flash.display.BitmapData;
import rotmg.util.AssetLibrary;
import rotmg.util.BitmapUtil;
import rotmg.util.TextureRedrawer;
import rotmg.util.redrawers.GlowRedrawer;

public class IconFactory {

	public IconFactory() {
		super();
	}

	public static BitmapData makeCoin() {
		BitmapData loc2 = TextureRedrawer.resize(AssetLibrary.getImageFromSet("lofiObj3", 225), null, 40, true, 0, 0);
		return cropAndGlowIcon(loc2);
	}

	public static BitmapData makeFortune() {
		BitmapData loc1 = TextureRedrawer.resize(AssetLibrary.getImageFromSet("lofiCharBig", 32), null, 20, true, 0, 0);
		return cropAndGlowIcon(loc1);
	}

	public static BitmapData makeFame() {
		BitmapData loc2 = TextureRedrawer.resize(AssetLibrary.getImageFromSet("lofiObj3", 224), null, 40, true, 0, 0);
		return cropAndGlowIcon(loc2);
	}

	public static BitmapData makeGuildFame() {
		BitmapData loc1 = TextureRedrawer.resize(AssetLibrary.getImageFromSet("lofiObj3", 226), null, 40, true, 0, 0);
		return cropAndGlowIcon(loc1);
	}

	private static BitmapData cropAndGlowIcon(BitmapData param1) {
		param1 = GlowRedrawer.outlineGlow(param1, 4294967295.0);
		param1 = BitmapUtil.cropToBitmapData(param1, 10, 10, param1.width - 20, param1.height - 20);
		return param1;
	}

	public Bitmap makeIconBitmap(int param1) {
		BitmapData loc2 = AssetLibrary.getImageFromSet("lofiInterfaceBig", param1);
		loc2 = TextureRedrawer.redraw(loc2, 320 / loc2.width, true, 0);
		return new Bitmap(loc2);
	}


}
