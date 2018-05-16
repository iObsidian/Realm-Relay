package realmrelay.game.objects.animation;

import realmrelay.game.util.MaskedImage;
import realmrelay.game.util.MaskedImageSet;
import realmrelay.game._as3.BitmapData;

import java.awt.*;
import java.util.*;
import java.util.List;

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

	public static int NUM_ACTION = 3;

	private static final int SEC_TO_DIRS[][] = { { LEFT, UP, DOWN }, { UP, LEFT, DOWN }, { UP, RIGHT, DOWN },
			{ RIGHT, UP, DOWN }, { RIGHT, DOWN }, { DOWN, RIGHT }, { DOWN, LEFT }, { LEFT, DOWN } };

	private static double PIOVER4 = (double) Math.PI / 4;

	public MaskedImage origImage;
	private int width;
	private int height;
	private int firstDir;
	private Map<Integer, HashMap<Integer, List<MaskedImage>>> dict;

	public AnimatedChar(MaskedImage image, int width, int height, int firstDir) {
		this.dict = new HashMap<>();
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

	private HashMap<Integer, List<MaskedImage>> loadDir(int offset, boolean mirror, boolean sym,
			MaskedImageSet frames) {
		List<MaskedImage> attackVec = null;
		BitmapData image = null;
		BitmapData mask = null;
		HashMap<Integer, List<MaskedImage>> dirDict = new HashMap<>();
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
		List<MaskedImage> standVec = new ArrayList<MaskedImage>();
		standVec.add(!!mirror ? standImage.mirror() : standImage);
		dirDict.put(STAND, standVec);
		List<MaskedImage> walkVec = new ArrayList<MaskedImage>();
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
			attackVec = new ArrayList<MaskedImage>();
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

	public MaskedImage imageFromDir(double angle, int action, double p) {
		int sec = (int) (angle / PIOVER4 + 4 % 8);
		int[] dirs = SEC_TO_DIRS[sec];
		HashMap<Integer, List<MaskedImage>> actionDict = this.dict.get(dirs[0]);
		if (actionDict == null) {
			actionDict = this.dict.get(dirs[1]);
			if (actionDict == null) {
				actionDict = this.dict.get(dirs[2]);
			}
		}
		List<MaskedImage> texVec = actionDict.get(action);
		p = (double) Math.max(0, Math.min(0.99999, p));
		int i = (int) (p * texVec.size());
		return texVec.get(i);
	}

	public int getHeight() {
		return this.height;
	}

}
