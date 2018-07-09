package rotmg.objects.animation;

import flash.display.BitmapData;

import java.util.ArrayList;
import java.util.List;

public class Animations {

	public AnimationsData animationsData;

	public List<Integer> nextRun = null;

	public RunningAnimation running = null;

	public Animations(AnimationsData animationsData) {
		super();
		this.animationsData = animationsData;
	}

	public BitmapData getTexture(int time) {
		AnimationData animationData;

		BitmapData texture = null;
		int start = 0;
		if (this.nextRun == null) {
			this.nextRun = new ArrayList<Integer>();
			for (AnimationData loc2 : this.animationsData.animations) {
				this.nextRun.add(loc2.getLastRun(time));
			}
		}
		if (this.running != null) {
			texture = this.running.getTexture(time);
			if (texture != null) {
				return texture;
			}
			this.running = null;
		}
		for (int i = 0; i < this.nextRun.size(); i++) {
			if (time > this.nextRun.get(i)) {
				start = this.nextRun.get(i);
				animationData = this.animationsData.animations.get(i);
				this.nextRun.set(i, animationData.getNextRun(time));
				if (!(animationData.prob != 1 && Math.random() > animationData.prob)) {
					this.running = new RunningAnimation(animationData, start);
					return this.running.getTexture(time);
				}
			}
		}
		return null;
	}
}

class RunningAnimation {

	public AnimationData animationData;

	public int start;

	public int frameId;

	public int frameStart;

	public BitmapData texture;

	RunningAnimation(AnimationData animationData, int start) {
		this.animationData = animationData;
		this.start = start;
		this.frameId = 0;
		this.frameStart = start;
		this.texture = null;
	}

	public BitmapData getTexture(int time) {
		FrameData frame = this.animationData.frames.get(this.frameId);
		while (time - this.frameStart > frame.time) {
			if (this.frameId >= this.animationData.frames.size() - 1) {
				return null;
			}
			this.frameStart = this.frameStart + frame.time;
			this.frameId++;
			frame = this.animationData.frames.get(this.frameId);
			this.texture = null;
		}
		if (this.texture == null) {
			this.texture = frame.textureData.getTexture((int) (Math.random() * 100));
		}
		return this.texture;
	}
}
