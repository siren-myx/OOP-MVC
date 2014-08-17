package animationCommands;

import graphics.Avatar;
import util.annotations.Tags;
@Tags({"Animator"})
public class ClapAnimator{
	Avatar theAvatar;
	public ClapAnimator(Avatar anAvatar){
		theAvatar = anAvatar;
	}
	    
    public synchronized void rotateArm(int angle){
    	if (angle>0) {
    		for (int m=0;m<angle;m++){
    			theAvatar.rotateArm(m);
            	ThreadSupport.sleep(20);
    		}
    	}
    	else if (angle<0) {
       		for (int m=0;m>angle;m--){
    			theAvatar.clapAll();
    		}	
    	}

    }
}

