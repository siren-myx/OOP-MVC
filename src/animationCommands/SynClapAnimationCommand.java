package animationCommands;

import util.annotations.Tags;
@Tags({"Animating Command"})
public class SynClapAnimationCommand implements Runnable {
	ClapAnimator theAnimator;
	int theAngle;
	
    public SynClapAnimationCommand (ClapAnimator anAnimator, int anAngle){
    	theAnimator = anAnimator;
    	theAngle = anAngle;
    }

    public void run() {
    	theAnimator.rotateArm(theAngle);
	}  
}
