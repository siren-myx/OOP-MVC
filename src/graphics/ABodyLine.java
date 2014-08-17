package graphics;

import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.LINE_PATTERN) 
@Tags({"Bounded Shape"})
public class ABodyLine extends ALine implements BodyLine{
	public ABodyLine(int iX, int iY, int initWidth, int initHeight) {
		super(iX, iY, initWidth, initHeight);
	}

}
