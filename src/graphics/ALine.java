package graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.LINE_PATTERN) 
@Tags({"Bounded Shape"})
public class ALine extends APoint implements Line{
    protected int width, height;
    
    public ALine (int iX, int iY, int initWidth, int initHeight) {
    	super(iX,iY);
        width = initWidth;
        height = initHeight;    
    }
    
    public void setWidth(int newWidth) {
    	int oldWidth = width;
    	width = newWidth;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Width",
				oldWidth, newWidth));
	}
    
    public void setHeight(int newHeight) {
    	int oldHeight = height;
    	height = newHeight;
		propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Height",
				oldHeight, newHeight));
	}
    
    public int getWidth() {return width;}
    public int getHeight() {return height;}

	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertySupport.add(listener);
	}
}
