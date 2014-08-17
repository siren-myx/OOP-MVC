package animationCommands;

import util.annotations.Tags;
@Tags({"Repeat"})
public class RepeatAnimationCommand implements Runnable {
    int repeatCount;
    Runnable repeatedParsing;
    
    public RepeatAnimationCommand (int number, Runnable parseCommand) {
    	repeatCount = number;
    	repeatedParsing = parseCommand;
    }
    
    public void run() {
    	for(int i=0; i<repeatCount;i++){
    		repeatedParsing.run();
    		}
    	
    }

}
