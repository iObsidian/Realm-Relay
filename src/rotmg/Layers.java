package rotmg.game;

import flash.display.DisplayObjectContainer;
import flash.display.Sprite;
import rotmg.core.view.ScreensView;
import rotmg.game.dialogs.view.DialogsView;
import rotmg.tooltips.view.TooltipsView;

public class Layers extends Sprite {

	public static Layers instance;

	public static Layers getInstance() {
		if (instance == null) {
			instance = new Layers();
		}

		return instance;
	}

	private ScreensView menu;

	public DisplayObjectContainer overlay;

	private TooltipsView tooltips;

	public DisplayObjectContainer top;

	public DisplayObjectContainer mouseDisabledTop;

	private DialogsView dialogs;

	private PopupView popups;

	public DisplayObjectContainer api;

	public DisplayObjectContainer console;

	public Layers() {
		super();
		addChild(this.menu = new ScreensView());
		addChild(this.overlay = new Sprite());
		addChild(this.top = new Sprite());
		addChild(this.mouseDisabledTop = new Sprite());
		this.mouseDisabledTop.mouseEnabled = false;
		addChild(this.popups = new PopupView());
		addChild(this.dialogs = new DialogsView());
		addChild(this.tooltips = new TooltipsView());
		addChild(this.api = new Sprite());
		addChild(this.console = new Sprite());
	}


}