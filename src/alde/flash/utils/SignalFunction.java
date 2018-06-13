package alde.flash.utils;

import java.util.function.Consumer;

public class SignalFunction<T> {

		public static final int CONSUMER = 0; // takes something
		public static final int RUNNABLE = 1; // takes nothing

		public int type;

		public SignalFunction(Consumer<T> listener) {
			type = CONSUMER;
			consumer_listener = listener;
		}

		public SignalFunction(Runnable listener) {
			type = RUNNABLE;
			runnable_listener = listener;
		}

		public Runnable runnable_listener;
		public Consumer<T> consumer_listener;

		public void dispatch(T t) {
			if (type == RUNNABLE) {
				runnable_listener.run();
			} else if (type == CONSUMER) {
				consumer_listener.accept(t);
			} else {
				System.err.println("Unknown type : " + type);
			}
		}

		public void dispatch() {
			if (type == RUNNABLE) {
				runnable_listener.run();
			} else {
				consumer_listener.accept(null);
			}
		}

	}