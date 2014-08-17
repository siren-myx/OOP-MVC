package graphics;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
@Tags({"Occupation"})
public class AnOccupation implements Occupation{
	boolean occupied;
	public AnOccupation(boolean newVal){
		occupied = newVal;
	}
	public void occupied() { occupied = true;}
	public void moveAway() { occupied = false;}
	public boolean getKightOccupying() { return occupied;}
}
