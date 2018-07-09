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

public class XML {

	public Element element;
	public List<AnimationData> animations;

	public XML(Element el) {
		this.element = el;
	}


	@Override
	public String toString() {
		return getTextValue();
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
			System.err.println("Could not build XML from String '" + data + "'.");
		}
	}

	private static int hexToInt(String textContent) {
		try {
			return Integer.decode(textContent);
		} catch (Exception e) {
			return Integer.parseInt(textContent);
		}
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

	public List<XML> childs(String name) {
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

	public XML child(String name) {
		for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return new XML((Element) child);
			}
		}
		return null;
	}

	public boolean hasOwnProperty(String tag) {
		for (XML x : childs(tag)) {
			if (x.name().contains(tag)) {
				return true;
			}
		}
		return false;
	}

	public String getValue(String tag) {
		return child(tag).element.getTextContent();
		//return element.getElementsByTagName(tag).item(0).getTextContent();
	}

	public boolean getBooleanValue(String tag) {
		return getBooleanValue(tag, false);
	}

	public boolean getBooleanValue(String tag, boolean defaultValue) {
		int value = getIntValue(tag, -1);

		if (value == -1) {
			System.err.println("Error : Could not get boolean value '" + tag + "' from int value.");

			//TODO attempt with String 'True'?

			return defaultValue;
		} else if (value == 1) {
			return false;
		} else {
			return false;
		}
	}

	public int getIntValue(String tag) {
		return getIntValue(tag, 0);
	}

	public int getIntValue(String tag, int defaultValue) {
		try {
			return hexToInt(getValue(tag));
		} catch (Exception e) {
			System.err.println(e.getMessage() + " with getting value " + tag + ", returning " + defaultValue + ".");
			return defaultValue;
		}
	}

	public double getDoubleValue(String tag) {
		return Double.parseDouble(getValue(tag));
	}

	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	public int getIntAttribute(String name) {
		return getIntAttribute(name, 0);
	}

	public int getIntAttribute(String name, int defaultValue) {
		try {
			return hexToInt(getAttribute(name));
		} catch (Exception e) {
			System.err
					.println(e.getMessage() + " with getting attribute " + name + ", returning " + defaultValue + ".");
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
			return Double.parseDouble(getAttribute(name));
		} catch (Exception e) {
			System.err.println("Value : " + getAttribute(name) + " Error : " + e.getMessage() + " with " + name
					+ ", returning " + defaultValue + "F.");
			return defaultValue;
		}
	}

	public List<XML> childs() {
		List<XML> xmls = new ArrayList<>();

		NodeList childs = element.getChildNodes();
		for (int i = 0; i < childs.getLength(); i++) {
			Node child = childs.item(i);

			if (child instanceof Element) {
				xmls.add(new XML((Element) child));
			}
		}
		return xmls;
	}


}
