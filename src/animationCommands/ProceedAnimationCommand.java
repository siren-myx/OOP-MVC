package animationCommands;

import util.annotations.Tags;
@Tags({"Proceed"})
public class ProceedAnimationCommand implements Runnable {
	ClearanceManager theManager;
	
    public ProceedAnimationCommand (ClearanceManager aManager){
    	theManager = aManager;
    }

    public void run() {
    	theManager.proceed();   
	}  
}
