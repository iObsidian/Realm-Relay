package rotmg.dialogs;

import rotmg.game._as3.Signal;

public class PopDialogSignal extends Signal {

    static PopDialogSignal instance;

    public static PopDialogSignal getInstance() {
        if (instance == null) {
            instance = new PopDialogSignal();
        }
        return instance;
    }


}
