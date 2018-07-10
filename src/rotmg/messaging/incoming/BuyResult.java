package rotmg.messaging.incoming;

import alde.flash.utils.MessageConsumer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class BuyResult extends IncomingMessage {

	public static final int UNKNOWN_ERROR_BRID = -1;
	public static final int SUCCESS_BRID = 0;
	public static final int INVALID_CHARACTER_BRID = 1;
	public static final int ITEM_NOT_FOUND_BRID = 2;
	public static final int NOT_ENOUGH_GOLD_BRID = 3;
	public static final int INVENTORY_FULL_BRID = 4;
	public static final int TOO_LOW_RANK_BRID = 5;
	public static final int NOT_ENOUGH_FAME_BRID = 6;
	public static final int PET_FEED_SUCCESS_BRID = 7;
	public int result;
	public String resultString;

	public BuyResult(int id, MessageConsumer callback) {
		super(id, callback);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		result = in.readInt();
		resultString = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(result);
		out.writeUTF(resultString);
	}

}
