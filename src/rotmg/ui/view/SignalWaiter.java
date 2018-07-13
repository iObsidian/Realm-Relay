package rotmg.ui.view;

import alde.flash.utils.SignalConsumer;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;

import java.util.function.Consumer;

/**
 * 99% match (confirm 'listenTo' is working)
 */
public class SignalWaiter {

	public Signal<?> complete;

	private Dictionary<Signal, Boolean> texts;

	public SignalWaiter() {
		super();

		this.complete = new Signal();
		this.texts = new Dictionary<>();
	}

	public SignalWaiter add(Signal signal) {
		return this.push(signal);
	}

	public SignalWaiter push(Signal param1) {
		this.texts.put(param1, true);
		this.listenTo(param1);
		return this;
	}

	public SignalWaiter pushArgs(Signal... rest) {
		for (Signal loc2 : rest) {
			this.push(loc2);
		}
		return this;
	}

	private void listenTo(Signal value) {
		Consumer onTextChanged = o -> {
			texts.remove(value);
			checkEmpty();
		};
		value.addOnce(new SignalConsumer<>(onTextChanged)); //TODO make sur this works
	}

	private void checkEmpty() {
		if (this.isEmpty()) {
			this.complete.dispatch();
		}
	}

	public boolean isEmpty() {
		return this.texts.size() == 0;
	}

}
