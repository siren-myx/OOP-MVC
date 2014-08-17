package graphics;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.models.PropertyListenerRegisterer;
@StructurePattern(StructurePatternNames.POINT_PATTERN)
@Tags({"Locatable"})
public interface Point extends PropertyListenerRegisterer{
	public int getX(); 
	public int getY(); 
	public void setX(int newX);
	public void setY(int newY);

}
