package kabam.rotmg.objects.animation;

import alde.flash.utils.XML;

import java.util.ArrayList;
import java.util.List;

public class AnimationsData {

	public List<AnimationData> animations;

	public AnimationsData(XML xml) {
		this.animations = new ArrayList<AnimationData>();
		for (XML animData : xml.childs("Animation")) {
			this.animations.add(new AnimationData(animData));
		}
	}

}
