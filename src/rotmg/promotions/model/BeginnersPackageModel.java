package rotmg.promotions.model;

import flash.utils.Date;
import rotmg.account.core.Account;
import rotmg.account.core.model.OfferModel;
import rotmg.util.Offer;
import rotmg.util.TimeUtil;

public class BeginnersPackageModel {

	private static BeginnersPackageModel instance;

	public static BeginnersPackageModel getInstance() {
		if (instance == null) {
			instance = new BeginnersPackageModel();
		}
		return instance;
	}

	private static final int REALM_GOLD_FOR_BEGINNERS_PKG = 2600;

	private static final int ONE_WEEK_IN_SECONDS = 604800;

	public Account account;

	public OfferModel model;

	public Signal markedAsPurchased;

	private double beginnersOfferSecondsLeft;

	private double beginnersOfferSetTimestamp;

	public BeginnersPackageModel() {
		super();
		this.markedAsPurchased = new Signal();
	}

	public boolean isBeginnerAvailable() {
		return this.getBeginnersOfferSecondsLeft() > 0;
	}

	public void setBeginnersOfferSecondsLeft(double param1) {
		this.beginnersOfferSecondsLeft = param1;
		this.beginnersOfferSetTimestamp = this.getNowTimeSeconds();
	}

	private double getNowTimeSeconds() {
		Date loc1 = new Date();
		return Math.round(loc1.time * 0.001);
	}

	public double getBeginnersOfferSecondsLeft() {
		return this.beginnersOfferSecondsLeft - (this.getNowTimeSeconds() - this.beginnersOfferSetTimestamp);
	}

	public double getUserCreatedAt() {
		return this.getNowTimeSeconds() + this.getBeginnersOfferSecondsLeft() - ONE_WEEK_IN_SECONDS;
	}

	public double getDaysRemaining() {
		return Math.ceil(TimeUtil.secondsToDays(this.getBeginnersOfferSecondsLeft()));
	}

	public Offer getOffer() {
		if (this.model.offers == null) {
			return null;
		}
		for (Offer loc1 : this.model.offers.offerList) {
			if (loc1.realmGold == REALM_GOLD_FOR_BEGINNERS_PKG) {
				return loc1;
			}
		}
		return null;
	}

	public void markAsPurchased() {
		this.setBeginnersOfferSecondsLeft(-1);
		this.markedAsPurchased.dispatch();
	}


}
