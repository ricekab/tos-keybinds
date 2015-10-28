package com.kevincyt.toskeys;

import java.util.HashMap;
import java.util.Map;

import com.airhacks.afterburner.injection.Injector;
import com.airhacks.afterburner.views.FXMLView;
import com.kevincyt.toskeys.gui.main.MainView;
import com.kevincyt.toskeys.model.KeyService;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		KeyService service = new KeyService();
		Map<Object, Object> injectorSource = new HashMap<Object, Object>();
		injectorSource.put("keyService", service);
		
		Injector.setConfigurationSource(injectorSource::get);
		
		FXMLView view = new MainView();
		stage.setTitle("Tree of Savior key binder");
		stage.setScene(new Scene(view.getView()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
