package realmrelay.game.classes.model;

import java.util.HashMap;
import java.util.List;

public class CharacterSkins {

	private final List<CharacterSkin> skins = new ArrayList<CharacterSkin>(0);

	private final HashMap<Integer, CharacterSkin> map = new HashMap<>();

	public CharacterSkins() {
		super();
	}

	private CharacterSkin defaultSkin;
	private CharacterSkin selectedSkin;
	private int maxLevelAchieved;

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
		skin.changed.add(this::onSkinChanged);
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
		List<CharacterSkin> _loc1 = new List<CharacterSkin>();
		for (CharacterSkin _loc2 : this.skins) {
			if (_loc2.getState() != CharacterSkinState.UNLISTED) {
				_loc1.add(_loc2_);
			}
		}
		return _loc1;
	}

	private void  onSkinChanged(CharacterSkin param1)  {
        if (param1.getIsSelected() && this.selectedSkin != param1) {
            this.selectedSkin && this.selectedSkin.setIsSelected(false);
            this.selectedSkin = param1;
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
