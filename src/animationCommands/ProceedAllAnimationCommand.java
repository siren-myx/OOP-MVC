package animationCommands;

import util.annotations.Tags;
@Tags({"Proceed All"})
public class ProceedAllAnimationCommand implements Runnable {
	ClearanceManager theManager;
	
    public ProceedAllAnimationCommand (ClearanceManager aManager){
    	theManager = aManager;
    }

    public void run() {
    	theManager.proceedAll();   
	}  
}
