package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.OVAL_PATTERN) 
@Tags({"Bounded Shape"})
public class AnOval extends ALine implements Oval{
    public AnOval (int iX, int iY, int iW, int iH) {
    	super(iX, iY, iW, iH);   
    }
}
