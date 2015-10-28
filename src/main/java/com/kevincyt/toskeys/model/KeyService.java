package com.kevincyt.toskeys.model;

import java.io.File;
import java.util.Map;

public class KeyService {
	// VARS
	private File xmlReferenceFile;
	private Map<String, Hotkey> keyMap;

	public KeyService() {
	}

	public void readKeybindings(File xmlFile) {
		xmlReferenceFile = xmlFile;
		Map<String, Hotkey> newKeyMap = KeyBindingsParser.readFromXML(xmlFile);
		if(keyMap == null) {
			keyMap = newKeyMap;
		} else{
			Hotkey target, temp;
			for(String id : newKeyMap.keySet()){
				target = keyMap.get(id);
				temp = newKeyMap.get(id);
				target.getKeyProperty().setValue(temp.getKey());
				target.getUseAltProperty().setValue(temp.useAlt());
				target.getUseCtrlProperty().setValue(temp.useCtrl());
				target.getUseShiftProperty().setValue(temp.useShift());
			}
		}
	}

	/**
	 * Saves the key bindings in the destination file.
	 */
	public void saveKeybindings(File destination) {
		KeyBindingsParser.writeToXml(destination, xmlReferenceFile, keyMap);
	}
	
	/**
	 * Saves the key bindings in the original file.
	 */
	public void saveKeybindings(){
		KeyBindingsParser.writeToXml(xmlReferenceFile, xmlReferenceFile, keyMap);
	}

	public Map<String, Hotkey> getKeyMap() {
		return keyMap;
	}

}
