package realmrelay.game.ui.model;

public class Key {

	public static Key PURPLE = new Key(0);
	public static Key GREEN = new Key(1);
	public static Key RED = new Key(2);
	public static Key YELLOW = new Key(3);

	public Key(int param1) {
		super();
		this.position = param1;
	}

	public int position;

}
