package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import realmrelay.packets.data.unused.Item;

public class TradeStart extends IncomingMessage {

	public TradeStart(int id, Consumer callback) {
		super(id, callback);
		this.myItems = new Item[0];
		this.yourItems = new Item[0];
	}

	private Item[] myItems;
	private String yourName;
	private Item[] yourItems;

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
