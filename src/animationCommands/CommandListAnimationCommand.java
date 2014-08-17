package animationCommands;
import java.util.List; 
import java.util.ArrayList; 

import util.annotations.Tags;
@Tags({"Command List"})
public class CommandListAnimationCommand implements Runnable{
	
	protected List<Runnable> commands = new ArrayList<Runnable>();
        
	@Tags({"add"})
	public void add(Runnable command){
		commands.add(command);
	}
	
	public void clean() { commands.clear();}

	
	public void run(){
		synchronized(this) {
			for (int i =0; i<commands.size();i++){
				if(!(commands.get(i)==null)) { 
					commands.get(i).run();
				}
			}
		}
	}
}

