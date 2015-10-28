package com.kevincyt.toskeys.model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return keyMap;
	}

	/**
	 * Writes the modified keybindings to the destination file, using the xmlReference file to recreate the
	 * entire file.
	 */
	public static void writeToXml(File destination, File xmlReference, Map<String, Hotkey> keyMap) {
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(xmlReference);

			Node root = doc.getDocumentElement();
			NodeList hotkeyNodes = root.getChildNodes();
			Node hotkey;
			String id, temp;
			Hotkey h;
			for (int i = 0; i < hotkeyNodes.getLength(); i++) {
				hotkey = hotkeyNodes.item(i);
				if(hotkey.hasAttributes()) {
					NamedNodeMap attributes = hotkey.getAttributes();
					id = attributes.getNamedItem("ID").getNodeValue();
					if(keyMap.containsKey(id)) {
						h = keyMap.get(id);
						attributes.getNamedItem("Key").setNodeValue(h.getKey());
						temp = h.useShift() == true ? "YES" : "NO";
						attributes.getNamedItem("UseShift").setNodeValue(temp);
						temp = h.useAlt() == true ? "YES" : "NO";
						attributes.getNamedItem("UseAlt").setNodeValue(temp);
						temp = h.useCtrl() == true ? "YES" : "NO";
						attributes.getNamedItem("UseCtrl").setNodeValue(temp);
					}
				}
			}
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer t = tf.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult stream = new StreamResult(destination);
			t.transform(source, stream);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Overwrite the key bindings in the given file, using it as reference first.
	 */
	public static void writeToXML(File file, Map<String, Hotkey> keyMap) {
		KeyBindingsParser.writeToXml(file, file, keyMap);
	}

}
