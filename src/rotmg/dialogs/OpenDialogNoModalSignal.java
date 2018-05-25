package rotmg.dialogs;

import flash.display.Sprite;

public class OpenDialogNoModalSignal extends Signal<Sprite> {

    static OpenDialogNoModalSignal instance;

    public static OpenDialogNoModalSignal getInstance() {
        if (instance == null) {
            instance = new OpenDialogNoModalSignal();
        }
        return instance;
    }

}

