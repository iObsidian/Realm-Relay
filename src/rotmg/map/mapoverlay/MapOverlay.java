package rotmg.map.mapoverlay;

import alde.flash.utils.Vector;
import flash.display.Sprite;
import rotmg.map.Camera;
import rotmg.view.components.QueuedStatusText;
import rotmg.view.components.QueuedStatusTextList;

public class MapOverlay extends Sprite {

	private final Vector<SpeechBalloon> speechBalloons = new Vector<>();

	private final Vector queuedText = new Vector<>();

	public MapOverlay() {
		super();
		mouseEnabled = true;
		mouseChildren = true;
	}

	public void addSpeechBalloon(SpeechBalloon param1) {
		int loc2 = param1.go.objectId;
		SpeechBalloon loc3 = this.speechBalloons.get(loc2);
		if (loc3 && contains(loc3)) {
			removeChild(loc3);
		}
		this.speechBalloons.put(loc2, param1);
		addChild(param1);
	}

	public void addStatusText(CharacterStatusText param1) {
		addChild(param1);
	}

	public void addQueuedText(QueuedStatusText param1) {
		int loc2 = param1.go.objectId;
		QueuedStatusTextList loc3 = this.queuedText[loc2] = this.queuedText[loc2] || this.makeQueuedStatusTextList();
		loc3.append(param1);
	}

	private QueuedStatusTextList makeQueuedStatusTextList() {
		QueuedStatusTextList loc1 = new QueuedStatusTextList();
		loc1.target = this;
		return loc1;
	}

	public void draw(Camera param1, int param2) {
		IMapOverlayElement loc4 = null;
		int loc3 = 0;
		while (loc3 < numChildren) {
			loc4 = getChildAt(loc3) (IMapOverlayElement);
			if (!loc4 || loc4.draw(param1, param2)) {
				loc3++;
			} else {
				loc4.dispose();
			}
		}
	}


}
