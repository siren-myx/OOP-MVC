package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@Tags({"Bounded Shape"})
public interface Angle extends Point{	
	public Line getLeftArm();
	public Line getRightArm();
	public Line getLeftLeg();
	public Line getRightLeg();
	public int getArmX();
	public int getArmY();
	public int getLegX();
	public int getLegY();
	public void setArmLocation(int newX, int newY);
	public void setLegLocation(int newX, int newY);
	public void rotateLeftArm(int newWidth, int newHeight);
	public void rotateRightArm(int newWidth, int newHeight);
	public void rotateLeftLeg(int newWidth, int newHeight);
	public void rotateRightLeg(int newWidth, int newHeight);
}
