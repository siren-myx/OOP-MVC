package animationCommands;

import database.Database;
import util.annotations.Tags;
@Tags({"Call"})
public class CallAnimationCommand implements Runnable {
	Database<String, Runnable> theCommandTable;
	String typeValue;
	
    public CallAnimationCommand (String commandName,
    		Database<String, Runnable> aCommandTable) {
    	theCommandTable = aCommandTable;
    	typeValue = commandName;
    }

    public void run() {
    	theCommandTable.get(typeValue);
	}  
}