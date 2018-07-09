package rotmg.tutorial;

<<<<<<< HEAD:src/kabam/rotmg/tutorial/doneAction.java
import kabam.rotmg.AGameSprite;
<<<<<<< HEAD:src/rotmg/tutorial/doneAction.java
=======
import rotmg.AGameSprite;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/tutorial/doneAction.java
=======
import rotmg.AGameSprite;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/tutorial/doneAction.java

public class doneAction {

	public static void doneAction(AGameSprite param1, String param2) {
		if (param1.tutorial == null) {
			return;
		}
		param1.tutorial.doneAction(param2);
	}

}
