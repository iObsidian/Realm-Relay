package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.data.Item;

public class TradeStartPacket extends Packet {

	private Item[] myItems = new Item[0];
	private String yourName;
	private Item[] yourItems = new Item[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		myItems = new Item[in.readShort()];
		for (int i = 0; i < myItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			myItems[i] = item;
		}
		yourName = in.readUTF();
		yourItems = new Item[in.readShort()];
		for (int i = 0; i < yourItems.length; i++) {
			Item item = new Item();
			item.parseFromInput(in);
			yourItems[i] = item;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(myItems.length);
		for (Item item : myItems) {
			item.writeToOutput(out);
		}
		out.writeUTF(yourName);
		out.writeShort(yourItems.length);
		for (Item item : yourItems) {
			item.writeToOutput(out);
		}
	}

}
