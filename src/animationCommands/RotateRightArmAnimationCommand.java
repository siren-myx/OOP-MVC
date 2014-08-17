package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Rotate Right Arm"})
public class RotateRightArmAnimationCommand implements Runnable {
	Avatar theAvatar;
	int rotateAngle;
	
    public RotateRightArmAnimationCommand (Avatar anAvatar, int angle){
    	theAvatar = anAvatar;
    	rotateAngle = angle;
    }

    public void run() {
    	if (rotateAngle>=0){
        	for (int i=0; i<rotateAngle; i++){
    			theAvatar.moveAll(0,0);
    			theAvatar.rotateRightArm(i);
            	ThreadSupport.sleep(15);
        		}
    		}
    	else if (rotateAngle<0){
        	for (int i=0; i>rotateAngle; i--){
    			theAvatar.moveAll(0,0);
    			theAvatar.rotateRightArm(i);	
            	ThreadSupport.sleep(15);
        		}
    	} 		
	}
}


