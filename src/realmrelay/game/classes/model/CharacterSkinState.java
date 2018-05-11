package realmrelay.game.classes.model;

public class CharacterSkinState {

	public static CharacterSkinState OWNED = new CharacterSkinState(false, "OWNED", false);

	public static CharacterSkinState UNLISTED = new CharacterSkinState(true, "UNLISTED", false);

	public static CharacterSkinState PURCHASABLE = new CharacterSkinState(false, "PURCHASABLE", true);

	public static CharacterSkinState PURCHASING = new CharacterSkinState(true, "PURCHASING", true);

	public static CharacterSkinState LOCKED = new CharacterSkinState(true, "LOCKED", true);

	public static CharacterSkinState NULL = new CharacterSkinState(true, "NULL", true);

	public CharacterSkinState(boolean param1, String param2, boolean param3) {
		super();
		this.isDisabled = param1;
		this.skinStateDeterminedByLevel = param3;
		this.name = param2;
	}

	private boolean isDisabled;
	private boolean skinStateDeterminedByLevel;
	private String name;

	public boolean isDisabled() {
		return this.isDisabled;
	}

	public boolean isSkinStateDeterminedByLevel() {
		return this.skinStateDeterminedByLevel;
	}

}
