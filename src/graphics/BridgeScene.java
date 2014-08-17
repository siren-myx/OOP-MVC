package graphics;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;

public interface BridgeScene extends PropertyChangeListener, PropertyListenerRegisterer{
	public Oval getKightStandingAreas();
	public Oval getGuardStandingAreas();
	public ImageShape getBridgeAndGorge();
	public KightTurn getKightTurn();
	public void say(String text);
	public void approach();
	public void pass();
	public void fail();
	public void move();
	public Avatar[] getAvatars();
	
	public boolean preSpeak();
	public boolean prePass();
	public boolean preApproach();
	public boolean preFail();
	
	public void setSpeak();
	public void setSpeakExit();
	public void setPass();
	public void setPassExit();
	public void setFail();
	public void setFailExit();
	public void setApproach();
	public void setApproachExit();
}
