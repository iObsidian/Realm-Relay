package rotmg.text.view.stringBuilder;

import rotmg.language.model.StringMap;

public class TemplateBuilder implements StringBuilder {

	private String template;

	private Object tokens;

	private String postfix = "";

	private String prefix = "";

	private StringMap provider;

	public TemplateBuilder() {
		super();
	}

	public TemplateBuilder setTemplate(String param1) {
		return this.setTemplate(param1, null);
	}

	public TemplateBuilder setTemplate(String param1, Object param2) {
		this.template = param1;
		this.tokens = param2;
		return this;
	}

	public TemplateBuilder setPrefix(String param1) {
		this.prefix = param1;
		return this;
	}

	public TemplateBuilder setPostfix(String param1) {
		this.postfix = param1;
		return this;
	}

	public void setStringMap(StringMap param1) {
		this.provider = param1;
	}

	public String getString() {

		return "this is not yet implemented";
		/*
		var loc2:* = null;
		var loc3:String = null;
		var loc1:String = this.template;
		for(loc2 in this.tokens) {
			loc3 = this.tokens[loc2];
			if(loc3.charAt(0) == "{" && loc3.charAt(loc3.length - 1) == "}") {
				loc3 = this.provider.getValue(loc3.substr(1,loc3.length - 2));
			}
			loc1 = loc1.replace("{" + loc2 + "}",loc3);
		}
		loc1 = loc1.replace(/\\n/g,"\n");
		return this.prefix + loc1 + this.postfix;
	}*/
	}

}
