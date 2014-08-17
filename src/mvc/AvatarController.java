package mvc;

import graphics.BridgeScene;

import java.awt.event.ActionEvent;

public interface AvatarController {
	public void setModel (BridgeScene theScene);
	 public void actionPerformed(ActionEvent arg0);
	 public void processInput() ;
}
