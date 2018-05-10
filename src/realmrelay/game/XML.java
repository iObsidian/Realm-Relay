package realmrelay.game;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import realmrelay.game.objects.animation.AnimationData;

public class XML {

	public Element element;

	public XML(Element el) {
		this.element = el;
	}

	public String name() {
		if (element.getTagName() == null) {
			return element.getParentNode().getNodeName();
		} else {
			return element.getTagName();
		}
	}

	public List<XML> getChilds(String name) {
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

	public XML getChild(String name) {
		for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
			if (child instanceof Element && name.equals(child.getNodeName())) {
				return new XML((Element) child);
			}
		}
		return null;
	}

	public boolean hasOwnProperty(String tag) {
		for (XML x : getChilds(tag)) {
			if (x.name().contains(tag)) {
				return true;
			}
		}
		return false;
	}

	public String getValue(String tag) {
		return getChild(tag).element.getTextContent();
		//return element.getElementsByTagName(tag).item(0).getTextContent();
	}

	public int getIntValue(String tag) {
		return hexToInt(getValue(tag));
	}

	public float getFloatValue(String tag) {
		return Float.parseFloat(getValue(tag));
	}

	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	public int getIntAttribute(String name) {
		try {
			return hexToInt(getAttribute(name));
		} catch (Exception e) {
			System.err.println(e.getMessage() + " with " + name + ", returning 0.");
			return 0;
		}
	}

	public float getFloatAttribute(String name) {
		try {
			return Float.parseFloat(getAttribute(name));
		} catch (Exception e) {
			System.err.println(e.getMessage() + " with " + name + ", returning 0F.");
			return 0.0F;
		}
	}

	private static int hexToInt(String textContent) {
		try {
			return Integer.decode(textContent);
		} catch (Exception e) {
			return Integer.parseInt(textContent);
		}
	}

	public List<AnimationData> animations;

	public List<XML> getChilds() {
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
