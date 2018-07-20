package rotmg.map.partyoverlay;

import rotmg.ui.tooltip.PortraitToolTip;
import flash.events.MouseEvent;
import rotmg.map.Camera;
import rotmg.map.Map;
import rotmg.map.Quest;
import rotmg.objects.GameObject;
import rotmg.parameters.Parameters;
import rotmg.ui.tooltip.QuestToolTip;
import rotmg.ui.tooltip.ToolTip;

import static flash.utils.timer.getTimer.getTimer;

public class QuestArrow extends GameObjectArrow {

	public Map map;

	public QuestArrow(Map param1) {
		super(16352321, 12919330, true);
		this.map = param1;
	}

	public void refreshToolTip() {
		setToolTip(this.getToolTip(go, getTimer()));
	}

	protected void onMouseOver(MouseEvent param1) {
		super.onMouseOver(param1);
		this.refreshToolTip();
	}

	protected void onMouseOut(MouseEvent param1) {
		super.onMouseOut(param1);
		this.refreshToolTip();
	}

	private ToolTip getToolTip(GameObject param1, int param2) {
		if (param1 == null || param1.texture == null) {
			return null;
		}
		if (this.shouldShowFullQuest(param2)) {
			return new QuestToolTip(go);
		}
		if (Parameters.data.showQuestPortraits) {
			return new PortraitToolTip(param1);
		}
		return null;
	}

	private boolean shouldShowFullQuest(int param1) {
		Quest loc2 = this.map.quest;
		return mouseOver || loc2.isNew(param1);
	}

	public void draw(int param1, Camera param2) {
		Object loc4 = false;
		boolean loc5 = false;
		GameObject loc3 = this.map.quest.getObject(param1);
		if (loc3 != go) {
			setGameObject(loc3);
			setToolTip(this.getToolTip(loc3, param1));
		} else if (go != null) {
			loc4 = tooltip instanceof QuestToolTip;
			loc5 = this.shouldShowFullQuest(param1);
			if (!loc4.equals(loc5)) {
				setToolTip(this.getToolTip(loc3, param1));
			}
		}
		super.draw(param1, param2);
	}
}
