package realmrelay.game;

import realmrelay.game.util.AssetLoader;

public class WebMain {

    public static float sWidth = 800;
    public static float sHeight = 600;

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
