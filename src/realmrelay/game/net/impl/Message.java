package realmrelay.game.net.impl;

import realmrelay.game.net.api.MessageConsumer;
import realmrelay.packets.data.unused.IData;

import java.io.*;
import java.util.function.Consumer;

public class Message implements IData {

	public Message prev;

	public Message next;

	private boolean isCallback;

	public int id;

	public Consumer callback;

	/**public Message(int param1) {
		this(param1, null);
	}*/

	public Message(int param1, MessageConsumer param2) {
		this(param1, param2.getConsumer());
	}

	public Message(int param1, Consumer param2) {
		this.id = param1;
		this.isCallback = param2 != null;
		this.callback = param2;
	}

	public void consume() {
		if (this.isCallback) {
			this.callback.accept(this);
		}
		this.prev = null;
		this.next = null;
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
