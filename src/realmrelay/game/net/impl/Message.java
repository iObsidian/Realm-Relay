package realmrelay.game.net.impl;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.packets.data.unused.IData;

/**
 * WIP to replace RR's 'Packet'
 * 
 * Function is replaced with Java's Consumer
 * 
 * This is a close match, exception that parseFromInput and ouput throw exceptions (because stream.read() methods cause errors)
 * 
 */
public abstract class Message implements IData {

	public Message(int param1, Consumer param2) {
	}

	//public MessagePool pool;
	public Message prev;
	public Message next;
	public int id;

	public Consumer callback;
	private boolean isCallback;

}
