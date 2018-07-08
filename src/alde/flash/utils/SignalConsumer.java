package alde.flash.utils;

import java.util.function.Consumer;

public class SignalConsumer<T> {

	Consumer<T> consumer;
	Runnable runnable;

	public SignalConsumer(Consumer<T> consumer) {
		this.consumer = consumer;
	}

	public SignalConsumer(Runnable runnable) {
		this.runnable = runnable;
	}

	public void dispatch(T event) {
		if (runnable != null) {
			runnable.run();
		} else {
			consumer.accept(event);
		}
	}

	public void dispatch() {
		if (runnable != null) {
			runnable.run();
		} else {
			consumer.accept(null);
		}
	}

}