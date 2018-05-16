package realmrelay.game.objects;

import realmrelay.game._as3.XML;

public class SellableObject extends GameObject {


	public SellableObject(XML objectXML) {
		super(objectXML);
		isInteractive = true;
	}

	public int price = 0;

	public int currency = -1;

	public int rankReq = 0;

	public int guildRankReq = -1;

	public void setPrice(int price) {
		this.price = price;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public void setRankReq(int rankReq) {
		this.rankReq = rankReq;
	}

	public String soldObjectName() {
		return null;
	}

	public String soldObjectInternalName() {
		return null;
	}


}
