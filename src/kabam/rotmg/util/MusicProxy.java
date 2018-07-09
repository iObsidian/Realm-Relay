package kabam.rotmg.util;

import kabam.rotmg.sound.IMusic;
import kabam.rotmg.sound.Music;
import rotmg.sound.IMusic;
import rotmg.sound.Music;

public class MusicProxy implements IMusic {

	@Override
	public void load() {
		Music.load();
	}
}
