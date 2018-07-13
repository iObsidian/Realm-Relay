package rotmg.util;

import alde.flash.utils.EventConsumer;
import flash.display.DisplayObject;
import flash.display.DisplayObjectContainer;
import flash.display.InteractiveObject;
import flash.events.Event;
import flash.events.IEventDispatcher;

public class StageProxy implements IEventDispatcher {

	private static Stage3DProxy stage3D = null;

	protected DisplayObject reference;

	public StageProxy(DisplayObject param1) {
		super();
		this.reference = param1;
	}

	public DisplayObjectContainer getStage() {
		return this.reference.stage;
	}

	public double getStageWidth() {
		if (this.reference.stage != null) {
			return this.reference.stage.stageWidth;
		}
		return 800;
	}

	public double getStageHeight() {
		if (this.reference.stage != null) {
			return this.reference.stage.stageHeight;
		}
		return 600;
	}

	public InteractiveObject getFocus() {
		return this.reference.stage.focus;
	}

	public void setFocus(InteractiveObject param1) {
		this.reference.stage.focus = param1;
	}

	public void addEventListener(String param1, EventConsumer param2, boolean param3, int param4, boolean param5) {
		this.reference.stage.addEventListener(param1, param2, param3, param4, param5);
	}

	public void addEventListener(String param1, EventConsumer param2) {
		this.reference.stage.addEventListener(param1, param2, false, 0, false);
	}

	public void removeEventListener(String param1, EventConsumer param2, boolean param3) {
		this.reference.stage.removeEventListener(param1, param2, param3);
	}

	public void removeEventListener(String param1, EventConsumer param2) {
		this.reference.stage.removeEventListener(param1, param2, false);
	}

	public boolean dispatchEvent(Event param1) {
		return this.reference.stage.dispatchEvent(param1);
	}

	public boolean hasEventListener(String param1) {
		return this.reference.stage.hasEventListener(param1);
	}

	public boolean willTrigger(String param1) {
		return this.reference.stage.willTrigger(param1);
	}

	public String getQuality() {
		return this.reference.stage.quality;
	}

	public void setQuality(String param1) {
		this.reference.stage.quality = param1;
	}

	public Stage3DProxy getStage3Ds(int param1) {
		if (stage3D == null) {
			stage3D = new Stage3DProxy(this.reference.stage.stage3Ds[param1]);
		}
		return stage3D;
	}
}
