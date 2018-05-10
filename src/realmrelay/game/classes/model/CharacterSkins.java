package realmrelay.game.classes.model;

import java.util.List;

public class CharacterSkins {

	
	private final List<CharacterSkin> skins = new List<CharacterSkin>(0);

    private const map:Object = {};

    public  CharacterSkins()  {
        super();
    }
    private CharacterSkin defaultSkin;
    private CharacterSkin selectedSkin;
    private int maxLevelAchieved;

    public int  getCount()  {
        return this.skins.length;
    }

    public CharacterSkin  getDefaultSkin()  {
        return this.defaultSkin;
    }

    public CharacterSkin  getSelectedSkin()  {
        return this.selectedSkin;
    }

    public CharacterSkin  getSkinAt(int param1)  {
        return this.skins[param1];
    }

    public void  addSkin(CharacterSkin param1, boolean = false param2)  {
        param1.changed.add(this.onSkinChanged);
        this.skins.add(param1);
        this.map[param1.id] = param1;
        this.updateSkinState(param1);
        if (param2) {
            this.defaultSkin = param1;
            if (!this.selectedSkin) {
                this.selectedSkin = param1;
                param1.setIsSelected(true);
            }
        }
        else if (param1.getIsSelected()) {
            this.selectedSkin = param1;
        }
    }

    public void  updateSkins(int param1)  {
        this.maxLevelAchieved = param1;
        for(CharacterSkin _loc2 : this.skins) {
            this.updateSkinState(_loc2_);
        }
    }

    public CharacterSkin  getSkin(int param1)  {
        return this.map[param1] || this.defaultSkin;
    }

    public List<CharacterSkin>  getListedSkins()  {
        CharacterSkin _loc2 = null;
        List<CharacterSkin> _loc1 = new List<CharacterSkin>();
        for(_loc2_ in this.skins) {
            if (_loc2_.getState() != CharacterSkinState.UNLISTED) {
                _loc1_.add(_loc2_);
            }
        }
        return _loc1_;
    }

    private void  onSkinChanged(CharacterSkin param1)  {
        if (param1.getIsSelected() && this.selectedSkin != param1) {
            this.selectedSkin && this.selectedSkin.setIsSelected(false);
            this.selectedSkin = param1;
        }
    }

    private void  updateSkinState(CharacterSkin param1)  {
        if (!param1.skinSelectEnabled) {
            param1.setState(CharacterSkinState.UNLISTED);
        }
        else if (param1.getState().isSkinStateDeterminedByLevel()) {
            param1.setState(this.getSkinState(param1));
        }
    }

    private CharacterSkinState  getSkinState(CharacterSkin param1)  {
        if (!param1.skinSelectEnabled) {
            return CharacterSkinState.UNLISTED;
        }
        if (this.maxLevelAchieved >= param1.unlockLevel && param1.unlockSpecial == null) {
            return CharacterSkinState.PURCHASABLE;
        }
        return CharacterSkinState.LOCKED;
    }

	
}
