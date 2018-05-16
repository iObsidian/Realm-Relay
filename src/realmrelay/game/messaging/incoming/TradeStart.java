package realmrelay.game.messaging.incoming;

import realmrelay.game.messaging.data.TradeItem;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

public class TradeStart extends IncomingMessage {

	public TradeStart(int id, Consumer callback) {
		super(id, callback);
		this.myItems = new TradeItem[0];
		this.yourItems = new TradeItem[0];
	}

	private TradeItem[] myItems;
	private String yourName;
	private TradeItem[] yourItems;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		myItems = new TradeItem[in.readShort()];
		for (int i = 0; i < myItems.length; i++) {
			TradeItem item = new TradeItem();
			item.parseFromInput(in);
			myItems[i] = item;
		}
		yourName = in.readUTF();
		yourItems = new TradeItem[in.readShort()];
		for (int i = 0; i < yourItems.length; i++) {
			TradeItem item = new TradeItem();
			item.parseFromInput(in);
			yourItems[i] = item;
		}
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(myItems.length);
		for (TradeItem item : myItems) {
			item.writeToOutput(out);
		}
		out.writeUTF(yourName);
		out.writeShort(yourItems.length);
		for (TradeItem item : yourItems) {
			item.writeToOutput(out);
		}
	}

}
