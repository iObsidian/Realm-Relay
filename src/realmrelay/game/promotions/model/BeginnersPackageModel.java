package realmrelay.game.promotions.model;

import realmrelay.game._as3.Signal;
import realmrelay.game.account.core.Account;

public class BeginnersPackageModel {

	/*private static final int REALM_GOLD_FOR_BEGINNERS_PKG = 2600;

	private static final int ONE_WEEK_IN_SECONDS = 604800;

    [Inject]
	public Account account;

    [Inject]
	public OfferModel model;

	public Signal markedAsPurchased;

	private double beginnersOfferSecondsLeft;

	private double beginnersOfferSetTimestamp;

	public BeginnersPackageModel() {
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
		Offer loc1 = null;
		if (!this.model.offers) {
			return null;
		}
		for (loc1 in this.model.offers.offerList) {
			if (loc1.realmGold == REALM_GOLD_FOR_BEGINNERS_PKG) {
				return loc1;
			}
		}
		return null;
	}

	public void markAsPurchased() {
		this.setBeginnersOfferSecondsLeft(-1);
		this.markedAsPurchased.dispatch();
	}**/


}
