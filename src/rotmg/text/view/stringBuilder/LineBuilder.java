package rotmg.text.view.stringBuilder;

import rotmg.language.model.StringMap;

/**
 * Not fully implemented (about 15%)
 */
public class LineBuilder implements StringBuilder {

	public String key;

	public Object tokens;

	private String postfix = "";

	private String prefix = "";

	private StringMap map;

	public LineBuilder() {
		super();
	}

	@Override
	public void setStringMap(StringMap param1) {

	}

	@Override
	public String getString() {
		return null;
	}

	public StringBuilder setParams(String params) {
		this.params = params;
		return null;
	}
}
