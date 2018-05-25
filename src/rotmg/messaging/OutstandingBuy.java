package rotmg.messaging;

public class OutstandingBuy {

    OutstandingBuy(String id, int price, int currency, boolean converted) {
        super();
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.converted = converted;
    }

    private String id;
    private int price;
    private int currency;
    private boolean converted;

    public void record() {
    }

}
