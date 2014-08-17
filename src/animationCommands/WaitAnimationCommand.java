package animationCommands;

import util.annotations.Tags;
@Tags({"Wait"})
public class WaitAnimationCommand implements Runnable {
	ClearanceManager theManager;
	
    public WaitAnimationCommand (ClearanceManager aManager){
    	theManager = aManager;
    }

    public void run() {
    	theManager.waitForProceed();   
	}  
}
