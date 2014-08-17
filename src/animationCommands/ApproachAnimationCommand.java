package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
import graphics.BridgeScene;
@Tags({"Approach"})
public class ApproachAnimationCommand implements Runnable {
	Avatar theAvatar;
	BridgeScene theScene;
	final int APPROACH_X = 410, APPROACH_Y = 220;
    
    public ApproachAnimationCommand (BridgeScene aScene, Avatar anAvatar) {
    	theScene = aScene;
    	theAvatar = anAvatar;
    	}
    
    public void run() {
		theAvatar.moveAll(APPROACH_X-theAvatar.getX(), 0);
		theAvatar.moveAll(0, APPROACH_Y-theAvatar.getY());
		theScene.setApproachExit();
		theScene.setSpeak();
		theScene.setPass();
		theScene.setFail();
    }

}
