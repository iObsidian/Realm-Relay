package realmrelay.game.util;

import realmrelay.game.sound.IMusic;
import realmrelay.game.sound.Music;

public class MusicProxy implements IMusic {

    @Override
    public void load() {
        Music.load();
    }
}
