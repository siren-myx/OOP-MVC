package mvc;

import graphics.BridgeScene;

import java.awt.event.MouseEvent;

import sceneViews.ABridgeSceneView;
import util.annotations.Tags;
@Tags({"Bridge Scene Controller"})
public class ABridgeSceneMouseController implements BridgeSceneMouseController{
	BridgeScene aScene;
	ABridgeSceneView aView;
    
    public ABridgeSceneMouseController (BridgeScene newScene, ABridgeSceneView newView) {
    	aScene = newScene;
    	aView = newView;
    	aView.addMouseListener(this);
    }   
    public void mouseClicked(MouseEvent e) {
		System.out.println();		
		System.out.println("Mouse Click Location    X: "
				+ e.getX() + "\t Y: " + e.getY());
    } 
    
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}  
}
