package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
import graphics.BridgeScene;
@Tags({"Say"})
public class SayAnimationCommand implements Runnable {
	String words;
	Avatar theAvatar;
	BridgeScene theScene;
    
    public SayAnimationCommand (BridgeScene aScene, String newVal) {
    	theScene = aScene;
    	words = newVal;
    }
    public void run() {
    	theScene.say(words);
    }

}
