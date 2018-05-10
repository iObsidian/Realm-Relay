package realmrelay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import realmrelay.game.messaging.data.GroundTileData;
import realmrelay.game.messaging.incoming.CreateSuccess;
import realmrelay.game.messaging.incoming.NewTick;
import realmrelay.game.messaging.incoming.Text;
import realmrelay.game.messaging.incoming.Update;
import realmrelay.game.objects.GameObject;
import realmrelay.packets.Packet;
import realmrelay.packets.data.unused.PlayerData;
import realmrelay.script.PacketScriptEvent;

public class PacketManager {

	public static PlayerData playerData = new PlayerData();

	private static long startTime = 0;

	public static List<GameObject> objects = new ArrayList<GameObject>();
	public static List<GroundTileData> tiles = new ArrayList<GroundTileData>();

	public static void onClientPacketEvent(final PacketScriptEvent event) throws Exception {
		final Packet packet = event.getPacket();

		if (packet instanceof NewTick) {

			NewTick newTick = (NewTick) packet;

		} else if (packet instanceof Update) {

			Update update = (Update) packet;

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

		if (packet instanceof CreateSuccess) {

		}

	}

	public static void tellToPlayer(final PacketScriptEvent event, String text) throws IOException {
		Text notificationPacket = new Text();
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
