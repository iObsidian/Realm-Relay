package oryx2D.entity;

import oryx2D.graphics.Screen;
import oryx2D.level.Level;

public abstract class Entity {
	public int x, y;
	protected Level level;

	abstract public void update();

	abstract public void render(Screen screen);

	public void init(Level level) {
		this.level = level;
	}

}
