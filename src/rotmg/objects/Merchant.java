package rotmg.objects;

import flash.display.BitmapData;
import javafx.scene.Camera;
import rotmg.game._as3.XML;
import rotmg.game.map.Map;
import rotmg.game.util.IntPoint;
import sun.java2d.cmm.ColorTransform;

/**
 * Merchant for selleableobjects
 */
public class Merchant extends SellableObject implements IInteractiveObject {

	private static int NONE_MESSAGE = 0;

	private static int NEW_MESSAGE = 1;

	private static int MINS_LEFT_MESSAGE = 2;

	private static int ITEMS_LEFT_MESSAGE = 3;

	private static int DISCOUNT_MESSAGE = 4;

	private static double T = 1;

	/*
	 *private static const DOSE_MATRIX:Matrix = function():Matrix
	 {
	 var m:* = new Matrix();
	 m.translate(10,5);
	 return m;
	 }();
	 */

	public int merchandiseType = -1;

	public int count = -1;

	public int minsLeft = -1;

	public int discount = 0;

	public BitmapData merchandiseTexture = null;

	public int untilNextMessage = 0;

	public double alpha = 1.0F;

	private AddSpeechBalloonSignal addSpeechBalloon;

	private boolean firstUpdate = true;

	private int messageIndex = 0;

	private ColorTransform ct;

	public Merchant(XML objectXML) {
		super(objectXML);
		this.ct = new ColorTransform(1, 1, 1, 1);
		this.addSpeechBalloon = AddSpeechBalloonSignal.getInstance();
		isInteractive = true;
	}

	@Override
	public void setPrice(int price) {
		super.setPrice(price);
		this.untilNextMessage = 0;
	}

	@Override
	public void setRankReq(int rankReq) {
		super.setRankReq(rankReq);
		this.untilNextMessage = 0;
	}

	@Override
	public boolean addTo(Map map, double x, double y) {
		if (!super.addTo(map, x, y)) {
			return false;
		}
		map.merchLookup[new IntPoint(x, y)] = this;
		return true;
	}

	@Override
	public void removeFromMap() {
		IntPoint p = new IntPoint(x, y);
		if (map.merchLookup_[p] == this) {
			map.merchLookup_[p] = null;
		}
		super.removeFromMap();
	}

	public AddSpeechBalloonVO getSpeechBalloon(int message) {
		String text = null;
		int backColor = 0;
		int outlineColor = 0;
		int textColor = 0;
		switch (message) {
			case NEW_MESSAGE:
				text = "New!";
				backColor = 15132390;
				outlineColor = 16777215;
				textColor = 5931045;
				break;
			case MINS_LEFT_MESSAGE:
				if (this.minsLeft == 0) {
					text = "Going soon!";
				} else if (this.minsLeft == 1) {
					text = "Going in 1 min!";
				} else {
					text = "Going in " + this.minsLeft + " mins!";
				}
				backColor = 5973542;
				outlineColor = 16549442;
				textColor = 16549442;
				break;
			case ITEMS_LEFT_MESSAGE:
				text = this.count + " left!";
				backColor = 5973542;
				outlineColor = 16549442;
				textColor = 16549442;
				break;
			case DISCOUNT_MESSAGE:
				text = this.discount + "% off!";
				backColor = 6324275;
				outlineColor = 16777103;
				textColor = 16777103;
				break;
			default:
				return null;
		}
		return new AddSpeechBalloonVO(this, text, backColor, 1, outlineColor, 1, textColor, 6, true, false);
	}

	@Override
	public boolean update(int time, int dt) {
		GTween tween0 = null;
		GTween tween1 = null;
		super.update(time, dt);
		if (this.firstUpdate_) {
			if (this.minsLeft_ == 2147483647) {
				this.alpha = 0;
				tween0 = new GTween(this, 0.5 * T, {"alpha_":1});
				tween1 = new GTween(this, 0.5 * T, {"size_":150},{
					"ease":Sine.easeOut
				});
				tween1.nextTween = new GTween(this, 0.5 * T, {"size_":100},{
					"ease":Sine.easeIn
				});
				tween1.nextTween.paused = true;
			}
			this.firstUpdate = false;
		}
		this.untilNextMessage = this.untilNextMessage_ - dt;
		if (this.untilNextMessage_ > 0) {
			return true;
		}
		this.untilNextMessage = 5000;
		List<int> messages = new List<int>();
		if (this.minsLeft_ == 2147483647) {
			messages.add(NEW_MESSAGE);
		} else if (this.minsLeft_ >= 0 && this.minsLeft_ <= 5) {
			messages.add(MINS_LEFT_MESSAGE);
		}
		if (this.count_ >= 1 && this.count_ <= 2) {
			messages.add(ITEMS_LEFT_MESSAGE);
		}
		if (this.discount_ > 0) {
			messages.add(DISCOUNT_MESSAGE);
		}
		if (messages.length == 0) {
			return true;
		}
		this.messageIndex = ++this.messageIndex_ % messages.length;
		int message = messages[this.messageIndex_];
		this.addSpeechBalloon.dispatch(this.getSpeechBalloon(message));
		return true;
	}

	@Override
	public String soldObjectName() {
		return ObjectLibrary.typeToDisplayId.get(this.merchandiseType);
	}

	@Override
	public String soldObjectInternalName() {
		XML objectXML = ObjectLibrary.xmlLibrary[this.merchandiseType];
		return objectXML.getAttribute("id");
	}

	@Override
	public ToolTip getTooltip() {
		ToolTip toolTip = new EquipmentToolTip(this.merchandiseType_, map_.player_, -1, InventoryOwnerTypes.NPC);
		return toolTip;
	}

	@Override
	public BitmapData getIcon() {
		SimpleText tempText = null;
		BitmapData texture = ObjectLibrary.getRedrawnTextureFromType(this.merchandiseType, 80, true);
		XML eqXML = ObjectLibrary.xmlLibrary[this.merchandiseType_];
		if (eqXML.hasOwnProperty("Doses")) {
			texture = texture.clone();
			tempText = new SimpleText(12, 16777215, false, 0, 0, "Myriad Pro");
			tempText.text = String(eqXML.Doses);
			tempText.updateMetrics();
			texture.draw(tempText, DOSE_MATRIX);
		}
		return texture;
	}

	public int getTex1Id(int defaultTexId) {
		XML objXML = ObjectLibrary.xmlLibrary.get(this.merchandiseType);
		if (objXML == null) {
			return defaultTexId;
		}
		if (objXML.Activate == "Dye" && objXML.hasOwnProperty("Tex1")) {
			return objXML.getIntValue("Tex1");
		}
		return defaultTexId;
	}

	public int getTex2Id(int defaultTexId) {
		XML objXML = ObjectLibrary.xmlLibrary.get(this.merchandiseType);
		if (objXML == null) {
			return defaultTexId;
		}
		if (objXML.getAttribute("Activate").equals("Dye") && objXML.hasOwnProperty("Tex2")) {
			return objXML.getAttribute("Tex2");
		}
		return defaultTexId;
	}

	@Override
	protected BitmapData getTexture(Camera camera, int time) {
		if (this.alpha_ == 1 && size_ == 100) {
			return this.merchandiseTexture_;
		}
		BitmapData tempTexture = ObjectLibrary.getRedrawnTextureFromType(this.merchandiseType_, size_, false, false);
		if (this.alpha_ != 1) {
			this.ct_.alphaMultiplier = this.alpha_;
			tempTexture.colorTransform(tempTexture.rect, this.ct_);
		}
		return tempTexture;
	}

	public void setMerchandiseType(int merchandiseType) {
		this.merchandiseType = merchandiseType;
		this.merchandiseTexture = ObjectLibrary.getRedrawnTextureFromType(this.merchandiseType_, 100, false);
	}


}
