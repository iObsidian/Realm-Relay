package rotmg.characters.reskin.control;

import rotmg.assets.services.CharacterFactory;
import rotmg.classes.model.CharacterClass;
import rotmg.classes.model.CharacterSkin;
import rotmg.classes.model.ClassesModel;
import rotmg.messaging.outgoing.Reskin;
import rotmg.model.GameModel;
import rotmg.objects.Player;

public class ReskinHandler {

	public GameModel model = GameModel.getInstance();

	public ClassesModel classes = ClassesModel.getInstance();

	public CharacterFactory factory = CharacterFactory.getInstance();

	public ReskinHandler() {
		super();
	}

	public void execute(Reskin param1) {
		Player loc2 = null;
		int loc3 = 0;
		CharacterClass loc4 = null;

		if (param1.player != null) {
			loc2 = param1.player;
		} else {
			loc2 = this.model.player;
		}
		loc3 = param1.skinID;
		loc4 = this.classes.getCharacterClass(loc2.objectType);
		CharacterSkin loc5 = loc4.skins.getSkin(loc3);
		loc2.skinId = loc3;
		loc2.skin = this.factory.makeCharacter(loc5.template);
		loc2.isDefaultAnimatedChar = false;
	}


}
