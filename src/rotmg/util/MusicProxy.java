package rotmg.util;

import rotmg.game.sound.IMusic;
import rotmg.game.sound.Music;
import rotmg.sound.IMusic;
import rotmg.sound.Music;

public class MusicProxy implements IMusic {

    @Override
    public void load() {
        Music.load();
    }
}
