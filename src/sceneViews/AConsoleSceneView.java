package sceneViews;
import graphics.Avatar;
import graphics.BridgeScene;

import java.beans.PropertyChangeEvent;

import util.annotations.Tags;
@Tags({"Console Scene View"})
public class AConsoleSceneView implements ConsoleSceneView {
	BridgeScene aScene;
	Avatar[] avatars;
	String name = "";

	public AConsoleSceneView(BridgeScene theScene) {
		aScene = theScene;
		avatars = theScene.getAvatars();
		
		aScene.addPropertyChangeListener(this);	
		
//		for (Avatar anAvatar : avatars){
//			anAvatar.getText().addPropertyChangeListener(this);
//			anAvatar.getImage().addPropertyChangeListener(this);
//			anAvatar.getBodyLine().addPropertyChangeListener(this);
//			anAvatar.getAngleLines().addPropertyChangeListener(this);
//		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		System.out.println(arg0);
		System.out.println();	

		
//		if (arg0.getSource() == avatars[0].getText()		||
//			arg0.getSource() == avatars[0].getImage()		||
//			arg0.getSource() == avatars[0].getBodyLine()	||
//			arg0.getSource() == avatars[0].getAngleLines())
//			name = "Arthur";
//		else if (arg0.getSource() == avatars[1].getText()		||
//				 arg0.getSource() == avatars[1].getImage()		||
//				 arg0.getSource() == avatars[1].getBodyLine()	||
//				 arg0.getSource() == avatars[1].getAngleLines())
//			name = "Lancelot";
//		else if (arg0.getSource() == avatars[2].getText()		||
//				 arg0.getSource() == avatars[2].getImage()		||
//				 arg0.getSource() == avatars[2].getBodyLine()	||
//				 arg0.getSource() == avatars[2].getAngleLines())
//			name = "Robin";
//		else if (arg0.getSource() == avatars[3].getText()		||
//				 arg0.getSource() == avatars[3].getImage()		||
//				 arg0.getSource() == avatars[3].getBodyLine()	||
//				 arg0.getSource() == avatars[3].getAngleLines())			
//			name = "Galahad";
//		else if (arg0.getSource() == avatars[4].getText()		||
//				 arg0.getSource() == avatars[4].getImage()		||
//				 arg0.getSource() == avatars[4].getBodyLine()	||
//				 arg0.getSource() == avatars[4].getAngleLines())			
//			name = "Guardian";
//		
//		System.out.println();	
//		System.out.println("Avatar: " + name + "\tSources: " + arg0.getSource()+
//				"\t Property name: " + arg0.getPropertyName() + "\told value: "
//				+ arg0.getOldValue() + "\tnew value: " + arg0.getNewValue());
	}
}
