package rotmg.objects.animation;

import java.util.ArrayList;
import java.util.List;

import alde.flash.utils.XML;

public class AnimationsData {

	public List<AnimationData> animations;

	public AnimationsData(XML xml) {
		this.animations = new ArrayList<AnimationData>();
		for (XML animData : xml.children("Animation")) {
			this.animations.add(new AnimationData(animData));
		}
	}

}
