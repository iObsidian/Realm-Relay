package rotmg.messaging.outgoing;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.function.Consumer;

import rotmg.objects.Player;

public class Reskin extends OutgoingMessage {

	public Reskin(int id, Consumer callback) {
		super(id, callback);
	}

	public int skinID;
	public Player player;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		skinID = in.readInt();
	}

	@Override
	 public void consume() {
		super.consume();
		this.player = null;
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(skinID);
	}

}
