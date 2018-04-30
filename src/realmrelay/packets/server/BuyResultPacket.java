package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;

public class BuyResultPacket extends Packet {

	/**
	UnknownError = -1
	Success = 0
	InvalidCharacter = 1
	ItemNotFound = 2
	NotEnoughGold = 3
	InventoryFull = 4
	TooLowRank = 5
	NotEnoughFame = 6
	PetFeedSuccess = 7
	*/

	private int result;
	private String message;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		result = in.readInt();
		message = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(result);
		out.writeUTF(message);
	}

}
