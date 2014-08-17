package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Rotate Left Arm"})
public class RotateLeftArmAnimationCommand implements Runnable {
	Avatar theAvatar;
	int rotateAngle;
	
    public RotateLeftArmAnimationCommand (Avatar anAvatar, int angle){
    	theAvatar = anAvatar;
    	rotateAngle = angle;
    }

    public void run() {
    	if (rotateAngle>=0){
        	for (int i=0; i<rotateAngle; i++){
    			theAvatar.moveAll(0,0);
    			theAvatar.rotateLeftArm(i);
            	ThreadSupport.sleep(15);
        		}
    		}
    	else if (rotateAngle<0){
        	for (int i=0; i>rotateAngle; i--){
    			theAvatar.moveAll(0,0);
    			theAvatar.rotateLeftArm(i);	
            	ThreadSupport.sleep(15);
        		}
    	}
	} 
   
}


