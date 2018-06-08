package rotmg.ui;

import alde.flash.utils.Vector;
import flash.display.GraphicsPath;
import flash.display.GraphicsSolidFill;
import flash.display.IGraphicsData;
import flash.display.Sprite;
import flash.events.Event;
import flash.geom.Point;
import rotmg.AGameSprite;
import rotmg.GameSprite;
import rotmg.messaging.incoming.TradeAccepted;
import rotmg.messaging.incoming.TradeChanged;
import rotmg.messaging.incoming.TradeStart;
import rotmg.ui.view.UnFocusAble;
import rotmg.util.GraphicsUtil;


public class HUDView extends Sprite implements UnFocusAble {

	private final Point BG_POSITION = new Point(0, 0);

	private final Point MAP_POSITION = new Point(4, 4);

	private final Point CHARACTER_DETAIL_PANEL_POSITION = new Point(0, 198);

	private final Point STAT_METERS_POSITION = new Point(12, 230);

	private final Point EQUIPMENT_INVENTORY_POSITION = new Point(14, 304);

	private final Point TAB_STRIP_POSITION = new Point(7, 346);

	private final Point INTERACT_PANEL_POSITION = new Point(0, 500);

	/*
	 * private  CharacterWindowBackground background;

	 * private  MiniMapImp miniMap;

	 * private  EquippedGrid equippedGrid;

	 * private  StatMetersView statMeters;

	 * private  CharacterDetailsView characterDetails;

	 * private  Sprite equippedGridBG;

	 * private Player player;

	 * public  TabStripView tabStrip;

	 * public  InteractPanel interactPanel;

	 * public  TradePanel tradePanel;
	 */

	public HUDView() {
		super();
		this.createAssets();
		this.addAssets();
		this.positionAssets();
	}

	private void createAssets() {
		/*this.background = new CharacterWindowBackground();
		this.miniMap = new MiniMapImp(192, 192);
		this.tabStrip = new TabStripView();
		this.characterDetails = new CharacterDetailsView();
		this.statMeters = new StatMetersView();*/
	}

	private void addAssets() {
		/*addChild(this.background);
		addChild(this.miniMap);
		addChild(this.tabStrip);
		addChild(this.characterDetails);
		addChild(this.statMeters);*/
	}

	private void positionAssets() {
		/*this.background.x = this.BG_POSITION.x;
		this.background.y = this.BG_POSITION.y;
		this.miniMap.x = this.MAP_POSITION.x;
		this.miniMap.y = this.MAP_POSITION.y;
		this.tabStrip.x = this.TAB_STRIP_POSITION.x;
		this.tabStrip.y = this.TAB_STRIP_POSITION.y;
		this.characterDetails.x = this.CHARACTER_DETAIL_PANEL_POSITION.x;
		this.characterDetails.y = this.CHARACTER_DETAIL_PANEL_POSITION.y;
		this.statMeters.x = this.STAT_METERS_POSITION.x;
		this.statMeters.y = this.STAT_METERS_POSITION.y;*/
	}

	public void setPlayerDependentAssets(GameSprite param1) {
		/*this.player = param1.map.player;*/
		this.createEquippedGridBackground();
		this.createEquippedGrid();
		this.createInteractPanel(param1);
	}

	private void createInteractPanel(GameSprite param1) {
		/*this.interactPanel = new InteractPanel(param1, this.player, 200, 100);
		this.interactPanel.x = this.INTERACT_PANEL_POSITION.x;
		this.interactPanel.y = this.INTERACT_PANEL_POSITION.y;
		addChild(this.interactPanel);*/
	}

	private void createEquippedGrid() {
		/*this.equippedGrid = new EquippedGrid(this.player, this.player.slotTypes, this.player);
		this.equippedGrid.x = this.EQUIPMENT_INVENTORY_POSITION.x;
		this.equippedGrid.y = this.EQUIPMENT_INVENTORY_POSITION.y;
		addChild(this.equippedGrid);*/
	}

	private void createEquippedGridBackground() {
		Vector<IGraphicsData> loc3 = null;
		GraphicsSolidFill loc1 = new GraphicsSolidFill(6776679, 1);
		GraphicsPath loc2 = new GraphicsPath(new Vector<Integer>(), new Vector<Double>());
		loc3 = new Vector<IGraphicsData>(loc1, loc2, GraphicsUtil.END_FILL);
		GraphicsUtil.drawCutEdgeRect(0, 0, 178, 46, 6, new Vector<>(1, 1, 1, 1), loc2);
		/*this.equippedGridBG = new Sprite();
		 this.equippedGridBG.x = this.EQUIPMENT_INVENTORY_POSITION.x - 3;
		 this.equippedGridBG.y = this.EQUIPMENT_INVENTORY_POSITION.y - 3;
		 this.equippedGridBG.graphics.drawGraphicsData(loc3);
		 addChild(this.equippedGridBG);*/
	}

	public void draw() {
		/*if (this.equippedGrid) {
			this.equippedGrid.draw();
		}
		if (this.interactPanel) {
			this.interactPanel.draw();
		}*/
	}

	public void startTrade(AGameSprite param1, TradeStart param2) {
		/*if (!this.tradePanel) {
			this.tradePanel = new TradePanel(param1, param2);
			this.tradePanel.y = 200;
			this.tradePanel.addEventListener(Event.CANCEL, this.onTradeCancel);
			addChild(this.tradePanel);
			this.setNonTradePanelAssetsVisible(false);
		}*/
	}

	private void setNonTradePanelAssetsVisible(boolean param1) {
		/*this.characterDetails.visible = param1;
		this.statMeters.visible = param1;
		this.tabStrip.visible = param1;
		this.equippedGrid.visible = param1;
		this.equippedGridBG.visible = param1;
		this.interactPanel.visible = param1;*/
	}

	public void tradeDone() {
		this.removeTradePanel();
	}

	public void tradeChanged(TradeChanged param1) {
		/*if (this.tradePanel != null ) {
			this.tradePanel.setYourOffer(param1.offer);
		}*/
	}

	public void tradeAccepted(TradeAccepted param1) {
		/*if (this.tradePanel != null) {
			this.tradePanel.youAccepted(param1.myOffer, param1.yourOffer);
		}*/
	}

	private void onTradeCancel(Event param1) {
		/*this.removeTradePanel();*/
	}

	private void removeTradePanel() {
		/*if (this.tradePanel != null) {
			SpriteUtil.safeRemoveChild(this, this.tradePanel);
			this.tradePanel.removeEventListener(Event.CANCEL, this.onTradeCancel);
			this.tradePanel = null;
			this.setNonTradePanelAssetsVisible(true);
		}*/
	}


}
