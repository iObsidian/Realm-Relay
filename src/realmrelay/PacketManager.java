package realmrelay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import realmrelay.game.objects.GameObject;
import realmrelay.packets.Packet;
import realmrelay.packets.data.GroundTileData;
import realmrelay.packets.data.unused.PlayerData;
import realmrelay.packets.server.CreateSuccessPacket;
import realmrelay.packets.server.NewTickPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.UpdatePacket;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {

	public static PlayerData playerData = new PlayerData();

	private static long startTime = 0;

	public static List<GameObject> objects = new ArrayList<GameObject>();
	public static List<GroundTileData> tiles = new ArrayList<GroundTileData>();

	public static void onClientPacketEvent(final PacketScriptEvent event) throws Exception {
		final Packet packet = event.getPacket();

		if (packet instanceof NewTickPacket) {

			NewTickPacket newTick = (NewTickPacket) packet;

		} else if (packet instanceof UpdatePacket) {

			UpdatePacket update = (UpdatePacket) packet;

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

		if (packet instanceof CreateSuccessPacket) {

		}

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

}
