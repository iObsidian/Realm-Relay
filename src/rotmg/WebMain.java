package rotmg;

import rotmg.game.util.AssetLoader;
import rotmg.util.AssetLoader;

public class WebMain {

    public static double sWidth = 800;
    public static double sHeight = 600;

    public static void main(String[] args) {
        WebMain w = new WebMain();
    }

    public WebMain() {
        setup();
    }

    private void setup() {
        new AssetLoader().load();
    }

}
