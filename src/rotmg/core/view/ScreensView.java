package rotmg.core.view;

import flash.display.Sprite;

public class ScreensView extends Sprite {

	private Sprite current;

	private Sprite previous;

	public ScreensView() {
		super();
	}

	public void setScreen(Sprite param1) {
		if (this.current == param1) {
			return;
		}
		this.removePrevious();
		this.current = param1;
		addChild(param1);
	}

	private void removePrevious() {
		if (this.current != null && contains(this.current)) {
			this.previous = this.current;
			removeChild(this.current);
		}
	}

	public Sprite getPrevious() {
		return this.previous;
	}


}
