package rotmg.util;

<<<<<<< HEAD:src/kabam/rotmg/util/MusicProxy.java
import kabam.rotmg.sound.IMusic;
import kabam.rotmg.sound.Music;
=======
import rotmg.sound.IMusic;
import rotmg.sound.Music;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/util/MusicProxy.java

public class MusicProxy implements IMusic {

	@Override
	public void load() {
		Music.load();
	}
}
