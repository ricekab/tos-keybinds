package com.kevincyt.toskeys;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import com.kevincyt.toskeys.gui.main.MainPresenter;
import com.kevincyt.toskeys.gui.main.MainView;
import com.kevincyt.toskeys.model.KeyService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		KeyService service = new KeyService();
		// Have to read a default file to populate the main view
		service.readKeybindings(new File(System.getProperty("user.dir") + "\\hotkey_default.xml"));
		Map<Object, Object> injectorSource = new HashMap<Object, Object>();
		injectorSource.put("keyService", service);
		
		Injector.setConfigurationSource(injectorSource::get);
		
		FXMLView view = new MainView();
		stage.setTitle("Tree of Savior key binder");
		stage.setScene(new Scene(view.getView()));
		stage.show();
		
		// Open dialog to select xml file
		MainPresenter presenter = (MainPresenter)view.getPresenter();
		presenter.menubarController.openHotkeyFile();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
