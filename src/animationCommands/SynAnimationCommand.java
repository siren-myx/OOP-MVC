package animationCommands;

import util.annotations.Tags;
@Tags({"Animating Command"})
public class SynAnimationCommand implements Runnable {
	SynAnimator theAnimator;
	int xMove, yMove;
	
    public SynAnimationCommand (SynAnimator anAnimator, int x, int y){
    	theAnimator = anAnimator;
    	xMove = x;
    	yMove = y;
    }

    public void run() {
    	theAnimator.move(xMove, yMove);
	}  
}
