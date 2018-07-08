package alde.test;

import rotmg.messaging.outgoing.Hello;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MainTest {

	List<Consumer> consumers = new ArrayList<>();

	public MainTest() {
		Consumer<Hello> c = this::receive;
	}

	public static void main(String[] args) {
		MainTest m = new MainTest();
	}

	public void receive(Hello h) {

	}

	public void add(Consumer<ANY> c) {
		consumers.add(c);
	}

}



