package animationCommands;

import util.annotations.Tags;
import graphics.BridgeScene;
@Tags({"Fail Command"})
public class FailAnimationCommand implements Runnable {
	BridgeScene theScene;
    
    public FailAnimationCommand (BridgeScene aScene) {
    	theScene = aScene;
    }
    public void run() {
    	theScene.fail();	
    }

}
