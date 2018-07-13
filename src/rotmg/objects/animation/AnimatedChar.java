package rotmg.objects.animation;

import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.geom.Point;
import flash.geom.Rectangle;
import flash.utils.Dictionary;
import rotmg.map.Camera;
import rotmg.util.MaskedImage;
import rotmg.util.MaskedImageSet;


/**
 * This matches the client by 90%, only the Transparency-check is not suported and mirroring is untested
 */
public class AnimatedChar {

	public static final int RIGHT = 0;

	public static final int LEFT = 1;

	public static final int DOWN = 2;

	public static final int UP = 3;

	public static final int NUM_DIR = 4;

	public static final int STAND = 0;

	public static final int WALK = 1;

	public static final int ATTACK = 2;
	private static final int SEC_TO_DIRS[][] = {{LEFT, UP, DOWN}, {UP, LEFT, DOWN}, {UP, RIGHT, DOWN},
			{RIGHT, UP, DOWN}, {RIGHT, DOWN}, {DOWN, RIGHT}, {DOWN, LEFT}, {LEFT, DOWN}};
	public static int NUM_ACTION = 3;
	private static double PIOVER4 = (double) Math.PI / 4;

	public MaskedImage origImage;
	private int width;
	private int height;
	private int firstDir;
	private Dictionary<Integer, Dictionary<Integer, Vector<MaskedImage>>> dict;

	public AnimatedChar(MaskedImage image, int width, int height, int firstDir) {
		this.dict = new Dictionary<>();
		this.origImage = image;
		this.width = width;
		this.height = height;
		this.firstDir = firstDir;
		MaskedImageSet frames = new MaskedImageSet();
		frames.addFromMaskedImage(image, width, height);

		if (firstDir == RIGHT) {
			this.dict.put(RIGHT, this.loadDir(0, false, false, frames));
			this.dict.put(LEFT, this.loadDir(0, true, false, frames));
			if (frames.images.size() >= 14) {
				this.dict.put(DOWN, this.loadDir(7, false, true, frames));
				if (frames.images.size() >= 21) {
					this.dict.put(UP, this.loadDir(14, false, true, frames));
				}
			}
		} else if (firstDir == DOWN) {
			this.dict.put(DOWN, this.loadDir(0, false, true, frames));
			if (frames.images.size() >= 14) {
				this.dict.put(RIGHT, this.loadDir(7, false, false, frames));
				this.dict.put(LEFT, this.loadDir(7, true, false, frames));
				if (frames.images.size() >= 21) {
					this.dict.put(UP, this.loadDir(14, false, true, frames));
				}
			}
		} else {
			System.err.println("ERROR: unsupported first dir: " + firstDir);
		}
	}

	private Dictionary<Integer, Vector<MaskedImage>> loadDir(int offset, boolean mirror, boolean sym,
	                                                         MaskedImageSet frames) {
		Vector<MaskedImage> attackVec = null;
		BitmapData image = null;
		BitmapData mask = null;
		Dictionary<Integer, Vector<MaskedImage>> dirDict = new Dictionary<>();
		MaskedImage standImage = frames.images.get(offset + 0);
		MaskedImage walk1Image = frames.images.get(offset + 1);
		MaskedImage walk2Image = frames.images.get(offset + 2);
		/*if(walk2Image.amountTransparent() == 1)
		{
		    walk2Image = null;
		}*/
		MaskedImage attack1Image = frames.images.get(offset + 4);
		MaskedImage attack2Image = frames.images.get(offset + 5);
		/*if(attack1Image.amountTransparent() == 1)
		{
		    attack1Image = null;
		}*/
		/*if(attack2Image.amountTransparent() == 1)
		{
		    attack2Image = null;
		}*/
		MaskedImage swordBitImage = frames.images.get(offset + 6);
		if (attack2Image != null /*&& swordBitImage.amountTransparent() != 1*/) {
			image = new BitmapData(this.width * 3, this.height, true, 0);
			image.copyPixels(attack2Image.image, new Rectangle(0, 0, this.width, this.height),
					new Point(this.width, 0));
			image.copyPixels(swordBitImage.image, new Rectangle(0, 0, this.width, this.height),
					new Point(this.width * 2, 0));
			mask = null;
			if (attack2Image.mask != null || swordBitImage.mask != null) {
				mask = new BitmapData(this.width * 3, this.height, true, 0);
			}
			if (attack2Image.mask != null) {
				mask.copyPixels(attack2Image.mask, new Rectangle(0, 0, this.width, this.height),
						new Point(this.width, 0));
			}
			if (swordBitImage.mask != null) {
				mask.copyPixels(swordBitImage.mask, new Rectangle(0, 0, this.width, this.height),
						new Point(this.width * 2, 0));
			}
			attack2Image = new MaskedImage(image, mask);
		}
		Vector<MaskedImage> standVec = new Vector<MaskedImage>();
		standVec.add(!!mirror ? standImage.mirror() : standImage);
		dirDict.put(STAND, standVec);
		Vector<MaskedImage> walkVec = new Vector<MaskedImage>();
		walkVec.add(!!mirror ? walk1Image.mirror() : walk1Image);
		if (walk2Image != null) {
			walkVec.add(!!mirror ? walk2Image.mirror() : walk2Image);
		} else if (sym) {
			walkVec.add(!mirror ? walk1Image.mirror(7) : walk1Image);
		} else {
			walkVec.add(!!mirror ? standImage.mirror() : standImage);
		}
		dirDict.put(WALK, walkVec);
		if (attack1Image == null && attack2Image == null) {
			attackVec = walkVec;
		} else {
			attackVec = new Vector<MaskedImage>();
			if (attack1Image != null) {
				attackVec.add(!!mirror ? attack1Image.mirror() : attack1Image);
			}
			if (attack2Image != null) {
				attackVec.add(!!mirror ? attack2Image.mirror() : attack2Image);
			}
		}
		dirDict.put(ATTACK, attackVec);
		return dirDict;
	}

	public MaskedImage imageFromDir(int angle, int action, double p) {
		Vector<MaskedImage> loc4 = this.dict.get(angle).get(action);
		p = Math.max(0, Math.min(0.99999, p));
		int loc5 = (int) (p * loc4.length);

		/*System.out.println("Loc4 size : "+loc4.length);
		System.out.println("p = "+p);
		System.out.println("Loc5 = "+loc5);

		System.out.println(loc4.get(loc5));*/

		return loc4.get(loc5);
	}

	public MaskedImage imageFromAngle(double param1, int param2, double param3) {
		int loc4 = (int) ((param1 / PIOVER4 + 4) % 8);
		int[] loc5 = SEC_TO_DIRS[loc4];
		Dictionary<Integer, Vector<MaskedImage>> loc6 = this.dict.get(loc5[0]);
		if (loc6 == null) {
			loc6 = this.dict.get(loc5[1]);
			if (loc6 == null) {
				loc6 = this.dict.get(loc5[2]);
			}
		}
		Vector<MaskedImage> loc7 = loc6.get(param2);
		param3 = Math.max(0, Math.min(0.99999, param3));
		int loc8 = (int) (param3 * loc7.length);
		return loc7.get(loc8);
	}

	public int getHeight() {
		return this.height;
	}

	public MaskedImage imageFromFacing(double facing, Camera camera, int action, double p) {
		return null;
	}
}
