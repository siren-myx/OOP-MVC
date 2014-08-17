package graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.STRING_PATTERN)
@Tags({"Bounded Shape"})
public class AStringShape extends APoint implements StringShape{
	String text;
		
	public AStringShape(String initText, int iX, int iY) {
		super(iX, iY);
		text = initText;
	}
	
    public void setText(String newVal) {
    	String oldVal = text;
        text = newVal;
        propertySupport.notifyAllListeners(new PropertyChangeEvent(this, "Text", oldVal,
                newVal));
    }
    
	public void clear() { text = " ";}  
	public String getText() {return text;}
    
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.add(listener);  
    }
}
