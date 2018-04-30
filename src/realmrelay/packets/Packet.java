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

import realmrelay.GETXmlParse;
import realmrelay.ROTMGRelay;
import realmrelay.data.IData;
import realmrelay.packets.client.AOEAckPacket;
import realmrelay.packets.client.AcceptTradePacket;
import realmrelay.packets.client.Accept_Arena_DeathPacket;
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
import realmrelay.packets.client.Enter_ArenaPacket;
import realmrelay.packets.client.EscapePacket;
import realmrelay.packets.client.GoToAckPacket;
import realmrelay.packets.client.GroundDamagePacket;
import realmrelay.packets.client.GuildInvitePacket;
import realmrelay.packets.client.GuildRemovePacket;
import realmrelay.packets.client.HelloPacket;
import realmrelay.packets.client.InvDropPacket;
import realmrelay.packets.client.InvSwapPacket;
import realmrelay.packets.client.JoinGuildPacket;
import realmrelay.packets.client.Key_Info_RequestPacket;
import realmrelay.packets.client.LoadPacket;
import realmrelay.packets.client.MovePacket;
import realmrelay.packets.client.OtherHitPacket;
import realmrelay.packets.client.PetYardUpdatePacket;
import realmrelay.packets.client.Pet_Change_Form_MSGPacket;
import realmrelay.packets.client.Pet_CommandPacket;
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
import realmrelay.packets.client.TinkerQuestPacket;
import realmrelay.packets.client.UpdateAckPacket;
import realmrelay.packets.client.UseItemPacket;
import realmrelay.packets.client.UsePortalPacket;
import realmrelay.packets.client.ViewQuestsPacket;
import realmrelay.packets.server.AOEPacket;
import realmrelay.packets.server.AccountListPacket;
import realmrelay.packets.server.AllyShootPacket;
import realmrelay.packets.server.ArenaDeathPacket;
import realmrelay.packets.server.ArenaNextWavePacket;
import realmrelay.packets.server.BuyResultPacket;
import realmrelay.packets.server.ClientStatPacket;
import realmrelay.packets.server.CreateGuildResultPacket;
import realmrelay.packets.server.CreateSuccessPacket;
import realmrelay.packets.server.DamagePacket;
import realmrelay.packets.server.DeathPacket;
import realmrelay.packets.server.EnemyShootPacket;
import realmrelay.packets.server.FailurePacket;
import realmrelay.packets.server.FilePacket;
import realmrelay.packets.server.Global_NotificationPacket;
import realmrelay.packets.server.GoToPacket;
import realmrelay.packets.server.Hatch_PetPacket;
import realmrelay.packets.server.InvResultPacket;
import realmrelay.packets.server.InvitedToGuildPacket;
import realmrelay.packets.server.Key_Info_ResponsePacket;
import realmrelay.packets.server.MapInfoPacket;
import realmrelay.packets.server.NameResultPacket;
import realmrelay.packets.server.NewTickPacket;
import realmrelay.packets.server.New_AbilityPacket;
import realmrelay.packets.server.NotificationPacket;
import realmrelay.packets.server.Password_PromptPacket;
import realmrelay.packets.server.PetEvolveResultPacket;
import realmrelay.packets.server.PicPacket;
import realmrelay.packets.server.PingPacket;
import realmrelay.packets.server.PlaySoundPacket;
import realmrelay.packets.server.QuestObjIdPacket;
import realmrelay.packets.server.Quest_Fetch_ResponsePacket;
import realmrelay.packets.server.Quest_Redeem_ResponsePacket;
import realmrelay.packets.server.ReconnectPacket;
import realmrelay.packets.server.RemovePetFromListPacket;
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
import realmrelay.packets.server.UpdatePetPacket;
import realmrelay.packets.server.VerifyEmailDialogPacket;

public abstract class Packet implements IData {

	private static final List<Class<? extends Packet>> packetIdtoClassMap = new ArrayList<Class<? extends Packet>>(127);

	public static void init() {
		for (int i = 0; i < 127; i++) {
			packetIdtoClassMap.add(null);
		}
		List<Class<? extends Packet>> list = new LinkedList<Class<? extends Packet>>();

		list.add(AcceptTradePacket.class);
		list.add(AOEAckPacket.class);
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
		list.add(Enter_ArenaPacket.class);
		list.add(EscapePacket.class);
		list.add(GoToAckPacket.class);
		list.add(GroundDamagePacket.class);
		list.add(GuildInvitePacket.class);
		list.add(GuildRemovePacket.class);
		list.add(HelloPacket.class);
		list.add(InvDropPacket.class);
		list.add(InvSwapPacket.class);
		list.add(JoinGuildPacket.class);
		list.add(Key_Info_RequestPacket.class);
		list.add(Accept_Arena_DeathPacket.class);
		list.add(LoadPacket.class);
		list.add(MovePacket.class);
		list.add(OtherHitPacket.class);
		list.add(Pet_CommandPacket.class);
		list.add(PetYardUpdatePacket.class);
		list.add(PlayerHitPacket.class);
		list.add(PlayerShootPacket.class);
		list.add(PlayerTextPacket.class);
		list.add(PongPacket.class);
		list.add(RequestTradePacket.class);
		list.add(ReskinPacket.class);
		list.add(Pet_Change_Form_MSGPacket.class);
		list.add(SetConditionPacket.class);
		list.add(ShootAckPacket.class);
		list.add(SquareHitPacket.class);
		list.add(TeleportPacket.class);
		list.add(TinkerQuestPacket.class);
		list.add(UpdateAckPacket.class);
		list.add(UseItemPacket.class);
		list.add(UsePortalPacket.class);
		list.add(ViewQuestsPacket.class);
		list.add(AccountListPacket.class);
		list.add(AllyShootPacket.class);
		list.add(AOEPacket.class);
		list.add(ArenaDeathPacket.class);
		list.add(ArenaNextWavePacket.class);
		list.add(BuyResultPacket.class);
		list.add(ClientStatPacket.class);
		list.add(CreateGuildResultPacket.class);
		list.add(CreateSuccessPacket.class);
		list.add(DamagePacket.class);
		list.add(DeathPacket.class);
		list.add(EnemyShootPacket.class);
		list.add(FailurePacket.class);
		list.add(FilePacket.class);
		list.add(Global_NotificationPacket.class);
		list.add(GoToPacket.class);
		list.add(Hatch_PetPacket.class);
		list.add(InvitedToGuildPacket.class);
		list.add(InvResultPacket.class);
		list.add(Key_Info_ResponsePacket.class);
		list.add(MapInfoPacket.class);
		list.add(NameResultPacket.class);
		list.add(New_AbilityPacket.class);
		list.add(NewTickPacket.class);
		list.add(NotificationPacket.class);
		list.add(Password_PromptPacket.class);
		list.add(PetEvolveResultPacket.class);
		list.add(PicPacket.class);
		list.add(PingPacket.class);
		list.add(PlaySoundPacket.class);
		list.add(Quest_Fetch_ResponsePacket.class);
		list.add(QuestObjIdPacket.class);
		list.add(Quest_Redeem_ResponsePacket.class);
		list.add(ReconnectPacket.class);
		list.add(RemovePetFromListPacket.class);
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
		list.add(UpdatePetPacket.class);
		list.add(VerifyEmailDialogPacket.class);
		try {
			ROTMGRelay.echo("Mapping " + GETXmlParse.packetMap.size() + " packets.");
			for (Class<? extends Packet> packetClass : list) {
				Packet packet = packetClass.newInstance();

				if (packet.id() == -1) {
					packetIdtoClassMap.set(100, packetClass);
				} else {
					packetIdtoClassMap.set(packet.id(), packetClass);
				}
			}
			for (Entry<String, Integer> entry : GETXmlParse.packetMap.entrySet()) {
				byte id = entry.getValue().byteValue();
				Packet packet = Packet.create(id);
				if (packet instanceof UnknownPacket) {
					//ROTMGRelay.echo("Warning : Not mapped packet : " + entry.getKey() + " -> " + id);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates new packet from packet id
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws InstantiationException 
	 */
	public static Packet create(byte id) throws Exception {
		Class<? extends Packet> packetClass = packetIdtoClassMap.get(id);
		if (packetClass == null) {
			UnknownPacket packet = new UnknownPacket();
			packet.setId(id);
			return packet;
		}
		return packetClass.newInstance();
	}

	/**
	 * Creates new packet from packet id and packet bytes
	 * @param id
	 * @param bytes
	 * @return
	 * @throws IOException
	 */
	public static Packet create(byte id, byte[] bytes) throws Exception {
		Packet packet = Packet.create(id);
		packet.parseFromInput(new DataInputStream(new ByteArrayInputStream(bytes)));
		int byteLength = packet.getBytes().length;
		if (byteLength != bytes.length) {
			ROTMGRelay.echo(packet + " byte length is " + byteLength + " after parsing, but was " + bytes.length
					+ " before parsing. Try updating your packets.xml");
		}
		return packet;
	}

	public byte[] getBytes() throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(baos);
		this.writeToOutput(out);
		return baos.toByteArray();
	}

	public String getName() {
		String simpleName = this.getClass().getSimpleName();
		simpleName = simpleName.substring(0, simpleName.indexOf("Packet"));
		return simpleName.toUpperCase();
	}

	public byte id() {
		String name = this.getName();
		Integer id = (Integer) GETXmlParse.packetMap.get(name);
		if (id == null) {
			return -1;
		}
		return id.byteValue();
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
