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
import realmrelay.packets.client.*;
import realmrelay.packets.server.*;


public abstract class Packet implements IData {

	private static final List<Class<? extends Packet>> packetIdtoClassMap = new ArrayList<Class<? extends Packet>>(127);
	
	public static void init() {
		for (int i = 0; i < 127; i++) {
			packetIdtoClassMap.add(null);
		}
		List<Class<? extends Packet>> list = new LinkedList<Class<? extends Packet>>();
		
		list.add(AcceptTradePacket.class);
		list.add(AccountListPacket.class);
		list.add(AllyShootPacket.class);
		list.add(AOEAckPacket.class);
		list.add(AOEPacket.class);
		list.add(BuyPacket.class);
		list.add(BuyResultPacket.class);
		list.add(CancelTradePacket.class);
		list.add(ChangeGuildRankPacket.class);
		list.add(ChangeTradePacket.class);
		list.add(CheckCreditsPacket.class);
		list.add(ChooseNamePacket.class);
		list.add(ClientStatPacket.class);
		list.add(Create_SuccessPacket.class);
		list.add(CreateGuildPacket.class);
		list.add(CreateGuildResultPacket.class);
		list.add(CreatePacket.class);
		list.add(DamagePacket.class);
		list.add(DeathPacket.class);
		list.add(EditAccountListPacket.class);
		list.add(EnemyHitPacket.class);
		list.add(EscapePacket.class);
		list.add(FailurePacket.class);
		list.add(FilePacket.class);
		list.add(Global_NotificationPacket.class);
		list.add(GoToAckPacket.class);
		list.add(GoToPacket.class);
		list.add(GroundDamagePacket.class);
		list.add(GuildInvitePacket.class);
		list.add(GuildRemovePacket.class);
		list.add(HelloPacket.class);
		list.add(InvDropPacket.class);
		list.add(InvitedToGuildPacket.class);
		list.add(InvResultPacket.class);
		list.add(InvSwapPacket.class);
		list.add(JoinGuildPacket.class);
		list.add(LoadPacket.class);
		list.add(MapInfoPacket.class);
		list.add(MovePacket.class);
		list.add(NameResultPacket.class);
		list.add(New_TickPacket.class);
		list.add(NotificationPacket.class);
		list.add(OtherHitPacket.class);
		list.add(PicPacket.class);
		list.add(PingPacket.class);
		list.add(PlayerHitPacket.class);
		list.add(PlayerShootPacket.class);
		list.add(PlayerTextPacket.class);
		list.add(PlaySoundPacket.class);
		list.add(PongPacket.class);
		list.add(QuestObjIdPacket.class);
		list.add(ReconnectPacket.class);
		list.add(RequestTradePacket.class);
		list.add(ReskinPacket.class);
		list.add(SetConditionPacket.class);
		list.add(Shoot2Packet.class);
		list.add(ShootAckPacket.class);
		list.add(ShootPacket.class);
		list.add(Show_EffectPacket.class);
		list.add(SquareHitPacket.class);
		list.add(TeleportPacket.class);
		list.add(TextPacket.class);
		list.add(TradeAcceptedPacket.class);
		list.add(TradeChangedPacket.class);
		list.add(TradeDonePacket.class);
		list.add(TradeRequestedPacket.class);
		list.add(TradeStartPacket.class);
		list.add(UpdateAckPacket.class);
		list.add(UpdatePacket.class);
		list.add(UseItemPacket.class);
		list.add(UsePortalPacket.class);
		try {
			ROTMGRelay.echo("Mapping " + GETXmlParse.packetMap.size() +" packets.");
			for (Class<? extends Packet> packetClass: list) {
				Packet packet = packetClass.newInstance();
				
				
				if (packet.id() == -1){
					packetIdtoClassMap.set(100, packetClass);
				} else {
					packetIdtoClassMap.set(packet.id(), packetClass);
				}
			}
			for (Entry<String, Integer> entry: GETXmlParse.packetMap.entrySet()) {
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
			ROTMGRelay.echo(packet + " byte length is " + byteLength + " after parsing, but was " + bytes.length + " before parsing. Try updating your packets.xml");
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
