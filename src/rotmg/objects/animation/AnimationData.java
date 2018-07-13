package rotmg.objects.animation;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;

/**
 * This is a 100% match
 */
public class AnimationData {

	public double prob = 1.0F;
	public int period;
	public int periodJitter;
	public boolean sync = false;
	public Vector<FrameData> frames;

	public AnimationData(XML xml) {
		this.frames = new Vector<>();

		if (xml.hasOwnProperty("prob")) {
			this.prob = xml.getIntAttribute("prob");
		}

		this.period = (int) (xml.getIntAttribute("period") * 1000);

		this.periodJitter = (int) (xml.getDoubleAttribute("periodJitter") * 1000);

		this.sync = xml.getAttribute("sync").equalsIgnoreCase("true");
		for (XML frameXML : xml.children("Frame")) {
			this.frames.add(new FrameData(frameXML));
		}
	}

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
