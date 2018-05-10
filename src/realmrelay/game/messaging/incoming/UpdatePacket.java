package realmrelay.game.messaging.incoming;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.game.messaging.data.GroundTileData;
import realmrelay.game.messaging.data.ObjectData;
import realmrelay.packets.Packet;

public class UpdatePacket extends Packet {

	public GroundTileData[] tiles;
	public ObjectData[] newObjs;
	public int[] drops;


	public UpdatePacket() {
		this.tiles = new GroundTileData[0];
		this.newObjs = new ObjectData[0];
		this.drops = new int[0];
	}

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		tiles = new GroundTileData[in.readShort()];
		for (int i = 0; i < tiles.length; i++) {
			GroundTileData tile = new GroundTileData();
			tile.parseFromInput(in);
			tiles[i] = tile;
		}

		newObjs = new ObjectData[in.readShort()];
		for (int i = 0; i < newObjs.length; i++) {
			ObjectData Entity = new ObjectData();
			Entity.parseFromInput(in);
			newObjs[i] = Entity;
		}

		drops = new int[in.readShort()];
		for (int i = 0; i < drops.length; i++) {
			drops[i] = in.readInt();
		}

	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeShort(tiles.length);
		for (GroundTileData tile : tiles) {
			tile.writeToOutput(out);
		}
		out.writeShort(newObjs.length);
		for (ObjectData obj : newObjs) {
			obj.writeToOutput(out);
		}
		out.writeShort(drops.length);
		for (int drop : drops) {
			out.writeInt(drop);
		}
	}

}
