package realmrelay.packets.data;

class LootBag {

	public boolean worthToCheck = false;
	private int[] items = { -1, -1, -1, -1, -1, -1, -1, -1 };

	@Override
	public String toString() {
		return items.toString();
	}

	public LootBag(int id, WorldPosData pos, int[] items) {
		int id1 = id;
		WorldPosData pos1 = pos;
		this.items = items;
	}

	public LootBag() {
	}
}
