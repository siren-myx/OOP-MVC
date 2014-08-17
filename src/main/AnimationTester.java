package main;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JFrame;

import sceneViews.ABridgeSceneView;
import sceneViews.AConsoleSceneView;
import sceneViews.ConsoleSceneView;
import commandInterpreter.ACommandInterpreter;
import commandInterpreter.CommandInterpreter;
import mvc.ABridgeSceneMouseController;
import mvc.AnAvatarController;
import mvc.AvatarController;
import mvc.BridgeSceneMouseController;
import graphics.ABridgeScene;
import graphics.BridgeScene;
import animationCommands.AClearanceManager;
import animationCommands.ClearanceManager;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;

public class AnimationTester {
	public static void main(String[] args){	  
		ClearanceManager theManager = new AClearanceManager();
		CommandInterpreter interpreter = new ACommandInterpreter(theManager);
		BridgeScene theScene = new ABridgeScene(interpreter);
		
		ABridgeSceneView sceneView = new ABridgeSceneView(theScene);
		interpreter.setScene(theScene);
		JFrame frame = new JFrame("A Scene Paint View");
		frame.add((Component) sceneView);
		frame.setSize(1060, 650);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);
		
		AvatarController controller = new AnAvatarController(interpreter);
		controller.setModel(theScene);
		
		ConsoleSceneView theMonitor = new AConsoleSceneView(theScene);
		
		BridgeSceneMouseController mouseController = new ABridgeSceneMouseController(theScene, sceneView);
		
		OEFrame editor = ObjectEditor.edit(theManager);	
		editor.setLocation(560, 525);
		editor.setSize(220,150);	
	}
}