package rotmg.classes.model;

import org.osflash.signals.Signal;
import rotmg.assets.model.CharacterTemplate;

public class CharacterSkin {

	public final Signal<CharacterSkin> changed = new Signal<CharacterSkin>();

	public int id = 0;

	public String name = "";

	public int unlockLevel;

	public String unlockSpecial;

	public CharacterTemplate template;

	public int cost;

	public boolean limited = false;

	public boolean skinSelectEnabled = true;

	public boolean is16x16 = false;

	private CharacterSkinState state;

	private boolean isSelected;

	public CharacterSkin() {
		this.state = CharacterSkinState.NULL;
	}

	public boolean getIsSelected() {
		return this.isSelected;
	}

	public void setIsSelected(boolean param1) {
		if (this.isSelected != param1) {
			this.isSelected = param1;
			this.changed.dispatch(this);
		}
	}

	public CharacterSkinState getState() {
		return this.state;
	}

	public void setState(CharacterSkinState param1) {
		if (this.state != param1) {
			this.state = param1;
			this.changed.dispatch(this);
		}
	}

}
