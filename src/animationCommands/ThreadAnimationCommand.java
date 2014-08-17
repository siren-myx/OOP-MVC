package animationCommands;

import database.Database;
import util.annotations.Tags;
@Tags({"Thread"})
public class ThreadAnimationCommand implements Runnable {
	Database<String, Runnable> theCommandTable;
	String typeValue;
	
    public ThreadAnimationCommand (String commandName,
    		Database<String, Runnable> aCommandTable) {
    	theCommandTable = aCommandTable;
    	typeValue = commandName;
    }

    public void run() {
		new Thread(theCommandTable.get(typeValue)).start();
		}  
}