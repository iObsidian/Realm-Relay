package rotmg.ui;

import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;

public class DeprecatedClickableText extends ClickableTextBase {

	public DeprecatedClickableText(int param1, boolean param2, String param3) {
		super(param1, param2, param3);
	}

	protected TextFieldDisplayConcrete makeText() {
		return new TextFieldDisplayConcrete();
	}


}
