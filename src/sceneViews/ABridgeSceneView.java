package sceneViews;

import graphics.Angle;
import graphics.Avatar;
import graphics.BodyLine;
import graphics.BridgeScene;
import graphics.ImageShape;
import graphics.Oval;
import graphics.StringShape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import util.annotations.Tags;
import util.models.PropertyListenerRegisterer;
@Tags({"Inheriting Bridge Scene Painter"})
public class ABridgeSceneView extends Component implements PropertyChangeListener{
	BridgeScene aScene;
	Avatar[] avatars;
	boolean checkFailShape;
	int TEXT_POINT=15, HEAD_IMAGE_POINT=76;
    BasicStroke dotted = new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 
            1f, new float[] {0.1f}, 0f);
    
    public ABridgeSceneView(BridgeScene theScene){
    	aScene = theScene;
    	
		avatars = theScene.getAvatars();
		
		for (Avatar anAvatar : avatars){
			checkFailShape = anAvatar.checkFailShape();
			anAvatar.getText().addPropertyChangeListener(this);
			anAvatar.getImage().addPropertyChangeListener(this);
			anAvatar.getBodyLine().addPropertyChangeListener(this);
			anAvatar.getAngleLines().addPropertyChangeListener(this);
		}
		
		aScene.getBridgeAndGorge().addPropertyChangeListener(this);
		aScene.getKightStandingAreas().addPropertyChangeListener(this);
		aScene.getGuardStandingAreas().addPropertyChangeListener(this);
    }
   
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(dotted); 
        g.setColor(Color.BLACK);
        draw(g2, aScene);
    }
        
    public void draw(Graphics2D g, BridgeScene theScene) {
    	draw(g, theScene.getBridgeAndGorge());
    	draw(g, theScene.getKightStandingAreas());
    	draw(g, theScene.getGuardStandingAreas());
		
		for (Avatar anAvatar : avatars){
			checkFailShape = anAvatar.checkFailShape();
	    	draw(g, anAvatar.getAngleLines());
	    	draw(g, anAvatar.getBodyLine());
	    	draw(g, anAvatar.getText());
	    	draw(g, anAvatar.getImage());
		}
    }

    public void draw(Graphics2D g, BodyLine aBodyLine){   	
    	if (checkFailShape) {
    		g.drawLine(aBodyLine.getX(), aBodyLine.getY(), 
    			aBodyLine.getX()-38, aBodyLine.getY());
    		}
    	else {g.drawLine(aBodyLine.getX(), aBodyLine.getY(), aBodyLine.getX(),
        			aBodyLine.getHeight()+aBodyLine.getY());
    		}
    }
    
    public void draw(Graphics2D g, Angle anAngle) {
    	g.drawLine(anAngle.getArmX(), anAngle.getArmY(), 
    			anAngle.getLeftArm().getWidth()+anAngle.getArmX(), 
    			anAngle.getLeftArm().getHeight()+anAngle.getArmY());
    	
    	g.drawLine(anAngle.getArmX(), anAngle.getArmY(), 
    			anAngle.getRightArm().getWidth()+anAngle.getArmX(), 
    			anAngle.getRightArm().getHeight()+anAngle.getArmY());
    
    	g.drawLine(anAngle.getLegX(), anAngle.getLegY(), 
    			anAngle.getLeftLeg().getWidth()+anAngle.getLegX(), 
    			anAngle.getLeftLeg().getHeight()+anAngle.getLegY());
    
    	g.drawLine(anAngle.getLegX(), anAngle.getLegY(), 
    			anAngle.getRightLeg().getWidth()+anAngle.getLegX(), 
    			anAngle.getRightLeg().getHeight()+anAngle.getLegY());
    }
    
    public void draw(Graphics2D g, Oval anOval) {
    	g.drawOval(anOval.getX(), anOval.getY(), anOval.getWidth(), anOval.getHeight());    	
    }
        
    public void draw(Graphics2D g, StringShape aLabel) {
        String s = aLabel.getText();
        g.drawString(s, aLabel.getX(), aLabel.getY()+TEXT_POINT);      
    }
    
    public void draw(Graphics2D g, ImageShape anImage) {
        Image img = Toolkit.getDefaultToolkit().getImage(anImage.getImageFileName());
        g.drawImage(img, anImage.getX(), anImage.getY()+HEAD_IMAGE_POINT, this);     
    }
	
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		repaint();
	}
	
	public void register(PropertyListenerRegisterer aPropertyChangeRegister){
		aPropertyChangeRegister.addPropertyChangeListener(this);        
	}
	
}
