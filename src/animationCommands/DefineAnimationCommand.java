package animationCommands;

import database.Database;
import util.annotations.Tags;
@Tags({"Define"})
public class DefineAnimationCommand implements Runnable {
	Database<String, Runnable> theCommandTable;
	String commandName;
	Runnable theCommand;
	
    public DefineAnimationCommand (String name, Runnable aCommand,
    		Database<String, Runnable> aCommandTable) {
    	theCommandTable = aCommandTable;
    	commandName = name;
    	theCommand = aCommand;
    	
    }

    public void run() {
    	theCommandTable.put(commandName, theCommand);
	}  
}
