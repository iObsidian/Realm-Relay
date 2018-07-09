package kabam.rotmg.ui.model;

import kabam.rotmg.parameters.Parameters;
import rotmg.GameSprite;
import rotmg.parameters.Parameters;

/**
 * 100% match
 */
public class HUDModel {

	public GameSprite gameSprite;

	public HUDModel() {
		super();
	}

	public String getPlayerName() {
		return this.gameSprite.model.getName() != null ? this.gameSprite.model.getName() : this.gameSprite.map.player.name;
	}

	public String getButtonType() {
		return this.gameSprite.gsc.gameId == Parameters.NEXUS_GAMEID ? "OPTIONS_BUTTON" : "NEXUS_BUTTON";
	}

}
