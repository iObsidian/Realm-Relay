package rotmg.dialogs.view;

import alde.flash.utils.EventConsumer;
import flash.airglobal.Graphics;
import flash.display.DisplayObject;
import flash.display.DisplayObjectContainer;
import flash.display.Sprite;
import flash.events.Event;

public class DialogsView extends Sprite {

	private Sprite background;

	private DisplayObjectContainer container;

	private Sprite current;

	private DisplayObject pushed;

	public DialogsView() {
		super();
		addChild(this.background = new Sprite());
		addChild(this.container = new Sprite());
		this.background.visible = false;
		this.background.mouseEnabled = true;
	}


	public void showBackground() {
		this.showBackground(1381653);
	}

	public void showBackground(int param1) {
		Graphics loc2 = this.background.graphics;
		loc2.clear();
		loc2.beginFill(param1, 0.6);
		loc2.drawRect(0, 0, 800, 600);
		loc2.endFill();
		this.background.visible = true;
	}

	public void show(Sprite param1, boolean param2) {
		this.removeCurrentDialog();
		this.addDialog(param1);

		if (param2)
			this.showBackground();
	}

	public void hideAll() {
		this.background.visible = false;
		this.removeCurrentDialog();
	}

	public void push(Sprite param1) {
		this.current.visible = false;
		this.pushed = param1;
		addChild(param1);
		this.background.visible = true;
	}

	public DisplayObject getPushed() {
		return this.pushed;
	}

	public void pop() {
		removeChild(this.pushed);
		this.current.visible = true;
	}

	private void addDialog(Sprite param1) {
		this.current = param1;
		param1.addEventListener(Event.REMOVED, new EventConsumer<>(this::onRemoved));
		this.container.addChild(param1);
	}

	private void onRemoved(Event param1) {
		Sprite loc2 = (Sprite) param1.target;
		if (this.current == loc2) {
			this.background.visible = false;
			this.current = null;
		}
	}

	private void removeCurrentDialog() {
		if (this.current != null && this.container.contains(this.current)) {
			this.current.removeEventListener(Event.REMOVED, new EventConsumer<>(this::onRemoved));
			this.container.removeChild(this.current);
			this.background.visible = false;
		}
	}

}
