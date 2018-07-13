package rotmg.offer;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import rotmg.util.Offer;

public class Offers {

	private static final String BEST_DEAL = "(Best deal)";

	private static final String MOST_POPULAR = "(Most popular)";

	public String tok;

	public String exp;

	public Vector<Offer> offerList;

	public Offers(XML param1) {
		super();
		this.tok = param1.getValue("Tok");
		this.exp = param1.getValue("Exp");
		this.makeOffers(param1);
	}

	private void makeOffers(XML param1) {
		this.makeOfferList(param1);
		this.sortOfferList();
		this.defineBonuses();
		this.defineMostPopularTagline();
		this.defineBestDealTagline();
	}

	private void makeOfferList(XML param1) {
		this.offerList = new Vector<Offer>(0);
		for (XML loc2 : param1.children("Offer")) {
			this.offerList.add(this.makeOffer(loc2));
		}
	}

	private Offer makeOffer(XML param1) {
		String loc2 = param1.getValue("Id");
		double loc3 = param1.getDoubleValue("Price");
		int loc4 = param1.getIntValue("RealmGold");
		String loc5 = param1.getValue("CheckoutJWT");
		String loc6 = param1.getValue("Data");
		String loc7 = param1.hasOwnProperty("Currency") ? param1.getValue("Currency") : null;
		return new Offer(loc2, (int) loc3, loc4, loc5, loc6, loc7);
	}

	private void sortOfferList() {
		//this.offerList.sort(this.sortOffers);
	}

	private void defineBonuses() {
		int loc5 = 0;
		int loc6 = 0;
		double loc7 = 0;
		double loc8 = 0;
		if (this.offerList.length == 0) {
			return;
		}
		int loc1 = this.offerList.get(0).realmGold;
		int loc2 = this.offerList.get(0).price;
		double loc3 = loc1 / loc2;
		int loc4 = 1;
		while (loc4 < this.offerList.length) {
			loc5 = this.offerList.get(loc4).realmGold;
			loc6 = this.offerList.get(loc4).price;
			loc7 = loc6 * loc3;
			loc8 = loc5 - loc7;
			this.offerList.get(loc4).bonus = (int) (loc8 / loc6);
			loc4++;
		}
	}

	private int sortOffers(Offer param1, Offer param2) {
		return param1.price - param2.price;
	}

	private void defineMostPopularTagline() {
		for (Offer loc1 : this.offerList) {
			if (loc1.price == 10) {
				loc1.tagline = MOST_POPULAR;
			}
		}
	}

	private void defineBestDealTagline() {
		this.offerList.get(this.offerList.length - 1).tagline = BEST_DEAL;
	}

}
