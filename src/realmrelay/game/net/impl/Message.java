package realmrelay.game.net.impl;

import realmrelay.packets.data.unused.IData;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.function.Consumer;

/**
 * WIP to replace RR's 'Packet'
 * <p>
 * Function is replaced with Java's Consumer
 * <p>
 * This is a close match, exception that parseFromInput and ouput throw exceptions (because stream.read() methods cause errors)
 */
public abstract class Message implements IData {

	public MessagePool pool;

	public Message prev;

	public Message next;

	private boolean isCallback;

	public int id;

	public Consumer callback;

	public Message(int param1, Consumer param2) {
		super();
		this.id = param1;
		this.isCallback = param2 != null;
		this.callback = param2;
	}

	public void consume() {
		this.isCallback = false;
		this.prev = null;
		this.next = null;
		this.pool.append(this);
	}

}
