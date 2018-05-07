package realmrelay.packets.server;

import realmrelay.packets.Packet;
import realmrelay.packets.data.GroundTileData;
import realmrelay.packets.data.unused.Entity;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class UpdatePacket extends Packet {

	private GroundTileData[] tiles;
	public Entity[] newObjs;
	public int[] drops;


	public UpdatePacket() {
		this.tiles = new GroundTileData[0];
		this.newObjs = new Entity[0];
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

		newObjs = new Entity[in.readShort()];
		for (int i = 0; i < newObjs.length; i++) {
			Entity Entity = new Entity();
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
		for (Entity obj : newObjs) {
			obj.writeToOutput(out);
		}
		out.writeShort(drops.length);
		for (int drop : drops) {
			out.writeInt(drop);
		}
	}

}
