package realmrelay;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import realmrelay.packets.data.EnemyData;
import realmrelay.packets.data.EnemyProjectileData;
import realmrelay.packets.data.GroundData;
import realmrelay.packets.data.ItemData;
import realmrelay.packets.data.ItemProjectileData;
import realmrelay.packets.data.ObjectData;

public class GetXMLParse {

	public static final Map<Integer, ItemData> itemMap = new LinkedHashMap<Integer, ItemData>();
	public static final Map<Integer, ObjectData> objectMap = new LinkedHashMap<Integer, ObjectData>();
	public static final Map<Integer, GroundData> tileMap = new LinkedHashMap<Integer, GroundData>();
	public static final Map<String, Integer> packetMap = new LinkedHashMap<String, Integer>();
	public static final Map<Integer, EnemyData> enemyTypeMap = new LinkedHashMap<Integer, EnemyData>();

	private static final int XML_ITEMS = 0, XML_OBJECTS = 1, XML_PACKETS = 2, XML_TILES = 3, XML_ENEMIES = 4;

	static {
		System.out.println("Parsing files...");

		parseXMLtoMap("Object", XML_OBJECTS, "res/xml/objects.xml");
		parseXMLtoMap("Ground", XML_TILES, "res/xml/tiles.xml");
		parseXMLtoMap("Packet", XML_PACKETS, "res/xml/packets.xml");
		parseXMLtoMap("Object", XML_ITEMS, "res/xml/items.xml");
		parseXMLtoMap("Object", XML_ENEMIES, "res/xml/enemies.xml");

	}

	public static void report() {
		System.out.println("Found " + GetXMLParse.objectMap.size() + " objects...");
		System.out.println("Found " + GetXMLParse.tileMap.size() + " tiles...");
		System.out.println("Found " + GetXMLParse.itemMap.size() + " tiles...");
		System.out.println("Found " + GetXMLParse.packetMap.size() + " packets...");
	}

	private static void parseXMLtoMap(String elementTagName, int xmlType, String localFilePath) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			InputStream in = new FileInputStream(localFilePath);

			System.out.println("Mhm : " + localFilePath);

			Document doc = dBuilder.parse(in);
			in.close();
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName(elementTagName);
			xmlToMap(nodeList, xmlType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Unable to load Local File: " + localFilePath);
		}
	}

	private static void xmlToMap(NodeList node, int xmlType) {
		for (int j = 0; j < node.getLength(); j++) {
			Element el = (Element) node.item(j);
			String idtemp = el.getAttribute("id").replace(" ", "").toUpperCase();

			switch (xmlType) {
			case XML_PACKETS:
				String typetemp = el.getAttribute("type");
				int packetType = Integer.parseInt(typetemp);
				packetMap.put(idtemp, packetType);
				break;
			case XML_TILES: {
				GroundData groundData = new GroundData();
				groundData.id = el.getAttribute("id");
				groundData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;

				if ((nodeList = el.getElementsByTagName("File")).getLength() > 0) {
					groundData.file = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Index")).getLength() > 0) {
					groundData.index = hexToInt(nodeList.item(0).getTextContent());
				}

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
				if ((nodeList = el.getElementsByTagName("Speed")).getLength() > 0) {
					groundData.speed = Float.parseFloat(nodeList.item(0).getTextContent());
				}
				tileMap.put(groundData.type, groundData);
				break;
			}
			case XML_ITEMS: {
				ItemData itemData = new ItemData();
				itemData.id = el.getAttribute("id");
				itemData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;

				if ((nodeList = el.getElementsByTagName("File")).getLength() > 0) {
					itemData.file = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Index")).getLength() > 0) {
					itemData.index = hexToInt(nodeList.item(0).getTextContent());
				}

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
					List<ItemProjectileData> projectiles = new LinkedList<ItemProjectileData>();
					for (int i = 0; i < nodeList.getLength(); i++) {
						Element projectile = (Element) nodeList.item(i);
						ItemProjectileData projectileData = new ItemProjectileData();
						NodeList nl = null;
						if ((nl = projectile.getElementsByTagName("ObjectId")).getLength() > 0) {
							projectileData.objectId = nl.item(0).getTextContent();
						}
						if ((nl = projectile.getElementsByTagName("Speed")).getLength() > 0) {
							projectileData.speed = Float.parseFloat(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("MaxDamage")).getLength() > 0) {
							projectileData.maxDamage = Float.parseFloat(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("MinDamage")).getLength() > 0) {
							projectileData.minDamage = Float.parseFloat(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("LifetimeMS")).getLength() > 0) {
							projectileData.lifetimeMS = Float.parseFloat(nl.item(0).getTextContent());
						}
						projectiles.add(projectileData);
					}
					itemData.projectiles = projectiles.toArray();
				}
				itemMap.put(itemData.type, itemData);
				break;
			}
			case XML_ENEMIES: {
				EnemyData enemyData = new EnemyData();
				enemyData.id = String.valueOf(el.getAttribute("id")).toLowerCase();
				enemyData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;

				if ((nodeList = el.getElementsByTagName("File")).getLength() > 0) {
					enemyData.file = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Index")).getLength() > 0) {
					enemyData.index = hexToInt(nodeList.item(0).getTextContent());
				}

				// Float

				if ((nodeList = el.getElementsByTagName("XpMult")).getLength() > 0) {
					enemyData.xpMult = Float.parseFloat(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("Z")).getLength() > 0) {
					enemyData.z = Float.parseFloat(nodeList.item(0).getTextContent());
				}

				// Integer

				if ((nodeList = el.getElementsByTagName("Level")).getLength() > 0) {
					enemyData.level = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("ShadowSize")).getLength() > 0) {
					enemyData.shadowSize = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("Defense")).getLength() > 0) {
					enemyData.defense = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("MaxHitPoints")).getLength() > 0) {
					enemyData.maxHitPoints = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("Type")).getLength() > 0) {
					enemyData.type = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("Size")).getLength() > 0) {
					enemyData.size = Integer.parseInt(nodeList.item(0).getTextContent());
				}

				// String

				if ((nodeList = el.getElementsByTagName("DeathSound")).getLength() > 0) {
					enemyData.deathSound = nodeList.item(0).getTextContent();
				}

				if ((nodeList = el.getElementsByTagName("HitSound")).getLength() > 0) {
					enemyData.hitSound = nodeList.item(0).getTextContent();
				}

				if ((nodeList = el.getElementsByTagName("EnemyClass")).getLength() > 0) {
					enemyData.enemyClass = nodeList.item(0).getTextContent();
				}

				if ((nodeList = el.getElementsByTagName("Group")).getLength() > 0) {
					enemyData.group = nodeList.item(0).getTextContent();
				}

				if ((nodeList = el.getElementsByTagName("DisplayId")).getLength() > 0) {
					enemyData.displayId = nodeList.item(0).getTextContent();
				}

				// Boolean

				if (el.getElementsByTagName("OccupySquare").getLength() > 0) {
					enemyData.occupySquare = true;
				}

				if (el.getElementsByTagName("Flying").getLength() > 0) {
					enemyData.flying = true;
				}

				if (el.getElementsByTagName("NoArticle").getLength() > 0) {
					enemyData.noAtricle = true;
				}

				if (el.getElementsByTagName("StasisImmune").getLength() > 0) {
					enemyData.stasisImmune = true;
				}

				if (el.getElementsByTagName("Oryx").getLength() > 0) {
					enemyData.oryx = true;
				}

				if (el.getElementsByTagName("God").getLength() > 0) {
					enemyData.god = true;
				}

				if (el.getElementsByTagName("Quest").getLength() > 0) {
					enemyData.quest = true;
				}

				if (el.getElementsByTagName("Enemy").getLength() > 0) {
					enemyData.enemy = true;
				}

				if ((nodeList = el.getElementsByTagName("Projectile")).getLength() > 0) {
					for (int i = 0; i < nodeList.getLength(); i++) {
						Element projectile = (Element) nodeList.item(i);
						EnemyProjectileData projectileData = new EnemyProjectileData();
						NodeList nl = null;
						if (!projectile.getAttribute("id").equals("")) {
							projectileData.bulletId = Integer.parseInt(projectile.getAttribute("id"));
						}

						if ((nl = projectile.getElementsByTagName("ObjectId")).getLength() > 0) {
							projectileData.objectId = nl.item(0).getTextContent();
						}
						if ((nl = projectile.getElementsByTagName("Speed")).getLength() > 0) {
							projectileData.speed = Float.parseFloat(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("Damage")).getLength() > 0) {
							projectileData.damage = Integer.parseInt(nl.item(0).getTextContent());
						}
						if ((nl = projectile.getElementsByTagName("LifetimeMS")).getLength() > 0) {
							projectileData.lifetimeMS = Integer.parseInt(nl.item(0).getTextContent());
						}
						enemyData.projectiles.add(projectileData);
					}

				}
				enemyTypeMap.put(enemyData.type, enemyData);
				break;
			}
			case XML_OBJECTS: {
				ObjectData objectData = new ObjectData();
				objectData.id = el.getAttribute("id");
				objectData.type = Integer.decode(el.getAttribute("type"));
				NodeList nodeList = null;

				if ((nodeList = el.getElementsByTagName("AnimatedTexture")).getLength() > 0) {
					objectData.hasAnimatedTexture = true;
				}

				if ((nodeList = el.getElementsByTagName("File")).getLength() > 0) {
					objectData.file = nodeList.item(0).getTextContent();
				}
				if ((nodeList = el.getElementsByTagName("Index")).getLength() > 0) {
					objectData.index = hexToInt(nodeList.item(0).getTextContent());
				}

				if ((nodeList = el.getElementsByTagName("DungeonName")).getLength() > 0) {
					objectData.dungeonName = nodeList.item(0).getTextContent();
				}

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
					if (!nodeList.item(0).getTextContent().equals("") || nodeList.item(0).getTextContent() == null) {
						objectData.color = Integer.decode(nodeList.item(0).getTextContent());
					}
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
				objectMap.put(objectData.type, objectData);
				break;
			}
			}
		}

	}

	private static int hexToInt(String textContent) {

		try {
			return Integer.decode(textContent); //sometimes its not an hexadecimal value. fuck off deca! jk love kbai
		} catch (Exception e) {
			return Integer.parseInt(textContent);
		}
	}

}
