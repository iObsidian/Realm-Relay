package realmrelay.game.objects;

import realmrelay.game.XML;
import realmrelay.packets.data.unused.BitmapData;

import java.awt.*;

public class SellableObject extends GameObject implements IInteractiveObject {


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

	public ToolTip getTooltip() {
		return null;
	}

	public BitmapData getIcon() {
		return null;
	}

	public Panel getPanel(GameSprite gs) {
		return new SellableObjectPanel(gs, this);
	}


}
