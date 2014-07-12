package realmrelay;

import java.util.HashMap;
import realmrelay.data.Entity;
import realmrelay.data.Location;
import realmrelay.data.PlayerData;
import realmrelay.data.StatData;
import realmrelay.data.Status;
import realmrelay.packets.Packet;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.PlayerTextPacket;
import realmrelay.packets.server.Create_SuccessPacket;
import realmrelay.packets.server.New_TickPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.UpdatePacket;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {
	
	public static PlayerData playerData = new PlayerData();
	
	private long startTime = 0;
	
	public static HashMap<String, PlayerData> nameAndID = new HashMap<String, PlayerData>();
	
	public static void onClientPacketEvent(final PacketScriptEvent event) throws Exception {
		Packet packet = event.getPacket();
		
		if (packet instanceof PlayerTextPacket) {
			PlayerTextPacket ptp = (PlayerTextPacket) packet;
			
			if (ptp.text.startsWith(".")) {
				event.cancel();
			}
			
			if (ptp.text.startsWith(".") || ptp.text.startsWith("/")) {
				
				String command = ptp.text.substring(1);
				
				if (command.equalsIgnoreCase("")) {
					
				}
				
			}
			
		} else if (packet instanceof HelloPacket) {
			
			HelloPacket mpk = (HelloPacket) event.getPacket();
			
			playerData.id = mpk.gameId;
			
		}
		
		return;
	}
	
	public int currentTime() {
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
		} else if (packet instanceof UpdatePacket) {
			UpdatePacket update = (UpdatePacket) packet;
			for (Entity ent : update.newObjs) {
				for (StatData j : ent.status.data) {
					
					if (j.obf0 == 31) {
						PlayerData d = new PlayerData();
						d.id = ent.status.objectId;
						d.name = j.obf2;
						nameAndID.put(j.obf2, d); //save the name and the id of every players for later
					}
					
					if (ent.status.objectId == playerData.id) {
						//playerData.parseNewTICK(j.obf0, j.obf1, j.obf2);
						
						if (j.obf0 == 31) {
							System.out.println(j.obf2);
						}
					}
				}
				
			}
		} else if (packet instanceof New_TickPacket) {
			New_TickPacket up = (New_TickPacket) packet;
			for (Status wo : up.statuses) {
				for (StatData d : wo.data) {
					if (wo.objectId == playerData.id) {
						
						//playerData.parseNewTICK(d.obf0, d.obf1, d.obf2);
					}
				}
			}
			
		}
		return;
	}
	
}