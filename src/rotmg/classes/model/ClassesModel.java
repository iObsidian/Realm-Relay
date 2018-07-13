package rotmg.classes.model;

import alde.flash.utils.SignalConsumer;
import flash.utils.Dictionary;
import org.osflash.signals.Signal;

import java.util.ArrayList;

/**
 * The Class selection model for the UI (create new character)
 */
public class ClassesModel {

	public static final int WIZARD_ID = 782;
	private static ClassesModel instance;
	public final Signal<CharacterClass> selected = new Signal<>();
	private ArrayList<CharacterClass> classes = new ArrayList<>();
	private int count = 0;
	private CharacterClass selectedChar;
	private Dictionary<Integer, CharacterClass> map;

	public static ClassesModel getInstance() {
		if (instance == null) {
			instance = new ClassesModel();
		}

		return instance;
	}

	public int getCount() {
		return this.count;
	}

	public CharacterClass getClassAtIndex(int param1) {
		return this.classes.get(param1);
	}

	public CharacterClass getCharacterClass(int param1) {

		if (this.map.get(param1) == null) {
			this.map.put(param1, this.makeCharacterClass());
		}
		return this.map.get(param1);
	}

	public CharacterClass getSelected() {
		if (this.selectedChar != null) {
			return this.selectedChar;
		} else {
			return this.getCharacterClass(WIZARD_ID);
		}
	}

	public CharacterSkin getCharacterSkin(int param1) {
		CharacterSkin _loc2 = null;
		for (CharacterClass _loc3_ : this.classes) {
			_loc2 = _loc3_.skins.getSkin(param1);
			if (_loc2 != _loc3_.skins.getDefaultSkin()) {
				break;
			}
		}
		return _loc2;
	}

	private CharacterClass makeCharacterClass() {
		CharacterClass _loc1 = new CharacterClass();
		_loc1.selected.add(new SignalConsumer<CharacterClass>(this::onClassSelected));
		this.classes.add(_loc1);
		this.count = this.classes.size();
		return _loc1;
	}

	private void onClassSelected(CharacterClass param1) {
		if (this.selectedChar != param1) {
			this.selectedChar.setIsSelected(false);
			this.selectedChar = param1;
			this.selected.dispatch(param1);
		}
	}

}
