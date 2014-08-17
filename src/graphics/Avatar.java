package graphics;

public interface Avatar extends Point{
	public Angle getAngleLines();
	public BodyLine getBodyLine();
	public StringShape getText();	
	public ImageShape getImage();
	
	public void moveAll(int newX, int newY);
	public void clapLegMove(int newX, int newY);
		
	public void rotateLeftArm(int index);
	public void rotateLeftLeg(int index);
	public void rotateRightArm(int index);
	public void rotateRightLeg(int index);
	public void rotateArm(int index);
	public void rotateLeg(int index);
	public void rotateAll(int index);
	
	public void clapArm();
	public void clapLeg();
	public void clapAll();
	
	public void failShapeOn();
	public void failShapeOff();
	public boolean checkFailShape();
}
