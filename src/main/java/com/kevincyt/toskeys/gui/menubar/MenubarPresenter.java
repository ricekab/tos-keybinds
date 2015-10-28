package com.kevincyt.toskeys.gui.menubar;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.kevincyt.toskeys.model.KeyService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class MenubarPresenter implements Initializable {
	
	@Inject private KeyService keyService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	@FXML private void openHotkeyFile(){
		
	}
	
	@FXML private void saveHotkeys(){
		
	}
	
	@FXML private void saveHotkeysAs(){
		
	}
	
	@FXML private void quitApplication(){
		System.exit(0);
	}
}
