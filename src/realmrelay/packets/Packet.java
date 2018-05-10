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
import realmrelay.game.messaging.incoming.AccountListPacket;
import realmrelay.game.messaging.incoming.AllyShootPacket;
import realmrelay.game.messaging.incoming.AoePacket;
import realmrelay.game.messaging.incoming.BuyResultPacket;
import realmrelay.game.messaging.incoming.ClientStatPacket;
import realmrelay.game.messaging.incoming.CreateSuccessPacket;
import realmrelay.game.messaging.incoming.DamagePacket;
import realmrelay.game.messaging.incoming.DeathPacket;
import realmrelay.game.messaging.incoming.EnemyShootPacket;
import realmrelay.game.messaging.incoming.EvolvedPetMessagePacket;
import realmrelay.game.messaging.incoming.FailurePacket;
import realmrelay.game.messaging.incoming.FilePacket;
import realmrelay.game.messaging.incoming.GlobalNotificationPacket;
import realmrelay.game.messaging.incoming.GotoPacket;
import realmrelay.game.messaging.incoming.GuildResultPacket;
import realmrelay.game.messaging.incoming.InvResultPacket;
import realmrelay.game.messaging.incoming.KeyInfoResponsePacket;
import realmrelay.game.messaging.incoming.MapInfoPacket;
import realmrelay.game.messaging.incoming.NameResultPacket;
import realmrelay.game.messaging.incoming.NewAbilityMessagePacket;
import realmrelay.game.messaging.incoming.NewTickPacket;
import realmrelay.game.messaging.incoming.NotificationPacket;
import realmrelay.game.messaging.incoming.PasswordPromptPacket;
import realmrelay.game.messaging.incoming.PingPacket;
import realmrelay.game.messaging.incoming.PlaySoundPacket;
import realmrelay.game.messaging.incoming.QuestObjIdPacket;
import realmrelay.game.messaging.incoming.QuestRedeemResponsePacket;
import realmrelay.game.messaging.incoming.ReconnectPacket;
import realmrelay.game.messaging.incoming.ReskinUnlockPacket;
import realmrelay.game.messaging.incoming.ServerPlayerShootPacket;
import realmrelay.game.messaging.incoming.ShowEffectPacket;
import realmrelay.game.messaging.incoming.TextPacket;
import realmrelay.game.messaging.incoming.TradeAcceptedPacket;
import realmrelay.game.messaging.incoming.TradeChangedPacket;
import realmrelay.game.messaging.incoming.TradeDonePacket;
import realmrelay.game.messaging.incoming.TradeRequestedPacket;
import realmrelay.game.messaging.incoming.TradeStartPacket;
import realmrelay.game.messaging.incoming.UpdatePacket;
import realmrelay.game.messaging.incoming.VerifyEmailPacket;
import realmrelay.game.messaging.incoming.arena.ArenaDeathPacket;
import realmrelay.game.messaging.incoming.arena.ImminentArenaWavePacket;
import realmrelay.game.messaging.incoming.pets.DeletePetMessagePacket;
import realmrelay.game.messaging.incoming.pets.Hatch_PetMessagePacket;
import realmrelay.game.messaging.outgoing.AcceptTradePacket;
import realmrelay.game.messaging.outgoing.ActivePetUpdateRequestPacket;
import realmrelay.game.messaging.outgoing.AoeAckPacket;
import realmrelay.game.messaging.outgoing.BuyPacket;
import realmrelay.game.messaging.outgoing.CancelTradePacket;
import realmrelay.game.messaging.outgoing.ChangeGuildRankPacket;
import realmrelay.game.messaging.outgoing.ChangeTradePacket;
import realmrelay.game.messaging.outgoing.CheckCreditsPacket;
import realmrelay.game.messaging.outgoing.ChooseNamePacket;
import realmrelay.game.messaging.outgoing.CreateGuildPacket;
import realmrelay.game.messaging.outgoing.CreatePacket;
import realmrelay.game.messaging.outgoing.EditAccountListPacket;
import realmrelay.game.messaging.outgoing.EnemyHitPacket;
import realmrelay.game.messaging.outgoing.EscapePacket;
import realmrelay.game.messaging.outgoing.GoToQuestRoomPacket;
import realmrelay.game.messaging.outgoing.GotoAckPacket;
import realmrelay.game.messaging.outgoing.GroundDamagePacket;
import realmrelay.game.messaging.outgoing.GuildInvitePacket;
import realmrelay.game.messaging.outgoing.GuildRemovePacket;
import realmrelay.game.messaging.outgoing.HelloPacket;
import realmrelay.game.messaging.outgoing.InvDropPacket;
import realmrelay.game.messaging.outgoing.InvSwapPacket;
import realmrelay.game.messaging.outgoing.JoinGuildPacket;
import realmrelay.game.messaging.outgoing.KeyInfoRequestPacket;
import realmrelay.game.messaging.outgoing.LoadPacket;
import realmrelay.game.messaging.outgoing.MovePacket;
import realmrelay.game.messaging.outgoing.OtherHitPacket;
import realmrelay.game.messaging.outgoing.PlayerHitPacket;
import realmrelay.game.messaging.outgoing.PlayerShootPacket;
import realmrelay.game.messaging.outgoing.PlayerTextPacket;
import realmrelay.game.messaging.outgoing.PongPacket;
import realmrelay.game.messaging.outgoing.RequestTradePacket;
import realmrelay.game.messaging.outgoing.ReskinPacket;
import realmrelay.game.messaging.outgoing.SetConditionPacket;
import realmrelay.game.messaging.outgoing.ShootAckPacket;
import realmrelay.game.messaging.outgoing.SquareHitPacket;
import realmrelay.game.messaging.outgoing.TeleportPacket;
import realmrelay.game.messaging.outgoing.UpdateAckPacket;
import realmrelay.game.messaging.outgoing.UseItemPacket;
import realmrelay.game.messaging.outgoing.UsePortalPacket;
import realmrelay.game.messaging.outgoing.arena.EnterArenaPacket;
import realmrelay.game.messaging.outgoing.arena.QuestRedeemPacket;
import realmrelay.packets.data.unused.IData;

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
