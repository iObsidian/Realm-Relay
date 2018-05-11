package realmrelay.game.net.impl;

import realmrelay.packets.data.unused.IData;

import java.util.function.Consumer;

/**
 * WIP to replace RR's 'Packet'
 * <p>
 * Function is replaced with Java's Consumer
 * <p>
 * This is a close match, exception that parseFromInput and ouput throw exceptions (because stream.read() methods cause errors)
 */
public abstract class Message implements IData {

	public Message(int id, Consumer callback) {
		super();
		this.id = id;
		this.isCallback = callback != null;
		this.callback = callback;
	}

	//public MessagePool pool;
	public Message prev;
	public Message next;
	public int id;

	public Consumer callback;
	private boolean isCallback;




	public void consume() {
		this.isCallback && this.callback(this);
		this.prev = null;
		this.next = null;
		this.pool.append(this);
	}



}
