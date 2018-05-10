package realmrelay.packets.old;

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
import realmrelay.game.dailyQuests.QuestRedeemResponse;
import realmrelay.game.messaging.incoming.AccountList;
import realmrelay.game.messaging.incoming.AllyShoot;
import realmrelay.game.messaging.incoming.Aoe;
import realmrelay.game.messaging.incoming.BuyResult;
import realmrelay.game.messaging.incoming.ClientStat;
import realmrelay.game.messaging.incoming.CreateSuccess;
import realmrelay.game.messaging.incoming.Damage;
import realmrelay.game.messaging.incoming.Death;
import realmrelay.game.messaging.incoming.EnemyShoot;
import realmrelay.game.messaging.incoming.EvolvedPetMessage;
import realmrelay.game.messaging.incoming.Failure;
import realmrelay.game.messaging.incoming.File;
import realmrelay.game.messaging.incoming.GlobalNotification;
import realmrelay.game.messaging.incoming.Goto;
import realmrelay.game.messaging.incoming.GuildResult;
import realmrelay.game.messaging.incoming.InvResult;
import realmrelay.game.messaging.incoming.KeyInfoResponse;
import realmrelay.game.messaging.incoming.MapInfo;
import realmrelay.game.messaging.incoming.NameResult;
import realmrelay.game.messaging.incoming.NewAbilityMessage;
import realmrelay.game.messaging.incoming.NewTick;
import realmrelay.game.messaging.incoming.Notification;
import realmrelay.game.messaging.incoming.PasswordPrompt;
import realmrelay.game.messaging.incoming.Ping;
import realmrelay.game.messaging.incoming.PlaySound;
import realmrelay.game.messaging.incoming.QuestObjId;
import realmrelay.game.messaging.incoming.Reconnect;
import realmrelay.game.messaging.incoming.ReskinUnlock;
import realmrelay.game.messaging.incoming.ServerPlayerShoot;
import realmrelay.game.messaging.incoming.ShowEffect;
import realmrelay.game.messaging.incoming.Text;
import realmrelay.game.messaging.incoming.TradeAccepted;
import realmrelay.game.messaging.incoming.TradeChanged;
import realmrelay.game.messaging.incoming.TradeDone;
import realmrelay.game.messaging.incoming.TradeRequested;
import realmrelay.game.messaging.incoming.TradeStart;
import realmrelay.game.messaging.incoming.Update;
import realmrelay.game.messaging.incoming.VerifyEmail;
import realmrelay.game.messaging.incoming.arena.ArenaDeath;
import realmrelay.game.messaging.incoming.arena.ImminentArenaWave;
import realmrelay.game.messaging.incoming.pets.DeletePetMessage;
import realmrelay.game.messaging.incoming.pets.Hatch_PetMessage;
import realmrelay.game.messaging.outgoing.AcceptTradePacket;
import realmrelay.game.messaging.outgoing.ActivePetUpdateRequestPacket;
import realmrelay.game.messaging.outgoing.AoeAckPacket;
import realmrelay.game.messaging.outgoing.BuyPacket;
import realmrelay.game.messaging.outgoing.CancelTradePacket;
import realmrelay.game.messaging.outgoing.ChangeGuildRankPacket;
import realmrelay.game.messaging.outgoing.ChangeTradePacket;
import realmrelay.game.messaging.outgoing.CheckCreditsPacket;
import realmrelay.game.messaging.outgoing.ChooseName;
import realmrelay.game.messaging.outgoing.Create;
import realmrelay.game.messaging.outgoing.CreateGuild;
import realmrelay.game.messaging.outgoing.EditAccountList;
import realmrelay.game.messaging.outgoing.EnemyHit;
import realmrelay.game.messaging.outgoing.Escape;
import realmrelay.game.messaging.outgoing.GoToQuestRoom;
import realmrelay.game.messaging.outgoing.GotoAck;
import realmrelay.game.messaging.outgoing.GroundDamage;
import realmrelay.game.messaging.outgoing.GuildInvite;
import realmrelay.game.messaging.outgoing.GuildRemove;
import realmrelay.game.messaging.outgoing.Hello;
import realmrelay.game.messaging.outgoing.InvDrop;
import realmrelay.game.messaging.outgoing.InvSwap;
import realmrelay.game.messaging.outgoing.JoinGuild;
import realmrelay.game.messaging.outgoing.KeyInfoRequest;
import realmrelay.game.messaging.outgoing.Load;
import realmrelay.game.messaging.outgoing.Move;
import realmrelay.game.messaging.outgoing.OtherHit;
import realmrelay.game.messaging.outgoing.PlayerHit;
import realmrelay.game.messaging.outgoing.PlayerShoot;
import realmrelay.game.messaging.outgoing.PlayerText;
import realmrelay.game.messaging.outgoing.Pong;
import realmrelay.game.messaging.outgoing.RequestTrade;
import realmrelay.game.messaging.outgoing.Reskin;
import realmrelay.game.messaging.outgoing.SetCondition;
import realmrelay.game.messaging.outgoing.ShootAck;
import realmrelay.game.messaging.outgoing.SquareHit;
import realmrelay.game.messaging.outgoing.Teleport;
import realmrelay.game.messaging.outgoing.UpdateAck;
import realmrelay.game.messaging.outgoing.UseItem;
import realmrelay.game.messaging.outgoing.UsePortal;
import realmrelay.game.messaging.outgoing.arena.EnterArena;
import realmrelay.game.messaging.outgoing.arena.QuestRedeem;
import realmrelay.packets.data.unused.IData;

public abstract class Packet implements IData {

	private static final List<Class<? extends Packet>> packetIdtoClassMap = new ArrayList<Class<? extends Packet>>();
	public static boolean init;

	/**
	 * Creates new packet from packet id
	 *
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
		list.add(ChooseName.class);
		list.add(CreateGuild.class);
		list.add(Create.class);
		list.add(EditAccountList.class);
		list.add(EnemyHit.class);
		list.add(Escape.class);
		list.add(GotoAck.class);
		list.add(GoToQuestRoom.class);
		list.add(GroundDamage.class);
		list.add(GuildInvite.class);
		list.add(GuildRemove.class);
		list.add(Hello.class);
		list.add(InvDrop.class);
		list.add(InvSwap.class);
		list.add(JoinGuild.class);
		list.add(KeyInfoRequest.class);
		list.add(Load.class);
		list.add(Move.class);
		list.add(OtherHit.class);
		list.add(PlayerHit.class);
		list.add(PlayerShoot.class);
		list.add(PlayerText.class);
		list.add(Pong.class);
		list.add(RequestTrade.class);
		list.add(Reskin.class);
		list.add(SetCondition.class);
		list.add(ShootAck.class);
		list.add(SquareHit.class);
		list.add(Teleport.class);
		list.add(UpdateAck.class);
		list.add(UseItem.class);
		list.add(UsePortal.class);
		list.add(EnterArena.class);
		list.add(QuestRedeem.class);
		list.add(AccountList.class);
		list.add(AllyShoot.class);
		list.add(Aoe.class);
		list.add(BuyResult.class);
		list.add(ClientStat.class);
		list.add(CreateSuccess.class);
		list.add(Damage.class);
		list.add(Death.class);
		list.add(EnemyShoot.class);
		list.add(EvolvedPetMessage.class);
		list.add(Failure.class);
		list.add(File.class);
		list.add(GlobalNotification.class);
		list.add(Goto.class);
		list.add(GuildResult.class);
		list.add(InvResult.class);
		list.add(KeyInfoResponse.class);
		list.add(MapInfo.class);
		list.add(NameResult.class);
		list.add(NewAbilityMessage.class);
		list.add(NewTick.class);
		list.add(Notification.class);
		list.add(PasswordPrompt.class);
		list.add(Ping.class);
		list.add(PlaySound.class);
		list.add(QuestObjId.class);
		list.add(QuestRedeemResponse.class);
		list.add(Reconnect.class);
		list.add(ReskinUnlock.class);
		list.add(ServerPlayerShoot.class);
		list.add(ShowEffect.class);
		list.add(Text.class);
		list.add(TradeAccepted.class);
		list.add(TradeChanged.class);
		list.add(TradeDone.class);
		list.add(TradeRequested.class);
		list.add(TradeStart.class);
		list.add(Update.class);
		list.add(VerifyEmail.class);
		list.add(ArenaDeath.class);
		list.add(ImminentArenaWave.class);
		list.add(DeletePetMessage.class);
		list.add(Hatch_PetMessage.class);

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
