package graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import commandInterpreter.CommandInterpreter;
import animationCommands.LayAnimationCommand;
import animationCommands.MoveAnimationCommand;
import animationCommands.ThreadSupport;
import mvc.APropertyListenerSupport;
import mvc.PropertyListenerSupport;
import database.ADatabase;
import database.Database;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"BridgeScene"})
public class ABridgeScene implements BridgeScene{
	Thread sayThread, moveThread, failThread, failThread2;
	Oval kightStandingArea, guardStandingArea;
	Database<String, Avatar> avatarData = new ADatabase<String, Avatar>();
	CommandInterpreter theInterpreter;
	ImageShape bridgeAndGorge;
	KightTurn aTurn;
	
	boolean kightTurn, guardianTurn, speakTurn, failTurn, approachTurn, passTurn;
	final int AVATAR_SIZE=5, APPROACH_X = 410, APPROACH_Y = 220, FAIL_Y = 415;
	int index = 0, pass_x = 960, failX = 720;
	
	Avatar[] avatars = new Avatar[AVATAR_SIZE];


	PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	public ABridgeScene(CommandInterpreter tokens){
		bridgeAndGorge = new AShapeImage("BridgeAndGorge.jpg", 610, 180, 400, 350);
		
		avatars[0] = new AnAvatar("Arthur",40,50,"Arthur.jpg");
		avatarData.put("Arthur", avatars[0]);
		
		avatars[1] = new AnAvatar("Lancelot", 120, 190,"Lancelot.jpg");
		avatarData.put("Lancelot", avatars[1]);
		
		avatars[2] = new AnAvatar("Robin",120,50,"Robin.jpg");
		avatarData.put("Robin", avatars[2]);
		
		avatars[3] = new AnAvatar("Galahad",40,190,"Galahad.jpg");
		avatarData.put("Galahad", avatars[3]);
		
		avatars[4] = new AnAvatar("Guardian",515, 220,"Guardian.jpg");
		avatarData.put("Guardian", avatars[4]);
		
		kightStandingArea = new AnOval(400, 320, 80, 30);
		guardStandingArea = new AnOval(500, 320, 80, 30);
		
		aTurn = new AKightTurn(false);
		approachTurn=true;
		
		theInterpreter = tokens;
		theInterpreter.setAvatarData(avatarData);
		
		
		for (Avatar anAvatar : avatars){
			anAvatar.addPropertyChangeListener(this);
		}
	}		
	
	public boolean preSpeak(){
		return speakTurn;
	}
	public boolean prePass(){
		return passTurn;
	}
	public boolean preApproach(){
		return approachTurn;
	}
	public boolean preFail(){
		return failTurn;
	}
	
	
	public void setSpeak(){
		speakTurn = true;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Speak", true));
	}
	public void setSpeakExit(){
		speakTurn = false;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Say", false));
	}
	public void setPass(){
		passTurn = true;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Pass", true));
	}
	public void setPassExit(){
		passTurn = false;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Pass", false));
	}
	public void setFail(){
		failTurn = true;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Fail", true));
	}
	public void setFailExit(){
		failTurn = false;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Fail", false));
	}
	public void setApproach(){
		approachTurn = true;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Approach", true));
	}
	public void setApproachExit(){
		approachTurn = false;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "this", "Approach", false));
	}
	
	
	@Tags({"Say"})
	public void say(String text) {		
		assert preSpeak();
		if (!aTurn.getTurn()) { 
			avatars[theInterpreter.getAvatarIndex()].getText().clear();
			avatarData.get("Guardian").getText().setText(text);
			aTurn.setKnightSay();						  		 // A turn to knight to speak.
			ThreadSupport.sleep(550);
		}
		else {
			avatarData.get("Guardian").getText().clear();
			avatars[theInterpreter.getAvatarIndex()].getText().setText(text);
			aTurn.setGuardianSay();								 // A turn to guardian to speak.
			ThreadSupport.sleep(550);
		}
	}
	
	@Tags({"Approach"})
	public void approach(){
		assert preApproach();
		avatars[index].moveAll(APPROACH_X-avatars[index].getX(), 0);
		avatars[index].moveAll(0, APPROACH_Y-avatars[index].getY());
		setApproachExit();
		setSpeak();
		setPass();
		setFail();
		
	}
	
	@Tags({"Pass"})
	public void pass(){
		assert prePass();
		avatars[theInterpreter.getAvatarIndex()].getText().clear();
		moveThread = new Thread(new MoveAnimationCommand(avatars[theInterpreter.getAvatarIndex()],
				pass_x-avatars[theInterpreter.getAvatarIndex()].getX(), 0));
		moveThread.start();
		setSpeakExit();
		setPassExit();
		setFailExit();
		setApproach();
		
		pass_x+=20;
	}	
	

	@Tags({"Fail"})
	public void fail(){		
		assert preFail();
		if (!aTurn.getTurn()) {
			avatars[theInterpreter.getAvatarIndex()].getText().clear();
			
			failThread = new Thread(new MoveAnimationCommand(avatars[theInterpreter.getAvatarIndex()], 
					failX-avatars[theInterpreter.getAvatarIndex()].getX(),0));			
			failThread2 = new Thread(new LayAnimationCommand(avatars[theInterpreter.getAvatarIndex()],0,
					FAIL_Y-avatars[theInterpreter.getAvatarIndex()].getY()));
			
			failThread.start();
			failThread2.start();
		
			setFailExit();
			setApproach();	
			}
		else {
			((Avatar) avatarData.get("Guardian")).getText().clear();
			
			failThread = new Thread(new MoveAnimationCommand(((Avatar) avatarData.get("Guardian")), 
					failX-avatarData.get("Guardian").getX()+90,0));			
			failThread2 = new Thread(new LayAnimationCommand(((Avatar) avatarData.get("Guardian")),0,
					FAIL_Y-avatarData.get("Guardian").getY()));

			failThread.start();
			failThread2.start();	
			
			setApproachExit();
			}
		setSpeakExit();
		failX += 10;	
    }
	
	@Tags({"Move"})
	public void move(){
		avatars[theInterpreter.getAvatarIndex()].getText().clear();
		avatars[theInterpreter.getAvatarIndex()].failShapeOff();
	}
	
	public Avatar[] getAvatars() { return avatars;}
	public ImageShape getBridgeAndGorge() { return bridgeAndGorge;}
	public Oval getKightStandingAreas() { return kightStandingArea;}
	public Oval getGuardStandingAreas() { return guardStandingArea;}
	public KightTurn getKightTurn() { return aTurn;}


	@Override
	public void propertyChange(PropertyChangeEvent evt) {	
	}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);		
	}
}
