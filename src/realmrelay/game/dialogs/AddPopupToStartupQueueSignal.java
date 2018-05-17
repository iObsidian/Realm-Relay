package realmrelay.game.dialogs;

import realmrelay.game._as3.Signal;

public class AddPopupToStartupQueueSignal<AddPopupToStartupQueue> extends Signal {

    static AddPopupToStartupQueueSignal instance;

    public static AddPopupToStartupQueueSignal getInstance() {
        if (instance == null) {
            instance = new AddPopupToStartupQueueSignal();
        }
        return instance;
    }

}

