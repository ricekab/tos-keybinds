package com.kevincyt.toskeys.gui.menubar;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import com.kevincyt.toskeys.model.KeyService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MenubarPresenter implements Initializable {

	@FXML private MenuBar mbar;

	@Inject private KeyService keyService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@FXML
	public void openHotkeyFile() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Select hotkey file");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().add(new ExtensionFilter("XML files", "*.xml"));
		File selectedFile = fc.showOpenDialog(mbar.getScene().getWindow());
		if(selectedFile != null) {
			keyService.readKeybindings(selectedFile);
		}
	}

	@FXML
	private void saveHotkeys() {
		keyService.saveKeybindings();
	}

	@FXML
	private void saveHotkeysAs() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Save hotkeys as...");
		fc.setInitialDirectory(new File(System.getProperty("user.dir")));
		fc.getExtensionFilters().add(new ExtensionFilter("XML files", "*.xml"));
		File selectedFile = fc.showSaveDialog(mbar.getScene().getWindow());
		if(selectedFile != null) {
			keyService.saveKeybindings(selectedFile);
		}
	}

	@FXML
	private void quitApplication() {
		System.exit(0);
	}
}
