package rotmg.ui.model;

import rotmg.GameSprite;

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
