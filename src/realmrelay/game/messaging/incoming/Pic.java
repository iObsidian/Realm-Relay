package realmrelay.game.messaging.incoming;

/** This implementation was dropped due to the removed of IData from BitmapData
public class PicPacket extends IncomingMessage {

	private BitmapData bitmapData;

	public ArenaDeath(int id, Consumer callback) {
		super(id, callback);
	}

	public PicPacket() {
		this.bitmapData = new BitmapData(0, 0);
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		bitmapData.parseFromInput(in);
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		bitmapData.writeToOutput(out);
	}

}
*/