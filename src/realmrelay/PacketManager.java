package realmrelay;

import java.io.IOException;
import realmrelay.data.Entity;
import realmrelay.data.Location;
import realmrelay.data.StatData;
import realmrelay.data.Status;
import realmrelay.packets.Packet;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.MovePacket;
import realmrelay.packets.client.PlayerTextPacket;
import realmrelay.packets.client.UseItemPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.server.Create_SuccessPacket;
import realmrelay.packets.server.New_TickPacket;
import realmrelay.packets.server.ReconnectPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.UpdatePacket;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {
	
	public static int myId;
	public static String myName = "";
	public static Location position = new Location();
	
	private long startTime = 0;
	
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
			
			myId = mpk.gameId;
			
		}
		
		return;
	}
	
	public int currentTime() {
		return (int) (System.currentTimeMillis() - startTime);
	}
	
	public static TextPacket TextPacket(String text) {
		TextPacket tp = new TextPacket();
		tp.bubbleTime = 3;
		tp.cleanText = "";
		tp.name = "";
		tp.numStars = -1;
		tp.objectId = 0;
		tp.recipient = "";
		tp.text = text;
		return tp;
	}
	
	public static void echo(String text) {
		System.out.println(text);
	}
	
	public static void onServerPacketEvent(PacketScriptEvent event) throws Exception {
		Packet packet = event.getPacket();
		
		if (packet instanceof Create_SuccessPacket) {
			Create_SuccessPacket csp = (Create_SuccessPacket) packet;
			//echo("My ID : " + csp.objectId);
			myId = csp.objectId;
		} else if (packet instanceof UpdatePacket) {
			UpdatePacket update = (UpdatePacket) packet;
			for (Entity ent : update.newObjs) {
				if (ent.status.objectId == myId) {
					position = ent.status.pos;
					for (StatData j : ent.status.data) {
						if (j.obf0 == 31) {
							myName = j.obf2;
						}
					}
				}
				
			}
		} else if (packet instanceof New_TickPacket) {
			New_TickPacket up = (New_TickPacket) packet;
			for (Status i : up.statuses) {
				if (i.objectId == myId) {
					
				}
			}
			
		}
		return;
	}
	
}