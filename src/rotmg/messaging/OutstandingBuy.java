package rotmg.messaging;

public class OutstandingBuy {

	private String id;
	private int price;
	private int currency;
	private boolean converted;

	OutstandingBuy(String id, int price, int currency, boolean converted) {
		super();
		this.id = id;
		this.price = price;
		this.currency = currency;
		this.converted = converted;
	}

	public void record() {
	}

}
