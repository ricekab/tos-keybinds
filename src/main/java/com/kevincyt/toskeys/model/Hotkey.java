package com.kevincyt.toskeys.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Hotkey {
	// Properties
	private final StringProperty key;
	private final BooleanProperty useShift, useAlt, useCtrl;

	public Hotkey(String key, boolean useShift, boolean useAlt, boolean useCtrl) {
		this.key = new SimpleStringProperty(key);
		this.useShift = new SimpleBooleanProperty(useShift);
		this.useAlt = new SimpleBooleanProperty(useAlt);
		this.useCtrl = new SimpleBooleanProperty(useCtrl);
	}

	public Hotkey(String key) {
		this(key, false, false, false);
	}

	public StringProperty getKeyProperty() {
		return key;
	}

	public BooleanProperty getUseShiftProperty() {
		return useShift;
	}

	public BooleanProperty getUseAltProperty() {
		return useAlt;
	}

	public BooleanProperty getUseCtrlProperty() {
		return useCtrl;
	}

}