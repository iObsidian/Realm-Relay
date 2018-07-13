package rotmg.ui;

import alde.flash.utils.SignalConsumer;
import org.osflash.signals.Signal;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.StaticStringBuilder;

public class DeprecatedTextButton extends TextButtonBase {

	public final Signal textChanged = new Signal();

	public DeprecatedTextButton(int param1, String param2) {
		this(param1, param2, 0);
	}

	public DeprecatedTextButton(int param1, String param2, int param3) {
		this(param1, param2, param3, false);
	}

	public DeprecatedTextButton(int param1, String param2, int param3, boolean param4) {
		super(param3);
		addText(param1);
		if (param4) {
			text.setStringBuilder(new StaticStringBuilder(param2));
		} else {
			text.setStringBuilder(new LineBuilder().setParams(param2));
		}
		text.textChanged.addOnce(new SignalConsumer<>(this::onTextChanged));
	}

	protected void onTextChanged() {
		initText();
		this.textChanged.dispatch();
	}
}

