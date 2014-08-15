package realmrelay;

import java.io.IOException;
import java.util.HashMap;
import com.sun.corba.se.spi.activation.Server;
import realmrelay.data.Entity;
import realmrelay.data.Location;
import realmrelay.data.PlayerData;
import realmrelay.data.PortalData;
import realmrelay.data.SlotObject;
import realmrelay.data.StatData;
import realmrelay.data.Status;
import realmrelay.packets.Packet;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.InvSwapPacket;
import realmrelay.packets.client.PlayerTextPacket;
import realmrelay.packets.client.UseItemPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.server.Create_SuccessPacket;
import realmrelay.packets.server.New_TickPacket;
import realmrelay.packets.server.QuestObjIdPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.UpdatePacket;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {
	
	public static PlayerData playerData = new PlayerData();
	
	private static long startTime = 0;
	
	public static HashMap<String, PlayerData> nameAndID = new HashMap<String, PlayerData>();
	
	private static Packet lastUseItemPacket;
	
	private static boolean canGo;
	
	private static int myQuestId;
	
	private static Location myQuestPos;
	
	public static HashMap<Integer, PortalData> portals = new HashMap<Integer, PortalData>();
	
	private static boolean enteredInRealm;
	
	public static void onClientPacketEvent(final PacketScriptEvent event) throws Exception {
		final Packet packet = event.getPacket();
		
		if (packet instanceof PlayerTextPacket) {
			PlayerTextPacket ptp = (PlayerTextPacket) packet;
			
			if (ptp.text.startsWith(".")) {
				event.cancel();
			}
			
			if (ptp.text.startsWith(".") || ptp.text.startsWith("/")) {
				
				String command = ptp.text.substring(1).toLowerCase();
				String rest = ptp.text.substring(ptp.text.indexOf(" ") + 1).toLowerCase();
				
				if (command.startsWith("setpos")) {
					//myQuestPos = 
				}
				
			}
			
		}
		if (packet instanceof UsePortalPacket) {
			UsePortalPacket upk = (UsePortalPacket) packet;
			System.out.println(upk.objectId);
			
			
			if (portals.get(upk.objectId).population > 84){
				tellToPlayer(event, "You will be automatically connected soon.");
			
			}
			
		} else if (packet instanceof HelloPacket) {
			
			HelloPacket mpk = (HelloPacket) event.getPacket();
			
			playerData.id = mpk.gameId;
			
		} else if (packet instanceof UseItemPacket) {
			UseItemPacket psp = (UseItemPacket) event.getPacket();
			psp.itemUsePos = myQuestPos;
			
		} else if (packet instanceof UsePortalPacket) {
			UseItemPacket psp = (UseItemPacket) event.getPacket();
			psp.itemUsePos = myQuestPos;
			
		} else if (packet instanceof InvSwapPacket) {
			// inv debugger
			/*InvSwapPacket isp = (InvSwapPacket) event.getPacket();
			
			SlotObject slotObject1 = new SlotObject();
			slotObject1.objectType = isp.slotObject1.objectType;
			slotObject1.slotId = isp.slotObject1.slotId;
			
			System.out.println("from ObjectId (myId) : " + slotObject1.objectId);
			System.out.println("from OjectType (myId) : " + slotObject1.objectType);
			System.out.println("from SlotId (i) : " + slotObject1.slotId);*/
			
		}
		
		return;
	}
	
	public static int currentTime() {
		return (int) (System.currentTimeMillis() - startTime);
	}
	
	public static void echo(String text) {
		System.out.println(text);
	}
	
	public static void onServerPacketEvent(PacketScriptEvent event) throws Exception {
		Packet packet = event.getPacket();
		
		if (packet instanceof Create_SuccessPacket) {
			Create_SuccessPacket csp = (Create_SuccessPacket) packet;
			//echo("My ID : " + csp.objectId);
			playerData.id = csp.objectId;
		}
		if (packet instanceof QuestObjIdPacket) {
			QuestObjIdPacket qoid = (QuestObjIdPacket) packet;
			//echo("My ID : " + csp.objectId);
			myQuestId = qoid.objectId;
		} else if (packet instanceof UpdatePacket) {
			UpdatePacket update = (UpdatePacket) packet;
			
			for (Entity ent : update.newObjs) {
				
				for (StatData j : ent.status.data) {
					
					if (j.intValue == 31) {
						PlayerData d = new PlayerData();
						d.id = ent.status.objectId;
						d.name = j.stringValue;
						nameAndID.put(j.stringValue, d); //save the name and the id of every players for later
					}
					
					if (ent.status.objectId == playerData.id) {
						//playerData.parseNewTICK(j.obf0, j.intValue, j.stringValue);
						
						if (j.intValue == 31) {
							System.out.println(j.stringValue);
						}
					}
				}
				
				for (StatData data : ent.status.data) {
					if (data.id == 31 && data.stringValue.contains("NexusPortal")) { /* WTF IS "DARKPORTAL" ? */
						if (!enteredInRealm) {
							
							String portalName = data.stringValue.substring(data.stringValue.indexOf(".") + 1, data.stringValue.indexOf(" ("));
							int portalPopulation = Integer.parseInt(data.stringValue.substring(data.stringValue.indexOf("(") + 1, data.stringValue.indexOf("/")));
							
							PortalData portal = new PortalData(portalPopulation, ent.status.objectId, ent.status.pos, portalName);
							
							portals.put(ent.status.objectId, portal);
							
							echo("Found portal \"" + portal.name + "\" (" + portal.population + "/85).");
							
							
						}
					}
				}
				
				for (int drop : update.drops) {
					if (portals.containsKey(drop)) {
						portals.remove(drop);
						echo("Dropped a realm");
					}
				}
				
			}
		} else if (packet instanceof New_TickPacket) {
			New_TickPacket up = (New_TickPacket) packet;
			for (Status wo : up.statuses) {
				for (StatData d : wo.data) {
					if (wo.objectId == playerData.id) {
						
						//playerData.parseNewTICK(d.obf0, d.intValue, d.stringValue);
					}
					
				}
			}
			
		}
		return;
	}
	
	
	

public static void tellToPlayer(final PacketScriptEvent event, String text) throws IOException {
	TextPacket notificationPacket = new TextPacket();
	notificationPacket.bubbleTime = -1;
	notificationPacket.cleanText = "";
	notificationPacket.name = "";
	notificationPacket.numStars = -1;
	notificationPacket.objectId = -1;
	notificationPacket.recipient = "";
	notificationPacket.text = text;
	event.sendToClient(notificationPacket);
}

//LOL THIS IS SOME GAY SHIET
public static void fromJostun(final PacketScriptEvent event, String text) throws IOException {
	TextPacket notificationPacket = new TextPacket();
	notificationPacket.bubbleTime = -1;
	notificationPacket.cleanText = "";
	notificationPacket.name = "iObsidian";
	notificationPacket.numStars = 56;
	notificationPacket.objectId = -1;
	notificationPacket.recipient = playerData.name;
	notificationPacket.text = text;
	event.sendToClient(notificationPacket);
}






	
	
}



