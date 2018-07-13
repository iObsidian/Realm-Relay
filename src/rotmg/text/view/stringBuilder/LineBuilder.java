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
	private String params;

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

	public LineBuilder setParams(String params) {
		this.params = params;
		return this;
	}

	public LineBuilder setParams(String s, String s1) {
		this.key = key;
		this.params = params;
		return this;
	}

	public LineBuilder setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public LineBuilder setPostfix(String postfix) {
		this.postfix = postfix;
		return this;
	}

}
