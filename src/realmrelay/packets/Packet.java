package realmrelay.packets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import realmrelay.ROTMGRelay;
import realmrelay.game.Old_ObjectLibrary;
import realmrelay.packets.client.AcceptTradePacket;
import realmrelay.packets.client.ActivePetUpdateRequestPacket;
import realmrelay.packets.client.AoeAckPacket;
import realmrelay.packets.client.BuyPacket;
import realmrelay.packets.client.CancelTradePacket;
import realmrelay.packets.client.ChangeGuildRankPacket;
import realmrelay.packets.client.ChangeTradePacket;
import realmrelay.packets.client.CheckCreditsPacket;
import realmrelay.packets.client.ChooseNamePacket;
import realmrelay.packets.client.CreateGuildPacket;
import realmrelay.packets.client.CreatePacket;
import realmrelay.packets.client.EditAccountListPacket;
import realmrelay.packets.client.EnemyHitPacket;
import realmrelay.packets.client.EscapePacket;
import realmrelay.packets.client.GoToQuestRoomPacket;
import realmrelay.packets.client.GotoAckPacket;
import realmrelay.packets.client.GroundDamagePacket;
import realmrelay.packets.client.GuildInvitePacket;
import realmrelay.packets.client.GuildRemovePacket;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.InvDropPacket;
import realmrelay.packets.client.InvSwapPacket;
import realmrelay.packets.client.JoinGuildPacket;
import realmrelay.packets.client.KeyInfoRequestPacket;
import realmrelay.packets.client.LoadPacket;
import realmrelay.packets.client.MovePacket;
import realmrelay.packets.client.OtherHitPacket;
import realmrelay.packets.client.PlayerHitPacket;
import realmrelay.packets.client.PlayerShootPacket;
import realmrelay.packets.client.PlayerTextPacket;
import realmrelay.packets.client.PongPacket;
import realmrelay.packets.client.RequestTradePacket;
import realmrelay.packets.client.ReskinPacket;
import realmrelay.packets.client.SetConditionPacket;
import realmrelay.packets.client.ShootAckPacket;
import realmrelay.packets.client.SquareHitPacket;
import realmrelay.packets.client.TeleportPacket;
import realmrelay.packets.client.UpdateAckPacket;
import realmrelay.packets.client.UseItemPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.client.arena.EnterArenaPacket;
import realmrelay.packets.client.arena.QuestRedeemPacket;
import realmrelay.packets.data.unused.IData;
import realmrelay.packets.server.AccountListPacket;
import realmrelay.packets.server.AllyShootPacket;
import realmrelay.packets.server.AoePacket;
import realmrelay.packets.server.BuyResultPacket;
import realmrelay.packets.server.ClientStatPacket;
import realmrelay.packets.server.CreateSuccessPacket;
import realmrelay.packets.server.DamagePacket;
import realmrelay.packets.server.DeathPacket;
import realmrelay.packets.server.EnemyShootPacket;
import realmrelay.packets.server.EvolvedPetMessagePacket;
import realmrelay.packets.server.FailurePacket;
import realmrelay.packets.server.FilePacket;
import realmrelay.packets.server.GlobalNotificationPacket;
import realmrelay.packets.server.GotoPacket;
import realmrelay.packets.server.GuildResultPacket;
import realmrelay.packets.server.InvResultPacket;
import realmrelay.packets.server.KeyInfoResponsePacket;
import realmrelay.packets.server.MapInfoPacket;
import realmrelay.packets.server.NameResultPacket;
import realmrelay.packets.server.NewAbilityMessagePacket;
import realmrelay.packets.server.NewTickPacket;
import realmrelay.packets.server.NotificationPacket;
import realmrelay.packets.server.PasswordPromptPacket;
import realmrelay.packets.server.PingPacket;
import realmrelay.packets.server.PlaySoundPacket;
import realmrelay.packets.server.QuestObjIdPacket;
import realmrelay.packets.server.QuestRedeemResponsePacket;
import realmrelay.packets.server.ReconnectPacket;
import realmrelay.packets.server.ReskinUnlockPacket;
import realmrelay.packets.server.ServerPlayerShootPacket;
import realmrelay.packets.server.ShowEffectPacket;
import realmrelay.packets.server.TextPacket;
import realmrelay.packets.server.TradeAcceptedPacket;
import realmrelay.packets.server.TradeChangedPacket;
import realmrelay.packets.server.TradeDonePacket;
import realmrelay.packets.server.TradeRequestedPacket;
import realmrelay.packets.server.TradeStartPacket;
import realmrelay.packets.server.UpdatePacket;
import realmrelay.packets.server.VerifyEmailPacket;
import realmrelay.packets.server.arena.ArenaDeathPacket;
import realmrelay.packets.server.arena.ImminentArenaWavePacket;
import realmrelay.packets.server.pets.DeletePetMessagePacket;
import realmrelay.packets.server.pets.Hatch_PetMessagePacket;

public abstract class Packet implements IData {

	private static final List<Class<? extends Packet>> packetIdtoClassMap = new ArrayList<Class<? extends Packet>>();
	public static boolean init;

	/**
	 * Creates new packet from packet id
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 * @throws InstantiationException
	 */
	public static Packet create(byte id) throws Exception {

		Class<? extends Packet> packetClass = null;

		if (id != -1) {
			packetClass = Packet.packetIdtoClassMap.get(id);
		}

		if (packetClass == null) {
			UnknownPacket packet = new UnknownPacket();
			packet.setId(id);
			return packet;
		}
		return packetClass.newInstance();
	}

	/**
	 * Creates new packet from packet id and packet bytes
	 *
	 * @param id
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Packet create(byte id, byte[] bytes) throws Exception {

		Packet packet = Packet.create(id);
		packet.parseFromInput(new DataInputStream(new ByteArrayInputStream(bytes)));

		System.out.println(packet.getName());
		
		return packet;
	}

	public static void init() {
		ROTMGRelay.echo("Mapping Packet IDs...");
		for (int i = 0; i < 127; i++) {
			Packet.packetIdtoClassMap.add(null);
		}
		List<Class<? extends Packet>> list = new LinkedList<Class<? extends Packet>>();
		list.add(AcceptTradePacket.class);
		list.add(ActivePetUpdateRequestPacket.class);
		list.add(AoeAckPacket.class);
		list.add(BuyPacket.class);
		list.add(CancelTradePacket.class);
		list.add(ChangeGuildRankPacket.class);
		list.add(ChangeTradePacket.class);
		list.add(CheckCreditsPacket.class);
		list.add(ChooseNamePacket.class);
		list.add(CreateGuildPacket.class);
		list.add(CreatePacket.class);
		list.add(EditAccountListPacket.class);
		list.add(EnemyHitPacket.class);
		list.add(EscapePacket.class);
		list.add(GotoAckPacket.class);
		list.add(GoToQuestRoomPacket.class);
		list.add(GroundDamagePacket.class);
		list.add(GuildInvitePacket.class);
		list.add(GuildRemovePacket.class);
		list.add(HelloPacket.class);
		list.add(InvDropPacket.class);
		list.add(InvSwapPacket.class);
		list.add(JoinGuildPacket.class);
		list.add(KeyInfoRequestPacket.class);
		list.add(LoadPacket.class);
		list.add(MovePacket.class);
		list.add(OtherHitPacket.class);
		list.add(PlayerHitPacket.class);
		list.add(PlayerShootPacket.class);
		list.add(PlayerTextPacket.class);
		list.add(PongPacket.class);
		list.add(RequestTradePacket.class);
		list.add(ReskinPacket.class);
		list.add(SetConditionPacket.class);
		list.add(ShootAckPacket.class);
		list.add(SquareHitPacket.class);
		list.add(TeleportPacket.class);
		list.add(UpdateAckPacket.class);
		list.add(UseItemPacket.class);
		list.add(UsePortalPacket.class);
		list.add(EnterArenaPacket.class);
		list.add(QuestRedeemPacket.class);
		list.add(AccountListPacket.class);
		list.add(AllyShootPacket.class);
		list.add(AoePacket.class);
		list.add(BuyResultPacket.class);
		list.add(ClientStatPacket.class);
		list.add(CreateSuccessPacket.class);
		list.add(DamagePacket.class);
		list.add(DeathPacket.class);
		list.add(EnemyShootPacket.class);
		list.add(EvolvedPetMessagePacket.class);
		list.add(FailurePacket.class);
		list.add(FilePacket.class);
		list.add(GlobalNotificationPacket.class);
		list.add(GotoPacket.class);
		list.add(GuildResultPacket.class);
		list.add(InvResultPacket.class);
		list.add(KeyInfoResponsePacket.class);
		list.add(MapInfoPacket.class);
		list.add(NameResultPacket.class);
		list.add(NewAbilityMessagePacket.class);
		list.add(NewTickPacket.class);
		list.add(NotificationPacket.class);
		list.add(PasswordPromptPacket.class);
		list.add(PingPacket.class);
		list.add(PlaySoundPacket.class);
		list.add(QuestObjIdPacket.class);
		list.add(QuestRedeemResponsePacket.class);
		list.add(ReconnectPacket.class);
		list.add(ReskinUnlockPacket.class);
		list.add(ServerPlayerShootPacket.class);
		list.add(ShowEffectPacket.class);
		list.add(TextPacket.class);
		list.add(TradeAcceptedPacket.class);
		list.add(TradeChangedPacket.class);
		list.add(TradeDonePacket.class);
		list.add(TradeRequestedPacket.class);
		list.add(TradeStartPacket.class);
		list.add(UpdatePacket.class);
		list.add(VerifyEmailPacket.class);
		list.add(ArenaDeathPacket.class);
		list.add(ImminentArenaWavePacket.class);
		list.add(DeletePetMessagePacket.class);
		list.add(Hatch_PetMessagePacket.class);

		String name = null;
		try {

			for (Class<? extends Packet> packetClass : list) {
				name = packetClass.getName();
				Packet packet = packetClass.newInstance();

				if (packet.id() != -1) {
					Packet.packetIdtoClassMap.set(packet.id(), packetClass);
				}

			}
			for (Entry<String, Integer> entry : Old_ObjectLibrary.packetMap.entrySet()) {
				byte id = entry.getValue().byteValue();
				Packet packet = Packet.create(id);
				if (packet instanceof UnknownPacket) {
					ROTMGRelay.echo("Unknown packet... " + id + " " + entry.getKey());
				}
			}
		} catch (Exception e) {
			ROTMGRelay.echo("Error with packet " + name + ". Is there a 'Packet' suffix?");
			e.printStackTrace();
		}
		ROTMGRelay.echo("Completed.");
		init = true;
	}

	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		writeToOutput(out);
		return baos.toByteArray();
	}

	//Can cause Index Out Of Bound Exception if packet name doesnt contain Packet
	public String getName() {
		String simpleName = this.getClass().getSimpleName();
		simpleName = simpleName.substring(0, simpleName.indexOf("Packet"));
		return simpleName.toUpperCase();
	}

	public byte id() { //return id as byte
		String name = getName();

		if (!Old_ObjectLibrary.packetMap.containsKey(name)) {
			ROTMGRelay.echo("Could not find packet with name '" + name + "'.");
			return -1;
		} else {
			return Old_ObjectLibrary.packetMap.get(name).byteValue();
		}

	}

	@Override
	public String toString() {
		return getName();
	}

}
