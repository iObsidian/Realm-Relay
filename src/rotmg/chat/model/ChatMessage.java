package rotmg.chat.model;

public class ChatMessage {

	public String name;

	public String text;

	public int objectId = -1;

	public int numStars = -1;

	public String recipient = "";

	public boolean isToMe;

	public boolean isWhisper;

	public Object tokens;

	public ChatMessage() {
		super();
	}

	//public static function make(param1:String, param2:String, param3:int = -1, param4:int = -1, param5:String = "", param6:Boolean = false, param7:Object = null, param8:Boolean = false) : ChatMessage {
	public static ChatMessage make(String name, String text) {
		return make(name, text, -1, -1, "", false, null, false);
	}

	public static ChatMessage make(String param1, String param2, int param3, int param4, String param5, boolean param6, Object param7, boolean param8) {
		ChatMessage loc9 = new ChatMessage();
		loc9.name = param1;
		loc9.text = param2;
		loc9.objectId = param3;
		loc9.numStars = param4;
		loc9.recipient = param5;
		loc9.isToMe = param6;
		loc9.isWhisper = param8;
		loc9.tokens = param7;
		return loc9;
	}


}
