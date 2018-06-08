package rotmg.objects.animation;

import alde.flash.utils.XML;

import java.util.ArrayList;
import java.util.List;

public class AnimationsData {

	public AnimationsData(XML xml) {
		this.animations = new ArrayList<AnimationData>();
		for (XML animData : xml.getChilds("Animation")) {
			this.animations.add(new AnimationData(animData));
		}
	}

	public List<AnimationData> animations;

}
