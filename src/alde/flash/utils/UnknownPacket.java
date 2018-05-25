package alde.flash.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import rotmg.messaging.incoming.IncomingMessage;


public class UnknownPacket extends IncomingMessage {

	public UnknownPacket(int id, Consumer callback) {
		super(id, callback);
	}

	private byte id;
	private final List<Byte> bytes = new LinkedList<Byte>();

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		try {
			while (true) {
				this.bytes.add(in.readByte());
			}
		} catch (Exception e) {
		}
	}

	public void setId(byte id) {
		this.id = id;
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		for (Byte b : this.bytes) {
			out.writeByte(b);
		}
	}

}
