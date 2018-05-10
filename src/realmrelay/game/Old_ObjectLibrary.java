package realmrelay.game;

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

import realmrelay.game.objects.GameObject;
import realmrelay.packets.data.unused.GroundData;
import realmrelay.packets.data.unused.ItemData;
import realmrelay.packets.data.unused.ItemProjectileData;

public class Old_ObjectLibrary {

    public static final Map<Integer, ItemData> itemMap = new LinkedHashMap<Integer, ItemData>();
    public static final Map<Integer, GameObject> objectMap = new LinkedHashMap<Integer, GameObject>();
    public static final Map<Integer, GroundData> tileMap = new LinkedHashMap<Integer, GroundData>();
    public static final Map<String, Integer> packetMap = new LinkedHashMap<String, Integer>();

    private static final int XML_ITEMS = 0, XML_OBJECTS = 1, XML_PACKETS = 2, XML_TILES = 3;

    static {
        System.out.println("Parsing files...");

        parseXMLtoMap("Object", XML_OBJECTS, "xml/objects.xml");
        parseXMLtoMap("Ground", XML_TILES, "xml/tiles.xml");
        parseXMLtoMap("Packet", XML_PACKETS, "xml/packets.xml");
        parseXMLtoMap("Object", XML_ITEMS, "xml/items.xml");

    }

    public static void report() {
        System.out.println("Found " + Old_ObjectLibrary.objectMap.size() + " objects...");
        System.out.println("Found " + Old_ObjectLibrary.tileMap.size() + " tiles...");
        System.out.println("Found " + Old_ObjectLibrary.itemMap.size() + " tiles...");
        System.out.println("Found " + Old_ObjectLibrary.packetMap.size() + " packets...");
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
        /**for (int j = 0; j < node.getLength(); j++) {
            Element el = (Element) node.item(j);
            String idtemp = el.attribute("id").replace(" ", "").toUpperCase();

            switch (xmlType) {
                case XML_PACKETS:
                    String typetemp = el.attribute("type");
                    int packetType = Integer.parseInt(typetemp);
                    packetMap.put(idtemp, packetType);
                    break;
                case XML_TILES: {
                    GroundData groundData = new GroundData();
                    groundData.id = el.attribute("id");
                    groundData.type = Integer.decode(el.attribute("type"));
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
                    itemData.id = el.attribute("id");
                    itemData.type = Integer.decode(el.attribute("type"));
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
                case XML_OBJECTS: {
                    GameObject objectData = new GameObject();
                    objectData.id = el.attribute("id");
                    objectData.type = Integer.decode(el.attribute("type"));
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
        }*/

    }


}
