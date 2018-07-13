package rotmg.pets.data;

import alde.flash.utils.XML;
import flash.utils.Dictionary;
import rotmg.appengine.SavedCharacter;
import rotmg.core.model.PlayerModel;
import rotmg.map.AbstractMap;
import rotmg.objects.ObjectLibrary;
import rotmg.pets.controller.NotifyActivePetUpdated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// This is a 100% match, except maybe for the getInstance() [Inject]
public class PetsModel {

	static public PetsModel instance;
	public NotifyActivePetUpdated notifyActivePetUpdated = NotifyActivePetUpdated.getInstance();
	public PlayerModel playerModel = PlayerModel.getInstance();
	private Map<Integer, PetVO> hash;
	private List<PetVO> pets;
	private XML yardXmlData;
	private int type;
	private PetVO activePet;

	public PetsModel() {
		this.hash = new Dictionary<>();
		this.pets = new ArrayList<PetVO>();
	}

	public static PetsModel getInstance() {
		if (instance == null) {
			instance = new PetsModel();
		}
		return instance;
	}

	public PetVO getPetVO(int param1) {
		PetVO loc2 = null;
		if (this.hash.get(param1) != null) {
			return this.hash.get(param1);
		}
		loc2 = new PetVO(param1);
		this.pets.add(loc2);
		this.hash.put(param1, loc2);
		return loc2;
	}

	public PetVO getCachedVOOnly(int param1) {
		return this.hash.get(param1);
	}

	public List<PetVO> getAllPets() {
		return this.pets;
	}

	public void addPet(PetVO param1) {
		this.pets.add(param1);
	}

	public PetVO getActivePet() {
		return this.activePet;
	}

	public void setActivePet(PetVO param1) {
		this.activePet = param1;
		SavedCharacter loc2 = this.playerModel.getCharacterById(this.playerModel.currentCharId);
		if (loc2 == null) {
			loc2.setPetVO(this.activePet);
		}
		this.notifyActivePetUpdated.dispatch();
	}

	public void removeActivePet() {
		SavedCharacter loc1 = this.playerModel.getCharacterById(this.playerModel.currentCharId);
		if (loc1 == null) {
			loc1.setPetVO(null);
		}
		this.activePet = null;
		this.notifyActivePetUpdated.dispatch();
	}

	public PetVO getPet(int param1) {
		int loc2 = this.getPetIndex(param1);
		if (loc2 == -1) {
			return null;
		}
		return this.pets.get(loc2);
	}

	private int getPetIndex(int param1) {
		PetVO loc2 = null;
		int loc3 = 0;
		while (loc3 < this.pets.size()) {
			loc2 = this.pets.get(loc3);
			if (loc2.getID() == param1) {
				return loc3;
			}
			loc3++;
		}
		return -1;
	}

	public int getPetYardRarity() {
		return PetYardEnum.selectByValue(this.yardXmlData.getAttribute("id")).rarity.ordinal;
	}

	public int getPetYardType() {
		return this.yardXmlData != null ? PetYardEnum.selectByValue(this.yardXmlData.getAttribute("id")).ordinal : 1;
	}

	public void setPetYardType(int param1) {
		this.type = param1;
		this.yardXmlData = ObjectLibrary.getXMLfromId(ObjectLibrary.getIdFromType(param1));
	}

	public boolean isMapNameYardName(AbstractMap param1) {
		if (param1.name == null) {
			return false;
		} else {
			return param1.name.substring(0, 8).equals("Pet Yard");
		}
	}

	public int getPetYardUpgradeFamePrice() {
		return this.yardXmlData.getIntValue("Fame");
	}

	public int getPetYardUpgradeGoldPrice() {
		return this.yardXmlData.getIntValue("Price");
	}

	public int getPetYardObjectID() {
		return this.type;
	}

	public void deletePet(int index) {
		this.pets.remove(this.getPetIndex(index));
	}

	public void clearPets() {
		this.hash = null;
		this.pets = new ArrayList<PetVO>();
		this.removeActivePet();
	}


}
