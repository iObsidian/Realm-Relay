package rotmg.account.core.model;

import rotmg.offer.Offers;

public class OfferModel {

	public static final int TIME_BETWEEN_REQS = 5 * 60 * 1000;

	public int lastOfferRequestTime;

	public String lastOfferRequestGUID;

	public Offers offers;

	public OfferModel() {
		super();
	}

}
