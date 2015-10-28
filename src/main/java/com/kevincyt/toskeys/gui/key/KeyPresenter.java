package com.kevincyt.toskeys.gui.key;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.kevincyt.toskeys.model.KeyService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class KeyPresenter implements Initializable {
	
	@Inject private KeyService keyService;
	
	@FXML private Label id;
	@FXML private Button key;
	@FXML private CheckBox shift, ctrl, alt;
	
	private ButtonKeyEventHandler keyEventHandler;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		keyEventHandler = new ButtonKeyEventHandler(key);
		key.setOnAction(event -> {
			keyEventHandler.prepare();
			System.out.println("Add Handler");
			key.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);
		}); 

	}

}
