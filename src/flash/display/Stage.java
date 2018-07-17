package flash.display;

public class Stage extends DisplayObjectContainer {

	public static Stage instance = new Stage();
	public String scaleMode;
	public DisplayObject root;
	public double stageWidth;
	public double stageHeight;
	public InteractiveObject focus;
	public String quality;
	public Stage3D[] stage3Ds;

	public static Stage getInstance() {
		return instance;
	}
}
