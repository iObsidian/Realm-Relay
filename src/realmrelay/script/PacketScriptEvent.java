package realmrelay.script;

import realmrelay.User;
import realmrelay.packets.Packet;

public class PacketScriptEvent extends ScriptEvent {

	private final Packet packet;

	private boolean bCancelled = false;

	protected PacketScriptEvent(User user, Packet packet) {
		super(user);
		this.packet = packet;
	}

	public void cancel() {
		this.bCancelled = true;
	}

	public Packet getPacket() {
		return this.packet;
	}

	public boolean isCancelled() {
		return this.bCancelled;
	}

}
