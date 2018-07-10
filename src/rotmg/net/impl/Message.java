package rotmg.net.impl;

import alde.flash.utils.IData;
import alde.flash.utils.MessageConsumer;

import java.io.*;

public class Message implements IData {

	public Message prev;

	public Message next;
	public int id;
	public MessageConsumer callback;
	private boolean isCallback;

	public Message(int param1, MessageConsumer param2) {
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
