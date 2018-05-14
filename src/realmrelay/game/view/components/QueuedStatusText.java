package realmrelay.game.view.components;

import realmrelay.game._as3.Sprite;
import realmrelay.game.objects.GameObject;

public class QueuedStatusText extends Sprite {

	public QueuedStatusTextList list;

	public QueuedStatusText next;

	public StringBuilder stringBuilder;


	public QueuedStatusText(GameObject param1, StringBuilder param2, int param3, int param4) {
		this(param1, param2, param3, param4, 0);
	}

	public QueuedStatusText(GameObject param1, StringBuilder param2, int param3, int param4, int param5) {
		this.stringBuilder = param2;
		super(param1, param3, param4, param5);
		setStringBuilder(param2);
	}

	@Override
	public void dispose() {
		this.list.shift();
	}

}
