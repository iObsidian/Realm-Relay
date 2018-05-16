package realmrelay.game.net.impl;

import realmrelay.packets.data.unused.IData;

import java.io.*;

public class Message implements IData {

	public MessagePool pool;

	public Message prev;

	public Message next;

	private boolean isCallback;

	public int id;

	public Function callback;

	public Message(int param1) {
		this(param1, null);
	}

	public Message(int param1, Function param2) {
		super();
		this.id = param1;
		this.isCallback = param2 != null;
		this.callback = param2;
	}

	public void consume() {
		this.isCallback && this.callback(this);
		this.prev = null;
		this.next = null;
		this.pool.append(this);
	}

	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		writeToOutput(out);
		return baos.toByteArray();
	}


	@Override
	public void parseFromInput(DataInput in) throws IOException {
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {

	}
}
