package com.kevincyt.toskeys.gui.main;

import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.inject.Inject;

import com.airhacks.afterburner.views.FXMLView;
import com.kevincyt.toskeys.gui.key.KeyPresenter;
import com.kevincyt.toskeys.gui.key.KeyView;
import com.kevincyt.toskeys.gui.menubar.MenubarPresenter;
import com.kevincyt.toskeys.model.Hotkey;
import com.kevincyt.toskeys.model.KeyService;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Pane;

public class MainPresenter implements Initializable {
	
	@FXML private Pane main;
	
	@FXML private MenuBar menubar;
	@FXML public MenubarPresenter menubarController;
	
	@Inject private KeyService keyService;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Node> nodes = main.getChildren();
		Map<String, Hotkey> keyMap = keyService.getKeyMap();
		FXMLView view;
		KeyPresenter presenter;
		Hotkey h;
		SortedSet<String> sorted = new TreeSet<String>(keyMap.keySet());
		for(String id : sorted){
			view = new KeyView();
			presenter = (KeyPresenter)view.getPresenter();
			h = keyMap.get(id);
			
			presenter.id.setText(id);
			presenter.key.textProperty().bindBidirectional(h.getKeyProperty());
			presenter.alt.selectedProperty().bindBidirectional(h.getUseAltProperty());
			presenter.ctrl.selectedProperty().bindBidirectional(h.getUseCtrlProperty());
			presenter.shift.selectedProperty().bindBidirectional(h.getUseShiftProperty());
			
			nodes.add(view.getView());
		}
	}
	
}
