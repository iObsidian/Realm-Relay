package rotmg.view;

import alde.flash.utils.EventConsumer;
import flash.events.Event;
import flash.events.KeyboardEvent;
import flash.events.MouseEvent;
import org.osflash.signals.Signal;
import rotmg.GameSprite;
import rotmg.parameters.Parameters;
import rotmg.ui.panels.ButtonPanel;
import rotmg.util.TextKey;

public class MoneyChangerPanel extends ButtonPanel {

	public Signal triggered;

	public MoneyChangerPanel(GameSprite param1) {
		super(param1, TextKey.MONEY_CHANGER_TITLE, TextKey.MONEY_CHANGER_BUTTON);
		this.triggered = new Signal();
		addEventListener(Event.ADDED_TO_STAGE, new EventConsumer<>(this::onAddedToStage));
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
	}

	private void onAddedToStage(Event param1) {
		stage.addEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
	}

	private void onRemovedFromStage(Event param1) {
		stage.removeEventListener(KeyboardEvent.KEY_DOWN, new EventConsumer<>(this::onKeyDown));
	}

	protected void onButtonClick(MouseEvent param1) {
		this.triggered.dispatch();
	}

	private void onKeyDown(KeyboardEvent param1) {
		if (param1.keyCode == Parameters.data.interact && stage.focus == null) {
			this.triggered.dispatch();
		}
	}
}
