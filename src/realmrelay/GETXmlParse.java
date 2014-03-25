package realmrelay;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

import realmrelay.data.GroundData;
import realmrelay.data.ItemData;
import realmrelay.data.ObjectData;
import realmrelay.data.ProjectileData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class GETXmlParse {

	public static final Map<String, ItemData> itemMap = new HashMap<String, ItemData>();
	public static final Map<Integer, ItemData> itemMap2 = new HashMap<Integer, ItemData>();
	public static final Map<String, ObjectData> objectMap = new HashMap<String, ObjectData>();
	public static final Map<Integer, ObjectData> objectMap2 = new HashMap<Integer, ObjectData>();
	public static final Map<String, GroundData> tileMap = new HashMap<String, GroundData>();
	public static final Map<Integer, GroundData> tileMap2 = new HashMap<Integer, GroundData>();
	public static final Map<String, Integer> packetMap = new HashMap<String, Integer>();
	
	private static final int XML_ITEMS = 0;
	private static final int XML_OBJECTS = 1;
	private static final int XML_PACKETS = 2;
	private static final int XML_TILES = 3;

	public static void parseXMLData() throws Exception {
		File file = new File("xml/");
		if (!file.isDirectory()) {
			file.mkdir();
		}
		parseXMLtoMap("Object", XML_OBJECTS, "xml/objects.xml");
		parseXMLtoMap("Ground", XML_TILES, "xml/tiles.xml");
		parseXMLtoMap("Packet", XML_PACKETS, "xml/packets.xml");
		parseXMLtoMap("Object", XML_ITEMS, "xml/items.xml");
	}

	private static void parseXMLtoMap(String elementTagName, int xmlType, String localFilePath) throws Exception {
		File file = new File(localFilePath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		InputStream in = new FileInputStream(file);
		Document doc = dBuilder.parse(in);
		in.close();
		doc.getDocumentElement().normalize();
		NodeList nodeList = doc.getElementsByTagName(elementTagName);
		xmlToMap(nodeList, xmlType);
	}

	private static void xmlToMap(NodeList node, int xmlType) {
		for (int j = 0; j < node.getLength(); j++) {
			Element el = (Element) node.item(j);
			// convert names with lowercase letters and spaces to the correct format
			String idtemp = el.getAttribute("id").replace(" ", "").toUpperCase();
			if(xmlType == XML_TILES) {
				GroundData groundData = new GroundData();
				groundData.id = el.getAttribute("id");
				groundData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("MaxDamage")).getLength() > 0) {
					groundData.maxDamage = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MinDamage")).getLength() > 0) {
					groundData.minDamage = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if (el.getElementsByTagName("NoWalk").getLength() > 0) {
					groundData.noWalk = true;
				}
				if (el.getElementsByTagName("Push").getLength() > 0) {
					groundData.push = true;
				}
				if (el.getElementsByTagName("Sink").getLength() > 0) {
					groundData.sink = true;
				}
				if ((nodeList = el.getElementsByTagName("Speed")).getLength() > 0){
					groundData.speed = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				tileMap.put(idtemp, groundData);
				tileMap2.put(groundData.type, groundData);
			} else if (xmlType == XML_ITEMS) {
				ItemData itemData = new ItemData();
				itemData.id = el.getAttribute("id");
				itemData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("SlotType")).getLength() > 0) {
					itemData.slotType = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Tier")).getLength() > 0) {
					itemData.tier = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("PetFamily")).getLength() > 0) {
					itemData.petFamily = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Rarity")).getLength() > 0) {
					itemData.rarity = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Activate")).getLength() > 0) {
					itemData.activate = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Consumable")).getLength() > 0) {
					itemData.consumable = true;
				}
				if ((nodeList = el.getElementsByTagName("Soulbound")).getLength() > 0) {
					itemData.soulbound = true;
				}
				if ((nodeList = el.getElementsByTagName("Usable")).getLength() > 0) {
					itemData.usable = true;
				}
				if ((nodeList = el.getElementsByTagName("BagType")).getLength() > 0) {
					itemData.bagType = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("FeedPower")).getLength() > 0) {
					itemData.feedPower = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("RateOfFire")).getLength() > 0) {
					itemData.rateOfFire = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("FameBonus")).getLength() > 0) {
					itemData.fameBonus = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MpCost")).getLength() > 0) {
					itemData.mpCost = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MpEndCost")).getLength() > 0) {
					itemData.mpEndCost = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MultiPhase")).getLength() > 0) {
					itemData.multiPhase = true;
				}
				if ((nodeList = el.getElementsByTagName("NumProjectiles")).getLength() > 0) {
					itemData.numProjectiles = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Projectile")).getLength() > 0) {
					List<ProjectileData> projectiles = new LinkedList<ProjectileData>();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Element projectile = (Element) nodeList.item(i);
						ProjectileData projectileData = new ProjectileData();
						NodeList nl = null;
						if ((nl = projectile.getElementsByTagName("ObjectId")).getLength() > 0) {
							projectileData.objectId = nl.item(0).getTextContent();
						}
						if ((nl = projectile.getElementsByTagName("Speed")).getLength() > 0) {
							projectileData.speed = Float.parseFloat(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("MaxDamage")).getLength() > 0) {
							projectileData.maxDamage = Integer.parseInt(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("MinDamage")).getLength() > 0) {
							projectileData.minDamage = Integer.parseInt(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("LifetimeMS")).getLength() > 0) {
							projectileData.lifetimeMS = Integer.parseInt(nl.item(0).getTextContent());
						}
						projectiles.add(projectileData);
					}
					itemData.projectiles = projectiles.toArray();
				}
				itemMap.put(idtemp, itemData);
				itemMap2.put(itemData.type, itemData);
			} else if (xmlType == XML_OBJECTS) {
				ObjectData objectData = new ObjectData();
				objectData.id = el.getAttribute("id");
				objectData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;
				if ((nodeList = el.getElementsByTagName("MaxHitPoints")).getLength() > 0) {
					objectData.maxHitPoints = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MaxSize")).getLength() > 0) {
					objectData.maxSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("MinSize")).getLength() > 0) {
					objectData.minSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Size")).getLength() > 0) {
					objectData.size = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("SizeStep")).getLength() > 0) {
					objectData.sizeStep = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("ShadowSize")).getLength() > 0) {
					objectData.shadowSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Color")).getLength() > 0) {
					objectData.color = Integer.decode(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("XpMult")).getLength() > 0) {
					objectData.xpMult = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if ((nodeList = el.getElementsByTagName("Rotation")).getLength() > 0) {
					objectData.rotation = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				if (el.getElementsByTagName("DrawOnGround").getLength() > 0) {
					objectData.drawOnGround = true;
				}
				if (el.getElementsByTagName("Enemy").getLength() > 0) {
					objectData.enemy = true;
				}
				if (el.getElementsByTagName("FullOccupy").getLength() > 0) {
					objectData.fullOccupy = true;
				}
				if (el.getElementsByTagName("OccupySquare").getLength() > 0) {
					objectData.occupySquare = true;
				}
				if (el.getElementsByTagName("EnemyOccupySquare").getLength() > 0) {
					objectData.enemyOccupySquare = true;
				}
				if (el.getElementsByTagName("BlocksSight").getLength() > 0) {
					objectData.blocksSight = true;
				}
				if (el.getElementsByTagName("NoMiniMap").getLength() > 0) {
					objectData.noMiniMap = true;
				}
				if (el.getElementsByTagName("StasisImmune").getLength() > 0) {
					objectData.stasisImmune = true;
				}
				if (el.getElementsByTagName("ProtectFromGroundDamage").getLength() > 0) {
					objectData.protectFromGroundDamage = true;
				}
				if (el.getElementsByTagName("ProtectFromSink").getLength() > 0) {
					objectData.protectFromSink = true;
				}
				if (el.getElementsByTagName("Connects").getLength() > 0) {
					objectData.connects = true;
				}
				if ((nodeList = el.getElementsByTagName("Z")).getLength() > 0) {
					objectData.z = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				objectMap.put(idtemp, objectData);
				objectMap2.put(objectData.type, objectData);
			} else if (xmlType == XML_PACKETS) {
				String typetemp = el.getAttribute("type");
				int packetType = Integer.parseInt(typetemp);
				packetMap.put(idtemp, packetType);
			}
		}
	}
	
}