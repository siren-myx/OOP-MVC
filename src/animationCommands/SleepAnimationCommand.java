package animationCommands;

import util.annotations.Tags;
@Tags({"Sleep"})
public class SleepAnimationCommand implements Runnable {
	int sleepTime;
	
    public SleepAnimationCommand (int timeInterval){
    	sleepTime = timeInterval;
    }

    public void run() {
	  ThreadSupport.sleep(sleepTime); 
	}  
}
