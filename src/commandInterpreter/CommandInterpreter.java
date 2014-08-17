package commandInterpreter;

import tokens.Token;
import graphics.Avatar;
import graphics.BridgeScene;
import database.Database;
public interface CommandInterpreter{
	public void setScene(BridgeScene theScene);
	public void setCommand(String newVal);
	public void setAvatarData(Database<String, Avatar> data);
	public void setInterpreter(Token[] newVal);
	public void setAvatarIndex(Token aToken);
	public void setWords(Token aToken);
	public void setErrorReport();
	public void resetErrorReport();
	public void clear();

	public void asynchronousAll();
	public void waitingAll();
	public void startAnimation();
	public void runInputCommand();
	
	public Runnable parseCommand();

	public int getAvatarIndex();

	public String getWords();
	public String getCommand();
	public String getErrorReport();

}
