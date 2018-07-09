package rotmg.ui;

import alde.flash.utils.SignalConsumer;
import flash.display.Sprite;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.view.SignalWaiter;
import rotmg.util.TextKey;

/**
 * Not fully implemented
 */
public class RankText extends Sprite {

	public Sprite background = null;

	public boolean largeText;

	private int numStars = -1;

	private TextFieldDisplayConcrete prefix = null;

	private SignalWaiter waiter;

	private Sprite icon;

	public RankText(int param1, boolean param2, boolean param3) {
		super();
		this.waiter = new SignalWaiter();
		this.largeText = param2;
		if (param3) {
			this.prefix = this.makeText();
			this.prefix.setStringBuilder(new LineBuilder().setParams(TextKey.RANK_TEXT_RANK));
			//this.prefix.filters = [new DropShadowFilter(0, 0, 0)];
			this.prefix.textChanged.addOnce(new SignalConsumer<>(this::position));
			addChild(this.prefix);
		}
		mouseEnabled = false;
		mouseChildren = false;
		this.draw(param1);
	}

	public TextFieldDisplayConcrete makeText() {
		return null;
	}

	public void draw(int param1) {
		return;
	}

	private void positionWhenTextIsReady() {
		if (this.waiter.isEmpty()) {
			this.position();
		} else {
			this.waiter.complete.addOnce(new SignalConsumer<>(this::position));
		}
	}

	private void position() {
		if (this.prefix == null) {
			this.background.x = this.prefix.width;
			this.prefix.y = this.icon.y - 3;
		}
	}
}