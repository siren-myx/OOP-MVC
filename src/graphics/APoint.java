package graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.APropertyListenerSupport;
import mvc.PropertyListenerSupport;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Tags({"Locatable"})
public abstract class APoint implements Point {	
	protected int x, y;
	
	PropertyListenerSupport propertySupport = new APropertyListenerSupport();

	public APoint(int theX, int theY) {
		x = theX;
		y = theY;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	
	public void setX(int newX) {
		int oldX = x;
		x = newX;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "X",
				oldX, newX));
		}
	
	public void setY(int newY) {
		int oldY= y;
		y = newY;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Y",
				oldY, newY));
		}
	
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
	}
}
