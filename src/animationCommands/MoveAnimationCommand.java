package animationCommands;

import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Move"})
public class MoveAnimationCommand implements Runnable {
	Avatar theAvatar;
	int xMove, yMove;
	
    public MoveAnimationCommand (Avatar anAvatar, int xDistance,
    		int yDistance){
    	theAvatar = anAvatar;
    	xMove = xDistance;
    	yMove = yDistance;
    }

    public void run() {
	    //move forward along X
	    if (xMove>0){
    		int n = 0;
	    	for(int m=0;m<xMove;m++){
	    		theAvatar.clapLegMove(1,0);
	    		//move up along Y
	    		if (yMove>n){
	    			theAvatar.clapLegMove(0,1);
	    			n++;
	    		}
	    		//move down along Y
	    		else if (yMove<n){
	    			theAvatar.clapLegMove(0,-1);
	    			n--;
	    		}
	    	}
	    }
	    
	    //move backward along X
	    if (xMove<0){
    		int n = 0;
	    	for(int m=0;m>xMove;m--){
	    		theAvatar.clapLegMove(-1,0);
	    		//move up along Y
	    		if (yMove>n){
	    			theAvatar.clapLegMove(0,1);
	    			n++;
	    		}
	    		//move down along Y
	    		else if (yMove<n){
	    			theAvatar.clapLegMove(0,1);
	    			n--;
	    		}
	    	}
	    }
	    
	    // move along Y when x=0
	    if (xMove==0 && yMove!=0){
			if (yMove>0){
				for (int m=0;m<yMove;m++){
					theAvatar.moveAll(0,1);
				}
			}
			if(yMove<0){
				for (int m=0; m>yMove;m--){
					theAvatar.moveAll(0,-1);
				}
			}
		}
	}
}


