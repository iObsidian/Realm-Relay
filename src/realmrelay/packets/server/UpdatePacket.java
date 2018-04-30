package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.data.Entity;
import realmrelay.data.Tile;
import realmrelay.packets.Packet;

public class UpdatePacket extends Packet {

	private Tile[] tiles = new Tile[0];
	public Entity[] newObjs = new Entity[0];
	public int[] drops = new int[0];

	@Override
	public void parseFromInput(DataInput in) throws IOException {

		tiles = new Tile[in.readShort()];
		for (int i = 0; i < tiles.length; i++) {
			Tile tile = new Tile();
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
		for (Tile tile : tiles) {
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
