package mvc;

import graphics.BridgeScene;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import tokens.ScannerBean;
import tokens.ScannerBeanSheet;
import commandInterpreter.CommandInterpreter;

public class AnAvatarController implements AvatarController, ActionListener{
	JFrame buttonFrame, commandFrame;
	JButton button, button1, button2, button3, button4, button5, button6;
	JTextField textField, commandText, errorText;
	BridgeScene aScene;
	CommandInterpreter theInterpreter;
	ScannerBeanSheet beanBox;
	String INPUT_INSPECTOR = "Error Checking: ";
	
	
    public AnAvatarController(CommandInterpreter anInterpreter) {
        theInterpreter = anInterpreter;
        theInterpreter.waitingAll();
        beanBox = new ScannerBean(anInterpreter);

        buttonFrame = new JFrame("Avatar Controller");
		button = new JButton("Enter Input");
		button2 = new JButton("SignedMove Demo");
		button3 = new JButton("RepeatCommand Demo");
		button4 = new JButton("ArmRotateCommand Demo");
		button5 = new JButton("AsynchronousAll Demo");
		button6 = new JButton("CommandList Demo");

	
		OEFrame sceneEditor = ObjectEditor.edit(buttonFrame);
		sceneEditor.dispose();

		textField = new JTextField();			
		errorText = new JTextField();
		errorText.setText(INPUT_INSPECTOR);

		buttonFrame.add(textField);
		buttonFrame.setSize(280, 250);
		buttonFrame.setLocation(280, 650);
		buttonFrame.setVisible(true);
		buttonFrame.setLayout(new GridLayout(8,1)); 
		buttonFrame.add(textField);
		buttonFrame.add(errorText);
		buttonFrame.add(button);
		buttonFrame.add(button2);
		buttonFrame.add(button3);
		buttonFrame.add(button4);
		buttonFrame.add(button5);
		buttonFrame.add(button6);

		
        button.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        textField.addActionListener(this);
    }
    
	public void setModel(BridgeScene newScene) {
		aScene = newScene;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getActionCommand()=="ArmRotateCommand Demo"){
			textField.setText("{ rotateLeftArm Robin +200 rotateRightArm Guardian -300 } ");
			theInterpreter.setCommand("{ rotateLeftArm Robin +200 rotateRightArm Guardian -300 } ");
			beanBox.setAnalyzer(); 
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="AsynchronousAll Demo"){
			theInterpreter.asynchronousAll();
		}
		else if (arg0.getActionCommand()=="RepeatCommand Demo"){
			textField.setText("repeat 5 repeat 2 { move Robin +50 +30 move Lancelot 40 10 } ");
			theInterpreter.setCommand("repeat 5 repeat 2 { move Robin +50 +30 move Lancelot 40 10 } ");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="SignedMove Demo"){
			textField.setText("move Arthur +200 -80 ");
			theInterpreter.setCommand("move Arthur +200 -80 ");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="CommandList Demo"){
			textField.setText("{ Approach Arthur say \"Hello\" say \"I want pass\" say \"No.\" +"
					+ " say \"Sad.\" fail }");
			theInterpreter.setCommand("{ Approach Arthur say \"Hello\" say \"I want pass\" say \"No.\" +"
					+ " say \"Sad.\" fail }");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="Repeat+Move Demo"){
			textField.setText("repeat 5 repeat 2 { move Robin +20 +30 move Lancelot 10 10 } ");
			theInterpreter.setCommand("repeat 5 repeat 2 { move Robin +20 +30 move Lancelot 10 10 } ");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="Fail"){
			textField.setText("Fail ");
			theInterpreter.setCommand("Fail ");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="Pass"){
			textField.setText("Pass ");
			theInterpreter.setCommand("Pass ");
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
		}
		else if (arg0.getActionCommand()=="All Commands Demo"){
		}
		else if (arg0.getActionCommand()=="Enter Input"){
			theInterpreter.setCommand(textField.getText());
			beanBox.setAnalyzer();
			theInterpreter.runInputCommand();
			errorText.setText(INPUT_INSPECTOR+theInterpreter.getErrorReport());
		}
	}

	public void processInput() {}
}
