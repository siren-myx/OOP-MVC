package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Lay"})
public class LayAnimationCommand implements Runnable {
	Avatar theAvatar;
	int xMove, yMove;
	
    public LayAnimationCommand (Avatar anAvatar, int xDistance,
    		int yDistance){
    	theAvatar = anAvatar;
    	xMove = xDistance;
    	yMove = yDistance;
    }

    public void run() {
    	ThreadSupport.sleep(5000);
		theAvatar.failShapeOn();		
		theAvatar.getAngleLines().setArmLocation(
				theAvatar.getText().getX()+12,
				theAvatar.getText().getY()+60); 
		theAvatar.getAngleLines().setLegLocation(
				theAvatar.getText().getX()-14,
				theAvatar.getText().getY()+60);
    	
		for (int m=0;m<yMove;m++){
			theAvatar.moveAll(0,1);
			theAvatar.rotateArm(2*m);
			theAvatar.rotateLeg(2*m);
        	ThreadSupport.sleep(20);
		}
	}
}


