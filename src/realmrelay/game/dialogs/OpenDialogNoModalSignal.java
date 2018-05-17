package realmrelay.game.dialogs;

import realmrelay.game._as3.Signal;
import realmrelay.game._as3.Sprite;

public class OpenDialogNoModalSignal extends Signal<Sprite> {

    static OpenDialogNoModalSignal instance;

    public static OpenDialogNoModalSignal getInstance() {
        if (instance == null) {
            instance = new OpenDialogNoModalSignal();
        }
        return instance;
    }

}

