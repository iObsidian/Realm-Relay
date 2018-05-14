package realmrelay.game.objects.animation;

import java.util.ArrayList;
import java.util.List;

import realmrelay.game._as3.XML;

/**
 * This is a 100% match
 */
public class AnimationData {

	public AnimationData(XML xml) {
		this.frames = new ArrayList<>();

		if (xml.hasOwnProperty("prob")) {
			this.prob = xml.getIntAttribute("prob");
		}

		this.period = (int) (xml.getIntAttribute("period") * 1000);
		
		this.periodJitter = (int) (xml.getFloatAttribute("periodJitter") * 1000);
		
		this.sync = xml.getAttribute("sync").equalsIgnoreCase("true");
		for (XML frameXML : xml.getChilds("Frame")) {
			this.frames.add(new FrameData(frameXML));
		}
	}

	public float prob = 1.0F;

	public int period;

	public int periodJitter;

	public boolean sync = false;

	public List<FrameData> frames;

	private int getPeriod() {
		if (this.periodJitter == 0) {
			return this.period;
		}
		return (int) (this.period - this.periodJitter + 2 * Math.random() * this.periodJitter);
	}

	public int getLastRun(int time) {
		if (this.sync) {
			return (int) time / this.period * this.period;
		}
		return (int) (time + this.getPeriod() + 200 * Math.random());
	}

	public int getNextRun(int time) {
		if (this.sync) {
			return time / this.period * this.period + this.period;
		}
		return time + this.getPeriod();
	}

}
