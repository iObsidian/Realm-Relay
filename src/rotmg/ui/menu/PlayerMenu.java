package rotmg.ui.menu;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import rotmg.ui.TeleportMenuOption;
import flash.events.Event;
import flash.events.MouseEvent;
import rotmg.AGameSprite;
import rotmg.objects.Player;
import rotmg.text.view.stringBuilder.LineBuilder;
import rotmg.text.view.stringBuilder.TextFieldDisplayConcrete;
import rotmg.ui.GameObjectListItem;
import rotmg.util.AssetLibrary;
import rotmg.util.GuildUtil;
import rotmg.util.TextKey;
import spark.filters.DropShadowFilter;

public class PlayerMenu extends Menu {

	public AGameSprite gs;

	public String playerName;

	public Player player;

	public GameObjectListItem playerPanel;

	public TextFieldDisplayConcrete namePlate;

	public PlayerMenu() {
		super(3552822, 16777215);
	}

	public void initDifferentServer(AGameSprite param1, String param2, boolean param3, boolean param4) {
		MenuOption loc5 = null;
		this.gs = param1;
		this.playerName = param2;
		this.player = null;
		this.namePlate = new TextFieldDisplayConcrete().setSize(13).setColor(16572160).setHTML(true);
		this.namePlate.setStringBuilder(new LineBuilder().setParams(this.playerName));
		this.namePlate.filters = new Vector<>(new DropShadowFilter(0, 0, 0));
		addChild(this.namePlate);
		this.yOffset = this.yOffset - 13;
		loc5 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 21), 16777215, TextKey.PLAYERMENU_PM);
		loc5.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onPrivateMessage));
		addOption(loc5);
		loc5 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 8), 16777215, TextKey.FRIEND_BLOCK_BUTTON);
		loc5.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onIgnoreDifferentServer));
		addOption(loc5);
		loc5 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 18), 16777215, "Add Friend");
		loc5.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onAddFriend));
		addOption(loc5);
	}

	private void onIgnoreDifferentServer(Event param1) {
		this.gs.gsc.playerText("/ignore " + this.playerName);
		remove();
	}

	public void init(AGameSprite param1, Player param2) {
		MenuOption loc3 = null;
		this.gs = param1;
		this.playerName = param2.name;
		this.player = param2;
		this.playerPanel = new GameObjectListItem(11776947, true, this.player, true);
		this.yOffset = this.yOffset + 7;
		addChild(this.playerPanel);
		if (Player.isAdmin || Player.isMod) {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 10), 16777215, "Ban MultiBoxer");
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onKickMultiBox));
			addOption(loc3);
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 10), 16777215, "Ban RWT");
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onKickRWT));
			addOption(loc3);
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 10), 16777215, "Ban Cheat");
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onKickCheat));
			addOption(loc3);
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 4), 16777215, TextKey.PLAYERMENU_MUTE);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onMute));
			addOption(loc3);
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 3), 16777215, TextKey.PLAYERMENU_UNMUTE);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onUnMute));
			addOption(loc3);
		}
		if (this.gs.map.allowPlayerTeleport() && this.player.isTeleportEligible(this.player)) {
			loc3 = new TeleportMenuOption(this.gs.map.player);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onTeleport));
			addOption(loc3);
		}
		if (this.gs.map.player.guildRank >= GuildUtil.OFFICER && (param2.guildName == null || param2.guildName.length() == 0)) {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 10), 16777215, TextKey.PLAYERMENU_INVITE);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onInvite));
			addOption(loc3);
		}
		if (!this.player.starred) {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterface2", 5), 16777215, TextKey.PLAYERMENU_LOCK);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onLock));
			addOption(loc3);
		} else {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterface2", 6), 16777215, TextKey.PLAYERMENU_UNLOCK);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onUnlock));
			addOption(loc3);
		}
		loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 7), 16777215, TextKey.PLAYERMENU_TRADE);
		loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onTrade));
		addOption(loc3);
		loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 21), 16777215, TextKey.PLAYERMENU_PM);
		loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onPrivateMessage));
		addOption(loc3);
		if (this.player.isFellowGuild) {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 21), 16777215, TextKey.PLAYERMENU_GUILDCHAT);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onGuildMessage));
			addOption(loc3);
		}
		if (!this.player.ignored) {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 8), 16777215, TextKey.FRIEND_BLOCK_BUTTON);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onIgnore));
			addOption(loc3);
		} else {
			loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 9), 16777215, TextKey.PLAYERMENU_UNIGNORE);
			loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onUnignore));
			addOption(loc3);
		}
		loc3 = new MenuOption(AssetLibrary.getImageFromSet("lofiInterfaceBig", 18), 16777215, "Add Friend");
		loc3.addEventListener(MouseEvent.CLICK, new EventConsumer<>(this::onAddFriend));
		addOption(loc3);
	}

	private void onKickMultiBox(Event param1) {
		this.gs.gsc.playerText("/kick " + this.player.name + " Multiboxing");
		remove();
	}

	private void onKickRWT(Event param1) {
		this.gs.gsc.playerText("/kick " + this.player.name + " RWT");
		remove();
	}

	private void onKickCheat(Event param1) {
		this.gs.gsc.playerText("/kick " + this.player.name + " Cheating");
		remove();
	}

	private void onMute(Event param1) {
		this.gs.gsc.playerText("/mute " + this.player.name);
		remove();
	}

	private void onUnMute(Event param1) {
		this.gs.gsc.playerText("/unmute " + this.player.name);
		remove();
	}

	private void onPrivateMessage(Event param1) {
		/*ShowChatInputSignal loc2 = ShowChatInputSignal.getInstance();
		loc2.dispatch(true, "/tell " + this.playerName + " ");
		remove();*/
	}

	private void onAddFriend(Event param1) {
		/*FriendActionSignal loc2 = StaticInjectorContext.getInjector().getInstance(FriendActionSignal);
		loc2.dispatch(new FriendRequestVO(FriendsActions.INVITE, this.playerName));
		remove();*/
	}

	private void onTradeMessage(Event param1) {
		/*ShowChatInputSignal loc2 = StaticInjectorContext.getInjector().getInstance(ShowChatInputSignal);
		loc2.dispatch(true, "/trade " + this.playerName);
		remove();*/
	}

	private void onGuildMessage(Event param1) {
		/*ShowChatInputSignal loc2 = StaticInjectorContext.getInjector().getInstance(ShowChatInputSignal);
		loc2.dispatch(true, "/g ");
		remove();*/
	}

	private void onTeleport(Event param1) {
		this.gs.map.player.teleportTo(this.player);
		remove();
	}

	private void onInvite(Event param1) {
		this.gs.gsc.guildInvite(this.playerName);
		remove();
	}

	private void onLock(Event param1) {
		this.gs.map.party.lockPlayer(this.player);
		remove();
	}

	private void onUnlock(Event param1) {
		this.gs.map.party.unlockPlayer(this.player);
		remove();
	}

	private void onTrade(Event param1) {
		this.gs.gsc.requestTrade(this.playerName);
		remove();
	}

	private void onIgnore(Event param1) {
		this.gs.map.party.ignorePlayer(this.player);
		remove();
	}

	private void onUnignore(Event param1) {
		this.gs.map.party.unignorePlayer(this.player);
		remove();
	}
}
