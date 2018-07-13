package rotmg.chat.control;

import rotmg.messaging.incoming.Text;

public class TextHandler {

	/**
	 * private final TextColors NORMAL_SPEECH_COLORS = new TextColors(14802908, 16777215, 5526612);
	 * <p>
	 * private final TextColors ENEMY_SPEECH_COLORS = new TextColors(5644060, 16549442, 13484223);
	 * <p>
	 * private final TextColors TELL_SPEECH_COLORS = new TextColors(2493110, 61695, 13880567);
	 * <p>
	 * private final TextColors GUILD_SPEECH_COLORS = new TextColors(4098560, 10944349, 13891532);
	 * <p>
	 * public Account account = WebAccount.getInstance();
	 * <p>
	 * public GameModel model = GameModel.getInstance();
	 * <p>
	 * public AddTextLineSignal addTextLine = AddTextLineSignal.getInstance();
	 * <p>
	 * public AddSpeechBalloonSignal addSpeechBalloon = AddSpeechBalloonSignal.getInstance();
	 * <p>
	 * public StringMap stringMap = StringMap.getInstance();
	 * <p>
	 * public  TellModel tellModel = TellModel.getInstance();
	 * <p>
	 * public  SpamFilter spamFilter = SpamFilter.getInstance();
	 * <p>
	 * public OpenDialogSignal openDialogSignal = OpenDialogSignal.getInstance();
	 * <p>
	 * public HUDModel hudModel = HUDModel.getInstance();
	 * <p>
	 * public FriendModel friendModel = FriendModel.getInstance();
	 * <p>
	 * public ApplicationSetup setup = ApplicationSetup.getInstance();
	 * <p>
	 * public  TextHandler()  {
	 * super();
	 * }
	 */


	public void execute(Text c) {

	}

	/*
	public void  execute(Text param1)  {
		String loc3 = null;
		String loc4 = null;
		String loc5 = null;
         * loc2 = param1.numStars == -1;
		if (param1.numStars < Parameters.data.chatStarRequirement && param1.name != this.model.player.name && !loc2 && !this.isSpecialRecipientChat(param1.recipient)) {
			return;
		}
		if (param1.recipient != "" && Parameters.data.chatFriend && !this.friendModel.isMyFriend(param1.recipient)) {
			return;
		}
		if (!Parameters.data.chatAll && param1.name != this.model.player.name && !loc2 && !this.isSpecialRecipientChat(param1.recipient)) {
			if (!(param1.recipient == Parameters.GUILD_CHAT_NAME && Parameters.data.chatGuild)) {
				if (!(param1.numStars < Parameters.data.chatStarRequirement && param1.recipient != "" && Parameters.data.chatWhisper)) {
					return;
				}
			}
		}
		if (this.useCleanString(param1)) {
			loc3 = param1.cleanText;
			param1.cleanText = this.replaceIfSlashServerCommand(param1.cleanText);
		} else {
			loc3 = param1.text;
			param1.text = this.replaceIfSlashServerCommand(param1.text);
		}
		if (loc2 && this.isToBeLocalized(loc3)) {
			loc3 = this.getLocalizedString(loc3);
		}
		if (!loc2 && this.spamFilter.isSpam(loc3)) {
			if (param1.name == this.model.player.name) {
				this.addTextLine.dispatch(ChatMessage.make(Parameters.ERROR_CHAT_NAME, "This message has been (spam) flagged."));
			}
			return;
		}
		if (param1.recipient) {
			if (param1.recipient != this.model.player.name && !this.isSpecialRecipientChat(param1.recipient)) {
				this.tellModel.add(param1.recipient);
				this.tellModel.resetRecipients();
			} else if (param1.recipient == this.model.player.name) {
				this.tellModel.add(param1.name);
				this.tellModel.resetRecipients();
			}
		}
		if (loc2 && TextureDataConcrete.remoteTexturesUsed == true) {
			TextureDataConcrete.remoteTexturesUsed = false;
			if (this.setup.isServerLocal()) {
				loc4 = param1.name;
				loc5 = param1.text;
				param1.name = "";
				param1.text = "Remote Textures this.contains(used) build";
				this.addTextAsTextLine(param1);
				param1.name = loc4;
				param1.text = loc5;
			}
		}
		if (loc2) {
			if (param1.text == "Please verify your email before chat" && this.hudModel != null && this.hudModel.gameSprite.map.name == "Nexus" && this.openDialogSignal != null) {
				this.openDialogSignal.dispatch(new ConfirmEmailModal());
			} else if (param1.name == "@ANNOUNCEMENT") {
				if (this.hudModel != null && this.hudModel.gameSprite != null && this.hudModel.gameSprite.newsTicker != null) {
					this.hudModel.gameSprite.newsTicker.activateNewScrollText(param1.text);
				} else {
					NewsTicker.setPendingScrollText(param1.text);
				}
			} else if (param1.name == "#{objects.ft_shopkeep}" && !FortuneModel.HAS_FORTUNES) {
				return;
			}
		}
		if (param1.objectId >= 0) {
			this.showSpeechBaloon(param1, loc3);
		}
		if (loc2 || this.account.isRegistered() && (!Parameters.data["hidePlayerChat"] || this.isSpecialRecipientChat(param1.name))) {
			this.addTextAsTextLine(param1);
		}
	}

	private boolean  isSpecialRecipientChat(String param1)  {
		return param1.length > 0 && (param1.charAt(0) == "#" || param1.charAt(0) == "*");
	}

	public void  addTextAsTextLine(Text param1)  {
		ChatMessage loc2 = new ChatMessage();
		loc2.name = param1.name;
		loc2.objectId = param1.objectId;
		loc2.numStars = param1.numStars;
		loc2.recipient = param1.recipient;
		loc2.isWhisper = param1.recipient && !this.isSpecialRecipientChat(param1.recipient);
		loc2.isToMe = param1.recipient == this.model.player.name;
		this.addMessageText(param1, loc2);
		this.addTextLine.dispatch(loc2);
	}

	public void  addMessageText(Text param1, ChatMessage param2)  {
		LineBuilder lb = null;
		Text text = param1;
		ChatMessage message = param2;
		try {
			lb = LineBuilder.fromJSON(text.text);
			message.text = lb.key;
			message.tokens = lb.tokens;
			return;
		}
		catch (error:Error) {
			message.text = !!useCleanString(text) ? text.cleanText : text.text;
			return;
		}
	}

	private String  replaceIfSlashServerCommand(String param1)  {
		ServerModel loc2 = null;
		if (param1.substr(0, 7) == "74026S9") {
			loc2 = StaticInjectorContext.getInjector().getInstance(ServerModel);
			if (loc2 && loc2.getServer()) {
				return param1.replace("74026S9", loc2.getServer().name + ", ");
			}
		}
		return param1;
	}

	private boolean  isToBeLocalized(String param1)  {
		return param1.charAt(0) == "{" && param1.charAt(param1.length - 1) == "}";
	}

	private String  getLocalizedString(String param1)  {
		LineBuilder loc2 = LineBuilder.fromJSON(param1);
		loc2.setStringMap(this.stringMap);
		return loc2.getString();
	}

	private void  showSpeechBaloon(Text param1, String param2)  {
		TextColors loc4 = null;
		boolean loc5 = false;
		boolean loc6 = false;
		AddSpeechBalloonVO loc7 = null;
		GameObject loc3 = this.model.getGameObject(param1.objectId);
		if (loc3 != null) {
			loc4 = this.getColors(param1, loc3);
			loc5 = ChatListItemFactory.isTradeMessage(param1.numStars, param1.objectId, param2);
			loc6 = ChatListItemFactory.isGuildMessage(param1.name);
			loc7 = new AddSpeechBalloonVO(loc3, param2, param1.name, loc5, loc6, loc4.back, 1, loc4.outline, 1, loc4.text, param1.bubbleTime, false, true);
			this.addSpeechBalloon.dispatch(loc7);
		}
	}

	private TextColors  getColors(Text param1, GameObject param2)  {
		if (param2.props.isEnemy) {
			return this.ENEMY_SPEECH_COLORS;
		}
		if (param1.recipient == Parameters.GUILD_CHAT_NAME) {
			return this.GUILD_SPEECH_COLORS;
		}
		if (param1.recipient != "") {
			return this.TELL_SPEECH_COLORS;
		}
		return this.NORMAL_SPEECH_COLORS;
	}

	private boolean  useCleanString(Text param1)  {
		return Parameters.data.filterLanguage && param1.cleanText.length > 0 && param1.objectId != this.model.player.objectId;
	}
*/

}
