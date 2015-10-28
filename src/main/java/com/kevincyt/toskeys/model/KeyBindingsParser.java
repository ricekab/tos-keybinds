package com.kevincyt.toskeys.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javafx.beans.property.StringProperty;

/**
 * Parses key bindings from and to XML format.
 */
public class KeyBindingsParser {

	public static Map<String, Hotkey> readFromXML(File file) {
		Map<String, Hotkey> keyMap = new HashMap<String, Hotkey>();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);

			Node root = doc.getDocumentElement();
			NodeList hotkeyNodes = root.getChildNodes();
			Node hotkey;
			String id, key;
			boolean shift, alt, ctrl;
			for (int i = 0; i < hotkeyNodes.getLength(); i++) {
				hotkey = hotkeyNodes.item(i);
				if(hotkey.hasAttributes()) {
					NamedNodeMap attributes = hotkey.getAttributes();
					id = attributes.getNamedItem("ID").getNodeValue();
					key = attributes.getNamedItem("Key").getNodeValue();
					shift = attributes.getNamedItem("UseShift").getNodeValue().equals("YES") ? true : false;
					alt = attributes.getNamedItem("UseAlt").getNodeValue().equals("YES") ? true : false;
					ctrl = attributes.getNamedItem("UseCtrl").getNodeValue().equals("YES") ? true : false;
					keyMap.put(id, new Hotkey(key, shift, alt, ctrl));
				}
			}

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return keyMap;
	}

	/**
	 * Writes the modified keybindings to the destination file, using the xmlReference file to recreate the
	 * entire file.
	 */
	public static void writeToXml(File destination, File xmlReference, Map<String, Hotkey> keyMap) {

	}

	/**
	 * Overwrite the key bindings in the given file, using it as reference first.
	 */
	public static void writeToXML(File file, Map<String, Hotkey> keyMap) {
		KeyBindingsParser.writeToXml(file, file, keyMap);
	}

}
