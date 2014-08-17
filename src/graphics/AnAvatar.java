package graphics;
import animationCommands.ThreadSupport;
import util.annotations.Tags;
import util.annotations.StructurePattern;
import util.annotations.StructurePatternNames;
@StructurePattern(StructurePatternNames.BEAN_PATTERN)
public class AnAvatar extends APoint implements Avatar {
	BodyLine bodyLine;
	Angle angleLines;
	StringShape theText;
	ImageShape headImage;
	boolean failShape;
	int count=0;
		
	public AnAvatar (String aText, int iX, int iY, String imageName){
		super(iX, iY);
		angleLines = new AAngle(iX+24, iY+72);
		bodyLine = new ABodyLine(iX+24, iY+60, 0, 40);
		theText = new AStringShape(aText, iX, iY);
		headImage = new AShapeImage(imageName, iX, iY-48, 100,200);
	}

	public BodyLine getBodyLine(){return bodyLine;}	
	public StringShape getText(){return theText;}
	public ImageShape getImage(){return headImage;}
	public Angle getAngleLines(){return angleLines;}
	
	public void failShapeOn(){failShape=true;}
	public void failShapeOff(){failShape=false;}
	public boolean checkFailShape(){return failShape;}

	@Tags({"Moving VShape"})
	public void moveVShape(int newX, int newY){
		angleLines.setArmLocation(angleLines.getArmX() + newX, angleLines.getArmY() + newY);
		angleLines.setLegLocation(angleLines.getLegX() + newX, angleLines.getLegY() + newY);
	}
	
	@Tags({"Moving Line"})
	public void moveLine(int newX, int newY){
		bodyLine.setX(bodyLine.getX()+newX);
		bodyLine.setY(bodyLine.getY()+newY);
	}
	
	@Tags({"Moving Text"})	
	public void moveText(int newX, int newY){
		theText.setX(theText.getX()+newX);
		theText.setY(theText.getY()+newY);
	}
	
	@Tags({"Moving Image"})	
	public void moveImage(int newX, int newY){
		headImage.setX(headImage.getX()+newX);
		headImage.setY(headImage.getY()+newY);
	}
	
	@Tags({"Moving Coordinate Location"})
	public void moveCoordianteLocation(int newX, int newY){
		setX(getX()+newX);
		setY(getY()+newY);
	}
	
	@Tags({"Moving All"})	
	public void moveAll(int newX, int newY){
		moveLine(newX, newY);
		moveText(newX, newY);
		moveImage(newX, newY);
		moveVShape(newX, newY);
		moveCoordianteLocation(newX, newY);
	}
	
	
	@Tags({"Moving with Claping Legs"})	
	public void clapLegMove(int newX, int newY){
		moveText(newX, newY);
		moveImage(newX, newY);
		moveLine(newX, newY);
		moveVShape(newX, newY);
		clapLeg();
		moveCoordianteLocation(newX, newY);
		}
	
	
	@Tags({"Rotate LeftArm"})	
	public void rotateLeftArm(int index){
		int width = (int)(32*(Math.cos(-index*(Math.PI/36))));
		int height = (int)(32*(Math.sin(index*(Math.PI/36))));
		angleLines.rotateLeftArm(width, height);
	}
	
	@Tags({"Rotate LeftLeg"})
	public void rotateLeftLeg(int index){
		int width = (int)(32*(Math.cos(-index*(Math.PI/36))));
		int height = (int)(32*(Math.sin(index*(Math.PI/36))));
		angleLines.rotateLeftLeg(width, height);
	}
	
	@Tags({"Rotate RightArm"})
	public void rotateRightArm(int index){
		int width = (int)(32*(Math.cos(-index*(Math.PI/36))));
		int height = (int)(32*(Math.sin(index*(Math.PI/36))));
		angleLines.rotateRightArm(width, height);
	}
	
	@Tags({"Rotate RightLeg"})
	public void rotateRightLeg(int index){
		int width = (int)(32*(Math.cos(-index*(Math.PI/36))));
		int height = (int)(32*(Math.sin(index*(Math.PI/36))));
		angleLines.rotateRightLeg(width, height);
	}
	
	@Tags({"Rotate Arms"})
	public void rotateArm(int index){
		rotateLeftArm(index);
		rotateRightArm(-index);
	}
	
	@Tags({"Rotate Legs"})
	public void rotateLeg(int index){
		rotateLeftLeg(index);
		rotateRightLeg(-index);
	}	
	
	@Tags({"Rotate All"})
	public void rotateAll(int index){
		rotateLeftArm(index);
		rotateLeftLeg(index);
		rotateRightArm(index);
		rotateRightLeg(index);
	}
	
	@Tags({"Clap Arms"})
	public void clapArm(){
		if((count%2)==0){				
			rotateRightArm(18);
			rotateLeftArm(18);
		}
		else{
			rotateLeftArm(7);
			rotateRightArm(29);
		}
    	ThreadSupport.sleep(15);
	}
	
	@Tags({"Clap Legs"})
	public void clapLeg(){
		count++;
		if((count%2)==0){	
			rotateLeftLeg(7);
			rotateRightLeg(29);
		}
		else{
			rotateRightLeg(18);
			rotateLeftLeg(18);
		}
    	ThreadSupport.sleep(15);
	}
	
	@Tags({"Clap All"})
	public void clapAll(){
		moveLine(0, 0);
		moveText(0, 0);
		moveImage(0, 0);
		moveVShape(0, 0);
		clapArm();
		clapLeg();
	}
}

