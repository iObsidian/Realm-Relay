package realmrelay.game.net.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * WIP to replace RR's 'Packet'
 * 
 * Function is replaced with Java's Consumer
 * 
 * This is a close match, exception that parseFromInput and ouput throw exceptions (because stream.read() methods cause errors)
 * 
 */
public class Message {

	public Message(int param1, Consumer param2) {

	}

	//public MessagePool pool;
	public Message prev;
	public Message next;
	public int id;

	public Consumer callback;
	private boolean isCallback;

	public void parseFromInput(DataInput in) throws IOException {
	}

	public void writeToOutput(DataOutput out) throws IOException {
	}

}
