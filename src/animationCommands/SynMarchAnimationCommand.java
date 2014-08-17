package animationCommands;

import animationCommands.ThreadSupport;
import util.annotations.Tags;
import graphics.Avatar;
@Tags({"Animating Command"})
public class SynMarchAnimationCommand implements Runnable {
	Avatar theAvatar;
	ClearanceManager theManager;
	int xMove, yMove;
	
    public SynMarchAnimationCommand (ClearanceManager aManager, Avatar anAvatar, int x, int y){
    	theManager = aManager;
    	theAvatar = anAvatar;
    	xMove = x;
    	yMove = y;
    }

    public void run() {
    	theManager.waitForProceed();     
		//move forward along X
	    if (xMove>0){
    		int n = 0;
	    	for(int m=0;m<xMove;m++){
	    		theAvatar.moveAll(2,0);
	    		theAvatar.rotateArm(m);
	    		//move up along Y
	    		if (yMove>n){
		        	ThreadSupport.sleep(20);
	    			theAvatar.moveAll(0,1);
	    			n++;
	    		}
	    		//move down along Y
	    		if (yMove<n){
		        	ThreadSupport.sleep(20);
	    			theAvatar.moveAll(0,-1);
	    			n--;
	    		}
	        	ThreadSupport.sleep(20);
	    	}
	    }
	    
	    //move backward along X
	    if (xMove<0){
    		int n = 0;
	    	for(int m=0;m>xMove;m--){
	    		theAvatar.moveAll(-1,0);
	    		theAvatar.rotateArm(m);
	    		//move up along Y
	    		if (yMove>n){
		        	ThreadSupport.sleep(20);
	    			theAvatar.moveAll(0,1);
	    			n++;
	    		}
	    		//move down along Y
	    		if (yMove<n){
		        	ThreadSupport.sleep(20);
	    			theAvatar.moveAll(0,1);
	    			n--;
	    		}
	        	ThreadSupport.sleep(20);
	    	}
	    }
	    // move along Y when x=0
	    if (xMove==0 && yMove!=0){
			if (yMove>0){
				for (int m=0;m<yMove;m++){
					theAvatar.moveAll(0,1);
		    		theAvatar.rotateArm(m);
		        	ThreadSupport.sleep(20);
				}
			}
			if(yMove<0){
				for (int m=0; m>yMove;m--){
					theAvatar.moveAll(0,-1);
		    		theAvatar.rotateArm(m);
		        	ThreadSupport.sleep(20);
				}
			}
		}
		
	}  
}
