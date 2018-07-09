package rotmg.arena.model;

import org.osflash.signals.Signal;

import rotmg.assets.services.CharacterFactory;
import rotmg.classes.model.CharacterSkin;
import rotmg.classes.model.ClassesModel;
import rotmg.model.GameModel;
import rotmg.pets.data.PetsModel;

public class CurrentArenaRunModel {

	public final Signal waveUpdated = new Signal();
	public GameModel gameModel = GameModel.getInstance();
	public PetsModel petModel = PetsModel.getInstance();
	public ClassesModel classesModel = ClassesModel.getInstance();
	public CharacterFactory factory = CharacterFactory.getInstance();
	public boolean died = false;
	public ArenaLeaderboardEntry entry;
	public int costOfContinue = 0;

	public CurrentArenaRunModel() {
		this.entry = new ArenaLeaderboardEntry();
		this.clear();
	}

	public void clear() {
		this.died = false;
		this.entry.currentWave = 0;
		this.entry.runtime = -1;
	}

	public void incrementWave() {
		if (this.died) {
			this.died = false;
		} else {
			this.entry.currentWave++;
			this.waveUpdated.dispatch();
		}
	}

	public boolean hasEntry() {
		return this.entry.runtime != -1;
	}

	public void saveCurrentUserInfo() {
		this.clear();
		this.entry.name = this.gameModel.player.name;
		CharacterSkin loc1 = this.classesModel.getCharacterClass(this.gameModel.player.objectType).skins.getSkin(this.gameModel.player.skinId);
		this.entry.playerBitmap = this.factory.makeIcon(loc1.template, !!loc1.is16x16 ? 50 : 100, this.gameModel.player.getTex1(), this.gameModel.player.getTex2());
		this.entry.pet = this.petModel.getActivePet();
		this.entry.guildName = this.gameModel.player.guildName;
		this.entry.guildRank = this.gameModel.player.guildRank;
		this.entry.slotTypes = this.gameModel.player.slotTypes;
		this.entry.equipment = this.gameModel.player.equipment;
		this.entry.isPersonalRecord = true;
	}


}
