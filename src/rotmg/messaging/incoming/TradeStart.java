package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;
import rotmg.messaging.data.TradeItem;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class TradeStart extends IncomingMessage {

	private TradeItem[] myItems;
	private String yourName;
	private TradeItem[] yourItems;

	public TradeStart(int id, MessageConsumer callback) {
		super(id, callback);
		this.myItems = new TradeItem[0];
		this.yourItems = new TradeItem[0];
	}

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
