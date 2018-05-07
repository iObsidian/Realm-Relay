package realmrelay;

import java.io.IOException;

import realmrelay.packets.Packet;
import realmrelay.packets.client.InvSwapPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.data.WorldPosData;
import realmrelay.packets.data.unused.PlayerData;
import realmrelay.packets.server.CreateSuccessPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {

	public static PlayerData playerData = new PlayerData();

	private static long startTime = 0;

	private static Packet lastUseItemPacket;

	private static boolean canGo;

	private static int myQuestId;

	private static WorldPosData myQuestPos;

	private static boolean enteredInRealm;

	private static int tryingToJoinPortalId = 0;

	private static int maxPopulation = 85;

	private static boolean wantToConnect = false;

	private static boolean isUsingAutoCon;

	private static boolean wantToBeReconnected = true;

	public static void onClientPacketEvent(final PacketScriptEvent event) throws Exception {
		final Packet packet = event.getPacket();

		if (packet instanceof UsePortalPacket) {

		} else if (packet instanceof InvSwapPacket) {

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
