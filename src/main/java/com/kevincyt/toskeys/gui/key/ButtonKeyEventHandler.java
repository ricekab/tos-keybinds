package com.kevincyt.toskeys.gui.key;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ButtonKeyEventHandler implements EventHandler<KeyEvent> {
	private Button src;
	private String prevKey;

	public ButtonKeyEventHandler(Button source) {
		this.src = source;
	}

	@Override
	public void handle(KeyEvent event) {
		System.out.println("Code: " + event.getCode());
		if(event.getCode() == KeyCode.ESCAPE) {
			src.setText(prevKey);
		} else {
			src.setText(event.getCode().toString());
		}
		System.out.println("Handler removed");
		src.removeEventFilter(KeyEvent.KEY_PRESSED, this);
	}

	public void prepare() {
		prevKey = src.getText();
		src.setText("...");
	}
}