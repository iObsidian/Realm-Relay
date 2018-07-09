package robotlegs.bender.bundles.mvcs.components;

import kabam.rotmg.map.mapoverlay.CharacterStatusText;
import kabam.rotmg.objects.GameObject;

/**
 * 100% match.
 */
public class QueuedStatusText extends CharacterStatusText {

	public QueuedStatusTextList list;

	public QueuedStatusText next;

	public StringBuilder stringBuilder;

	public QueuedStatusText(GameObject param1, StringBuilder param2, int param3, int param4, int param5) {
		super(param1, param3, param4, param5);
		this.stringBuilder = param2;
		setStringBuilder(param2);
	}

	public void dispose() {
		this.list.shift();
	}


}
