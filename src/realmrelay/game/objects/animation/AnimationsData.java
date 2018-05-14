package realmrelay.game.objects.animation;

import java.util.ArrayList;
import java.util.List;

import realmrelay.game._as3.XML;

public class AnimationsData {

    public AnimationsData(XML xml) {
        this.animations = new ArrayList<AnimationData>();
        for (XML animData : xml.getChilds("Animation")) {
            this.animations.add(new AnimationData(animData));
        }
    }

    public List<AnimationData> animations;

}
