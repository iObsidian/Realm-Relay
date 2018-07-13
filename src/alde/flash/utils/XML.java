package alde.flash.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import rotmg.objects.animation.AnimationData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Emulates AS3's built in XML capabilities.
 * <p>
 * See readme for more information on how to use
 */
public class XML {

	public Element element;
	public List<AnimationData> animations;

	public XML(Element el) {
		this.element = el;
	}

	public XML(String data) {
		Document doc = null;

		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src = new InputSource();
			src.setCharacterStream(new StringReader(data));

			doc = builder.parse(src);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}

		if (doc != null) {
			this.element = doc.getDocumentElement();
		} else {
			debug("Could not build XML from String '" + data + "'.");
		}
	}

	private static String checkIfHasZero(String textContent) {
		textContent = textContent.replace(" ", "");

		if (textContent.startsWith(".")) {
			textContent = "0" + textContent;
		}
		return textContent;
	}

	private static int hexToInt(String hex) {
		if (hex.startsWith("0x")) {
			//System.out.println("Hex ; " + hex + " to int : " + Integer.decode(hex));
			return Integer.decode(hex);
		} else {
			return Integer.parseInt(hex);
		}
	}

	@Override
	public String toString() {
		return getTextValue();
	}

	public String name() {
		if (element.getTagName() == null) {
			return element.getParentNode().getNodeName();
		} else {
			return element.getTagName();
		}
	}

	/**
	 * Can be a substitute to .name()
	 */
	public String getTextValue() {
		return element.getTextContent();
	}

	/**
	 * Get all children with name
	 */
	public List<XML> children(String name) {
		List<XML> xmls = new ArrayList<>();

		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);

			if (child instanceof Element && name.equals(child.getNodeName())) {
				xmls.add(new XML((Element) child));
			}
		}
		return xmls;
	}

	/**
	 * Get all child values
	 */
	public List<XML> children() {
		List<XML> xmls = new ArrayList<>();

		NodeList list = element.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			if (list.item(i) instanceof Element) {
				Element root = (Element) list.item(i);
				xmls.add(new XML(root));
			}
		}

		return xmls;
	}

	public XML child(String name) {
		for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return new XML((Element) child);
			}
		}
		return null;
	}

	public boolean hasOwnProperty(String tag) {
		for (XML x : children(tag)) {
			if (x.name().contains(tag)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Begin values
	 */

	public String getValue(String tag) {
		return child(tag).element.getTextContent();
		//return element.getElementsByTagName(tag).item(0).getTextContent();
	}

	public boolean getBooleanValue(String tag) {
		return getBooleanValue(tag, false);
	}

	/**
	 * Boolean (0 or 1)
	 */
	public boolean getBooleanValue(String tag, boolean defaultValue) {
		int value = getIntValue(tag, -1);

		if (value == -1) {
			debug("Error : Could not get boolean value '" + tag + "' from int value.");
			return defaultValue;
		} else if (value == 1) {
			return false;
		} else {
			return false;
		}
	}

	private void debug(String s) {
		//TODO
	}

	public int getIntValue(String tag) {
		return getIntValue(tag, 0);
	}

	public int getIntValue(String tag, int defaultValue) {
		try {
			return hexToInt(checkIfHasZero(getValue(tag)));
		} catch (Exception e) {
			debug(e.getMessage() + " with getting integer value " + tag + ", returning " + defaultValue + ".");
			return defaultValue;
		}
	}

	public double getDoubleValue(String tag) {
		return Double.parseDouble(checkIfHasZero(getValue(tag)));
	}

	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	public int getIntAttribute(String name) {
		return getIntAttribute(name, 0);
	}

	public int getIntAttribute(String name, int defaultValue) {
		try {
			return hexToInt(checkIfHasZero(getAttribute(name)));
		} catch (Exception e) {
			debug(e.getMessage() + " with getting double attribute '" + name + "', returning " + defaultValue + ".");
			return defaultValue;
		}
	}

	public double getDoubleAttribute(String name) {
		return getDoubleAttribute(name, 0);
	}

	/**
	 * Utility method to allow
	 * <p>
	 * xml.@attribute || Number(0)
	 * <p>
	 * to be replaced by
	 * <p>
	 * xml.getDoubleAttribute("attribute", 0)
	 */
	public double getDoubleAttribute(String name, double defaultValue) {
		try {
			return Float.parseFloat(checkIfHasZero(getAttribute(name)));
		} catch (Exception e) {
			try {
				return Double.parseDouble(checkIfHasZero(getAttribute(name)));
			} catch (Exception a) {
				debug(a.getMessage() + " with getting double attribute " + name
						+ ", returning " + defaultValue + "F.");
				return defaultValue;
			}
		}
	}

}
