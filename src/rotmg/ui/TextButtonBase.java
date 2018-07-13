package rotmg.ui;

import alde.flash.utils.EventConsumer;
import flash.events.MouseEvent;
import rotmg.text.view.stringBuilder.LineBuilder;

public class TextButtonBase extends BackgroundFilledText {

	public TextButtonBase(int param1) {
		super(param1);
	}

	protected void initText() {
		centerTextAndDrawButton();
		this.draw();
		addEventListener(MouseEvent.MOUSE_OVER, new EventConsumer<>(this::onMouseOver));
		addEventListener(MouseEvent.ROLL_OUT, new EventConsumer<>(this::onRollOut));
	}

	public void setText(String param1) {
		text.setStringBuilder(new LineBuilder().setParams(param1));
	}

	public void setEnabled(boolean param1) {
		if (param1 == mouseEnabled) {
			return;
		}
		mouseEnabled = param1;
		graphicsData.put(0, !!param1 ? enabledFill : disabledFill);
		this.draw();
	}

	private void onMouseOver(MouseEvent param1) {
		enabledFill.color = 16768133;
		this.draw();
	}

	private void onRollOut(MouseEvent param1) {
		enabledFill.color = 16777215;
		this.draw();
	}

	private void draw() {
		graphics.clear();
		graphics.drawGraphicsData(graphicsData);
	}
}
