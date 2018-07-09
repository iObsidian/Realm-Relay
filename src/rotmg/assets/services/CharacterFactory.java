package rotmg.assets.services;

import flash.display.BitmapData;
import rotmg.assets.model.Animation;
import rotmg.assets.model.CharacterTemplate;
import rotmg.objects.animation.AnimatedChar;
import rotmg.util.AnimatedChars;
import rotmg.util.BitmapUtil;
import rotmg.util.MaskedImage;
import rotmg.util.TextureRedrawer;
import rotmg.util.redrawers.GlowRedrawer;

public class CharacterFactory {

	public static CharacterFactory instance;
	private int texture1;
	private int texture2;
	private int size;

	public CharacterFactory() {
		super();
	}

	public static CharacterFactory getInstance() {
		if (instance == null) {
			instance = new CharacterFactory();
		}
		return instance;
	}

	public AnimatedChar makeCharacter(CharacterTemplate param1) {
		return AnimatedChars.getAnimatedChar(param1.file, param1.index);
	}

	/**
	 * Utility method for optional params
	 */
	public BitmapData makeIcon(CharacterTemplate characterTemplate) {
		return this.makeIcon(characterTemplate, 100, 0, 0);
	}

	public BitmapData makeIcon(CharacterTemplate param1, int param2, int param3, int param4) {
		return this.makeIcon(param1, param2, param3, param4, false);
	}

	public BitmapData makeIcon(CharacterTemplate param1, int param2, int param3, int param4, boolean param5) {
		this.texture1 = param3;
		this.texture2 = param4;
		this.size = param2;
		AnimatedChar loc6 = this.makeCharacter(param1);
		BitmapData loc7 = this.makeFrame(loc6, AnimatedChar.STAND, 0);
		loc7 = GlowRedrawer.outlineGlow(loc7, !!param5 ? 16711680 : 0);
		loc7 = BitmapUtil.cropToBitmapData(loc7, 6, 6, loc7.width - 12, loc7.height - 6);
		return loc7;
	}

	/**
	 * Utility method for optional params
	 */
	public Animation makeWalkingIcon(CharacterTemplate param1) {
		return this.makeWalkingIcon(param1, 100, 0, 0);
	}

	public Animation makeWalkingIcon(CharacterTemplate param1, int param2, int param3, int param4) {
		this.texture1 = param3;
		this.texture2 = param4;
		this.size = param2;
		AnimatedChar loc5 = this.makeCharacter(param1);
		BitmapData loc6 = this.makeFrame(loc5, AnimatedChar.WALK, 0.5F);
		loc6 = GlowRedrawer.outlineGlow(loc6, 0);
		BitmapData loc7 = this.makeFrame(loc5, AnimatedChar.WALK, 0);
		loc7 = GlowRedrawer.outlineGlow(loc7, 0);
		Animation loc8 = new Animation();
		loc8.setFrames(loc6, loc7);


		return loc8;
	}

	private BitmapData makeFrame(AnimatedChar param1, int param2, double param3) {
		MaskedImage loc4 = param1.imageFromDir(AnimatedChar.RIGHT, param2, param3);
		return TextureRedrawer.resize(loc4.image, loc4.mask, this.size, false, this.texture1, this.texture2);
	}


}
