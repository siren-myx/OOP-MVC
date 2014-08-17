package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Clap"})
public class ClapAnimationCommand implements Runnable {
	Avatar theAvatar;
	
    public ClapAnimationCommand (Avatar anAvatar){
    	theAvatar = anAvatar;
    }

    public void run() {
    	for (int n=0; n<100; n++){
        	theAvatar.clapAll();
        	ThreadSupport.sleep(50);
        	
    	}
    }
}