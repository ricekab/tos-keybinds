package com.kevincyt.toskeys.gui.key;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;

public class KeyPresenter implements Initializable {

	@FXML public Label id;
	@FXML public Button key;
	@FXML public CheckBox shift, ctrl, alt;

	private ButtonKeyEventHandler keyEventHandler;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		keyEventHandler = new ButtonKeyEventHandler(key);
		key.setOnAction(event -> {
			keyEventHandler.prepare();
			key.addEventFilter(KeyEvent.KEY_PRESSED, keyEventHandler);
		});
	}

}
