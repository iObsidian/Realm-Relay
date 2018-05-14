package realmrelay.game.util;

import com.sun.prism.ps.Shader;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import realmrelay.packets.data.unused.BitmapData;
import sun.java2d.cmm.ColorTransform;

public class TextureRedrawer {

	public static final int magic = 12;

	public static final int minSize = 2 * magic;

	private static final int BORDER = 4;

	public static final GlowFilter OUTLINE_FILTER = new GlowFilter(0, 0.8, 1.4, 1.4, 255, BitmapFilterQuality.LOW, false, false);

	private static Dictionary cache = new Dictionary();

	private static Dictionary faceCache = new Dictionary();

	private static Dictionary redrawCaches = new Dictionary();

	public static BitmapData sharedTexture = null;

	private static Class textureShaderEmbed = TextureRedrawer_textureShaderEmbed_;

	private static ByteArray textureShaderData = (ByteArray) new textureShaderEmbed();

	private static BitmapData colorTexture1 = new BitmapDataSpy(1, 1, false);

	private static BitmapData colorTexture2 = new BitmapDataSpy(1, 1, false);


	public  TextureRedrawer()  {
		super();
	}

	public static BitmapData redraw(BitmapData param1, int param, boolean param3, int param4, true, 5F, 0) {

	}

	public static BitmapData  redraw(BitmapData param1, int param2, boolean param3, int param4, boolean param5, double param6, int param7)  {
		String loc8 = getHash(param2, param3, param4, param6, param7);
		if (param5 && isCached(param1, loc8)) {
			return redrawCaches[param1][loc8];
		}
		BitmapData loc9 = resize(param1, null, param2, param3, 0, 0, param6);
		loc9 = GlowRedrawer.outlineGlow(loc9, param4, 1.4, param5, param7);
		if (param5) {
			cache(param1, loc8, loc9);
		}
		return loc9;
	}

	private static String  getHash(int param1, boolean param2, int param3, double param4, int param5)  {
		return param1.toString() + "," + param3.toString() + "," + param2 + "," + param4 + "," + param5;
	}

	private static void  cache(BitmapData param1, String param2, BitmapData param3)  {
		if (!(param1 in redrawCaches)) {
			redrawCaches[param1] = {};
		}
		redrawCaches[param1][param2] = param3;
	}

	private static boolean  isCached(BitmapData param1, String param2)  {
		if (param1 in redrawCaches) {
			if (param2 in redrawCaches[param1]) {
				return true;
			}
		}
		return false;
	}

	public static BitmapData  resize(BitmapData param1, BitmapData param2, int param3, boolean param4, int param5, int param6, double = 5 param7)  {
		if (param2 != null && (param5 != 0 || param6 != 0)) {
			param1 = retexture(param1, param2, param5, param6);
			param3 = param3 / 5;
		}
		int loc8 = param7 * (param3 / 100) * param1.width;
		int loc9 = param7 * (param3 / 100) * param1.height;
		Matrix loc10 = new Matrix();
		loc10.scale(loc8 / param1.width, loc9 / param1.height);
		loc10.translate(magic, magic);
		var loc11:BitmapData = new BitmapDataSpy(loc8 + minSize, loc9 + (!!param4 ? magic : 1) + magic, true, 0);
		loc11.draw(param1, loc10);
		return loc11;
	}

	public static BitmapData  redrawSolidSquare(int param1, int param2)  {
		var loc3:Dictionary = cache_[param2];
		if (loc3 == null) {
			loc3 = new Dictionary();
			cache_[param2] = loc3;
		}
		BitmapData loc4 = loc3[param1];
		if (loc4 != null) {
			return loc4;
		}
		loc4 = new BitmapDataSpy(param2 + 4 + 4, param2 + 4 + 4, true, 0);
		loc4.fillRect(new Rectangle(4, 4, param2, param2), 4278190080 | param1);
		loc4.applyFilter(loc4, loc4.rect, PointUtil.ORIGIN, OUTLINE_FILTER);
		loc3[param1] = loc4;
		return loc4;
	}

	public static void  clearCache()  {
		BitmapData loc1 = null;
		Dictionary loc2 = null;
		Dictionary loc3 = null;
		for(loc2 in cache_) {
			for(loc1 in loc2) {
				loc1.dispose();
			}
		}
		cache = new Dictionary();
		for(loc3 in faceCache_) {
			for(loc1 in loc3) {
				loc1.dispose();
			}
		}
		faceCache = new Dictionary();
	}

	public static BitmapData  redrawFace(BitmapData param1, double param2)  {
		if (param2 == 1) {
			return param1;
		}
		var loc3:Dictionary = faceCache_[param2];
		if (loc3 == null) {
			loc3 = new Dictionary();
			faceCache_[param2] = loc3;
		}
		BitmapData loc4 = loc3[param1];
		if (loc4 != null) {
			return loc4;
		}
		loc4 = param1.clone();
		loc4.colorTransform(loc4.rect, new ColorTransform(param2, param2, param2));
		loc3[param1] = loc4;
		return loc4;
	}

	private static BitmapData  getTexture(int param1, BitmapData param2)  {
		BitmapData loc3 = null;
        * loc4 = param1 >> 24 & 255;
        * loc5 = param1 & 16777215;
		switch (loc4) {
			case 0:
				loc3 = param2;
				break;
			case 1:
				param2.setPixel(0, 0, loc5);
				loc3 = param2;
				break;
			case 4:
				loc3 = AssetLibrary.getImageFromSet("textile4x4", loc5);
				break;
			case 5:
				loc3 = AssetLibrary.getImageFromSet("textile5x5", loc5);
				break;
			case 9:
				loc3 = AssetLibrary.getImageFromSet("textile9x9", loc5);
				break;
			case 10:
				loc3 = AssetLibrary.getImageFromSet("textile10x10", loc5);
				break;
			case 255:
				loc3 = sharedTexture_;
				break;
			default:
				loc3 = param2;
		}
		return loc3;
	}

	private static BitmapData  retexture(BitmapData param1, BitmapData param2, int param3, int param4)  {
		Matrix loc5 = new Matrix();
		loc5.scale(5, 5);
		BitmapData loc6 = new BitmapDataSpy(param1.width * 5, param1.height * 5, true, 0);
		loc6.draw(param1, loc5);
		var loc7:BitmapData = getTexture(param3 >= 0 ? int(param3) : 0, colorTexture1);
		var loc8:BitmapData = getTexture(param4 >= 0 ? int(param4) : 0, colorTexture2);
		Shader loc9 = new Shader(textureShaderData_);
		loc9.data.src.input = loc6;
		loc9.data.mask.input = param2;
		loc9.data.texture1.input = loc7;
		loc9.data.texture2.input = loc8;
		loc9.data.texture1Size.value = [param3 == 0 ? 0 : loc7.width];
		loc9.data.texture2Size.value = [param4 == 0 ? 0 : loc8.width];
		loc6.applyFilter(loc6, loc6.rect, PointUtil.ORIGIN, new ShaderFilter(loc9));
		return loc6;
	}

	private static Matrix  getDrawMatrix()  {
		Matrix loc1 = new Matrix();
		loc1.scale(8, 8);
		loc1.translate(BORDER, BORDER);
		return loc1;
	}



}
