package rotmg.classes.model;

import alde.flash.utils.SignalConsumer;
import flash.utils.Dictionary;

import java.util.ArrayList;
import java.util.List;

public class CharacterSkins {

	private final List<CharacterSkin> skins = new ArrayList<CharacterSkin>(0);

	private final Dictionary<Integer, CharacterSkin> map = new Dictionary<>();
	private CharacterSkin defaultSkin;
	private CharacterSkin selectedSkin;
	private int maxLevelAchieved;

	public CharacterSkins() {
		super();
	}

	public int getCount() {
		return this.skins.size();
	}

	public CharacterSkin getDefaultSkin() {
		return this.defaultSkin;
	}

	public CharacterSkin getSelectedSkin() {
		return this.selectedSkin;
	}

	public CharacterSkin getSkinAt(int index) {
		return this.skins.get(index);
	}

	public void addSkin(CharacterSkin skin, boolean defaultSkin) {
		skin.changed.add(new SignalConsumer<CharacterSkin>(this::onSkinChanged));
		this.skins.add(skin);
		this.map.put(skin.id, skin);
		this.updateSkinState(skin);
		if (defaultSkin) {
			this.defaultSkin = skin;
			if (this.selectedSkin == null) {
				this.selectedSkin = skin;
				skin.setIsSelected(true);
			}
		} else if (skin.getIsSelected()) {
			this.selectedSkin = skin;
		}
	}

	public void updateSkins(int maxLevel) {
		this.maxLevelAchieved = maxLevel;
		for (CharacterSkin skin : this.skins) {
			this.updateSkinState(skin);
		}
	}

	public CharacterSkin getSkin(int skin) {
		if (this.map.get(skin) != null) {
			return this.map.get(skin);
		} else {
			return this.defaultSkin;
		}
	}

	public List<CharacterSkin> getListedSkins() {
		List<CharacterSkin> availableSkins = new ArrayList<CharacterSkin>();
		for (CharacterSkin skin : this.skins) {
			if (skin.getState() != CharacterSkinState.UNLISTED) {
				availableSkins.add(skin);
			}
		}
		return availableSkins;
	}

	private void onSkinChanged(CharacterSkin skin) {
		if (skin.getIsSelected() && this.selectedSkin != skin) {
			this.selectedSkin.setIsSelected(false);
			this.selectedSkin = skin;
		}
	}

	private void updateSkinState(CharacterSkin param1) {
		if (!param1.skinSelectEnabled) {
			param1.setState(CharacterSkinState.UNLISTED);
		} else if (param1.getState().isSkinStateDeterminedByLevel()) {
			param1.setState(this.getSkinState(param1));
		}
	}

	private CharacterSkinState getSkinState(CharacterSkin param1) {
		if (!param1.skinSelectEnabled) {
			return CharacterSkinState.UNLISTED;
		}
		if (this.maxLevelAchieved >= param1.unlockLevel && param1.unlockSpecial == null) {
			return CharacterSkinState.PURCHASABLE;
		}
		return CharacterSkinState.LOCKED;
	}

}
