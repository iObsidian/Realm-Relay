package rotmg.model;

public class UseBuyPotionVO {

	public static String SHIFTCLICK = "shift_click";

	public static String CONTEXTBUY = "context_buy";

	public int objectId;

	public String source;

	public UseBuyPotionVO(int param1, String param2) {
		super();
		this.objectId = param1;
		this.source = param2;
	}


}
