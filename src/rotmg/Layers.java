package rotmg;

import flash.display.DisplayObjectContainer;
import flash.display.Sprite;
import rotmg.core.view.ScreensView;
import rotmg.dialogs.view.DialogsView;
import rotmg.tooltips.view.TooltipsView;
import rotmg.ui.popups.PopupView;

public class Layers extends Sprite {

	public static Layers instance;
	public DisplayObjectContainer overlay;
	public DisplayObjectContainer top;
	public DisplayObjectContainer mouseDisabledTop;
	public DisplayObjectContainer api;
	public DisplayObjectContainer console;
	private ScreensView menu;
	private TooltipsView tooltips;
	private DialogsView dialogs;
	private PopupView popups;

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

	public static Layers getInstance() {
		if (instance == null) {
			instance = new Layers();
		}

		return instance;
	}


}
