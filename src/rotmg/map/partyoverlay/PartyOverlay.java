package rotmg.map.partyoverlay;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.display.Sprite;
import flash.events.Event;
import rotmg.map.Camera;
import rotmg.map.Map;
import rotmg.objects.Party;
import rotmg.objects.Player;

public class PartyOverlay extends Sprite {

	public Map map;

	public Vector<PlayerArrow> partyMemberArrows = null;

	public QuestArrow questArrow;

	public PartyOverlay(Map param1) {
		super();
		PlayerArrow loc3 = null;
		this.map = param1;
		this.partyMemberArrows = new Vector<PlayerArrow>(Party.NUM_MEMBERS, true);
		int loc2 = 0;
		while (loc2 < Party.NUM_MEMBERS) {
			loc3 = new PlayerArrow();
			this.partyMemberArrows.put(loc2, loc3);
			addChild(loc3);
			loc2++;
		}
		this.questArrow = new QuestArrow(this.map);
		addChild(this.questArrow);
		addEventListener(Event.REMOVED_FROM_STAGE, new EventConsumer<>(this::onRemovedFromStage));
	}

	private void onRemovedFromStage(Event param1) {
		GameObjectArrow.removeMenu();
	}

	public void draw(Camera param1, int param2) {
		PlayerArrow loc6 = null;
		Player loc7 = null;
		int loc8 = 0;
		PlayerArrow loc9 = null;
		double loc10 = 0;
		double loc11 = 0;
		if (this.map.player == null) {
			return;
		}
		Party loc3 = this.map.party;
		Player loc4 = this.map.player;
		int loc5 = 0;
		while (loc5 < Party.NUM_MEMBERS) {
			loc6 = this.partyMemberArrows.get(loc5);
			if (!loc6.mouseOver) {
				if (loc5 >= loc3.members.length) {
					loc6.setGameObject(null);
				} else {
					loc7 = loc3.members.get(loc5);
					if (loc7.drawn || loc7.map == null || loc7.dead) {
						loc6.setGameObject(null);
					} else {
						loc6.setGameObject(loc7);
						loc8 = 0;
						while (loc8 < loc5) {
							loc9 = this.partyMemberArrows.get(loc8);
							loc10 = loc6.x - loc9.x;
							loc11 = loc6.y - loc9.y;
							if (loc10 * loc10 + loc11 * loc11 < 64) {
								if (!loc9.mouseOver) {
									loc9.addGameObject(loc7);
								}
								loc6.setGameObject(null);
								break;
							}
							loc8++;
						}
						loc6.draw(param2, param1);
					}
				}
			}
			loc5++;
		}
		if (!this.questArrow.mouseOver) {
			this.questArrow.draw(param2, param1);
		}
	}


}
