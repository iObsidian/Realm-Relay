package rotmg.map;

<<<<<<< HEAD:src/kabam/rotmg/map/Quest.java
import kabam.rotmg.objects.GameObject;
=======
import rotmg.objects.GameObject;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/map/Quest.java

import static flash.utils.getTimer.getTimer;

public class Quest {

	public Map map;

	public int objectId = -1;

	private int questAvailableAt = 0;

	private int questOldAt = 0;

	public Quest(Map param1) {
		super();
		this.map = param1;
	}

	public void setObject(int param1) {
		if (this.objectId == -1 && param1 != -1) {
			this.questAvailableAt = getTimer() + 200;
			this.questOldAt = this.questAvailableAt;
		}
		this.objectId = param1;
	}

	public void completed() {
		this.questAvailableAt = getTimer() + 200;
		this.questOldAt = this.questAvailableAt;
	}

	public GameObject getObject(int param1) {
		if (param1 < this.questAvailableAt) {
			return null;
		}
		return this.map.goDict.get(this.objectId);
	}

	public boolean isNew(int param1) {
		return param1 < this.questOldAt;
	}


}
