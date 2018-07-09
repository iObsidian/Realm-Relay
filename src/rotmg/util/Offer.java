package rotmg.util;

public class Offer {

	public String id;

	public int price;

	public int realmGold;

	public String jwt;

	public String data;

	public String currency;

	public String tagline;

	public int bonus;

	public Offer(String param1, int param2, int param3, String param4, String param5, String param6) {
		super();
		this.id = param1;
		this.price = param2;
		this.realmGold = param3;
		this.jwt = param4;
		this.data = param5;
		this.currency = param6 != null ? param6 : "USD";
	}
}
