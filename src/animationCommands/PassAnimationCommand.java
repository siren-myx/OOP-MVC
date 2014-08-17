package animationCommands;

import util.annotations.Tags;
import graphics.BridgeScene;
@Tags({"Pass"})
public class PassAnimationCommand implements Runnable {
	BridgeScene theScene;
    
    public PassAnimationCommand (BridgeScene aScene) {
    	theScene = aScene;
    }
    public void run() {
		theScene.pass();
    }
}
