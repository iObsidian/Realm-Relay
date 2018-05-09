package realmrelay.game.ui;

import java.util.ArrayList;
import java.util.List;

public class Options {

    //private static final List<String> TABS = new <String>[TextKey.OPTIONS_CONTROLS, TextKey.OPTIONS_HOTKEYS, TextKey.OPTIONS_CHAT, TextKey.OPTIONS_GRAPHICS, TextKey.OPTIONS_SOUND, TextKey.OPTIONS_FRIEND, "Debuffs", "Misc", "Aim Assist", "Auto Ability", "Loot Notif", "Recon", "Sprite World", "HP Display", "Other"];
    public static final int Y_POSITION = 550;
    public static final String CHAT_COMMAND = "chatCommand";
    public static final String CHAT = "chat";
    public static final String TELL = "tell";
    public static final String GUILD_CHAT = "guildChat";
    public static final String SCROLL_CHAT_UP = "scrollChatUp";
    public static final String SCROLL_CHAT_DOWN = "scrollChatDown";
    private static List<String> registeredCursors = new ArrayList<String>(0);

    // Unimplemented
    public static void refreshCursor() {
    }
}
