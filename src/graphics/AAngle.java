package graphics;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
import util.annotations.Tags;
@StructurePattern(StructurePatternNames.BEAN_PATTERN) 
@Tags({"Bounded Shape"})
public class AAngle extends APoint implements Angle{
    int width, height;
    Line[] lines = new Line[4];    

    public AAngle (int iX, int iY) {
    	super(iX, iY);
    	width = 24;
    	height = 16;
    	lines[0] = new ALine(iX, iY, width, height);
    	lines[1] = new ALine(iX, iY, -width, height);
    	lines[2] = new ALine(iX, iY+28, width, height);
    	lines[3] = new ALine(iX, iY+28, -width, height);
   } 

	public void setArmLocation(int newX, int newY) { 
		lines[0].setX(newX);
		lines[0].setY(newY);
		lines[1].setX(newX);
		lines[1].setY(newY);
	}
	public void setLegLocation(int newX, int newY) {
		lines[2].setX(newX);
		lines[2].setY(newY);
		lines[3].setX(newX);
		lines[3].setY(newY);
	}

	public void rotateLeftArm(int newWidth, int newHeight){
		lines[0].setWidth(newWidth);
		lines[0].setHeight(newHeight);
	}
	public void rotateRightArm(int newWidth, int newHeight){
		lines[1].setWidth(newWidth);
		lines[1].setHeight(newHeight);
	}
	public void rotateLeftLeg(int newWidth, int newHeight){
		lines[2].setWidth(newWidth);
		lines[2].setHeight(newHeight);
	}
	public void rotateRightLeg(int newWidth, int newHeight){
		lines[3].setWidth(newWidth);
		lines[3].setHeight(newHeight);
	}
	public Line getLeftArm() {return lines[0];}
	public Line getRightArm() {return lines[1];}
	public Line getLeftLeg() {return lines[2];}
	public Line getRightLeg() {return lines[3];}
	public int getArmX() { return lines[1].getX();}
	public int getArmY() { return lines[1].getY();}
	public int getLegX() { return lines[2].getX();}
	public int getLegY() { return lines[2].getY();}
}
