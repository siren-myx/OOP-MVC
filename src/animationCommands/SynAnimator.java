package animationCommands;

import graphics.Avatar;
import util.annotations.Tags;
@Tags({"Animator"})
public class SynAnimator{
	Avatar theAvatar;
	public SynAnimator(Avatar anAvatar){
		theAvatar = anAvatar;
	}
	
	public synchronized void move(int xMove, int yMove){
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
	    			theAvatar.rotateArm(m);
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
					theAvatar.clapLegMove(0,1);
					theAvatar.rotateArm(m);
				}
			}
			if(yMove<0){
				for (int m=0; m>yMove;m--){
					theAvatar.clapLegMove(0,-1);
					theAvatar.rotateArm(m);
				}
			}
		}
	}
    
}

