package rotmg.dialogs;

import rotmg.game._as3.Signal;
import rotmg.game._as3.Sprite;

public class PushDialogSignal extends Signal<Sprite> {

    static PushDialogSignal instance;

    public static PushDialogSignal getInstance() {
        if (instance == null) {
            instance = new PushDialogSignal();
        }
        return instance;
    }

}



