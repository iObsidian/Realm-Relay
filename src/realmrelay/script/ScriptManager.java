package realmrelay.script;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


import realmrelay.PacketManager;
import realmrelay.ROTMGRelay;
import realmrelay.User;
import realmrelay.packets.Packet;


public class ScriptManager {
	
	private final List<Invocable> scripts = new ArrayList<Invocable>();
	private final List<ScheduledScriptEvent> newScheduledEvents = new Vector<ScheduledScriptEvent>();
	private final List<ScheduledScriptEvent> scheduledEvents = new ArrayList<ScheduledScriptEvent>();
	private final User user;
	
	public ScriptManager(User user) {
		this.user = user;
		File folder = new File("scripts/");
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				int lastIndex = name.lastIndexOf(".");
				if (lastIndex == -1) {
					return false;
				}
				return name.substring(lastIndex).equalsIgnoreCase(".js");
			}
			
		};
		File[] files = folder.listFiles(filter);
		Object scriptEvent = new ScriptEvent(this.user);
		for (File file: files) {
			ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
			try {
				scriptEngine.put("$", scriptEvent);
				scriptEngine.eval(new FileReader(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (ScriptException e) {
				ROTMGRelay.error(e.getMessage());
			}
			this.scripts.add((Invocable) scriptEngine);
		}
	}

	public PacketScriptEvent clientPacketEvent(Packet packet) throws Exception {
		PacketScriptEvent event = new PacketScriptEvent(this.user, packet);
		PacketManager.onClientPacketEvent(event);
		this.invoke("onClientPacket", event);
		return event;
	}
	
	public void fireExpiredEvents() {
		while (!this.newScheduledEvents.isEmpty()) {
			ScheduledScriptEvent event = this.newScheduledEvents.remove(0);
			this.scheduledEvents.add(event);
		}
		Iterator<ScheduledScriptEvent> i = this.scheduledEvents.iterator();
		while (i.hasNext()) {
			ScheduledScriptEvent event = i.next();
			if (event.isExpired()) {
				String eventMethod = event.getEventMethod();
				Object[] args = event.getArguments();
				this.invoke(eventMethod, event, args);
				i.remove();
			}
		}
	}
	
	public void invoke(String method, ScriptEvent event, Object... args) {
		Object[] args0 = new Object[args.length + 1];
		args0[0] = event;
		for (int i = 0; i < args.length; i++) {
			args0[i + 1] = args[i];
		}
		for (Invocable script: this.scripts) {
			try {
				script.invokeFunction(method, args0);
			} catch (NoSuchMethodException e) {
				//
			} catch (ScriptException e) {
				ROTMGRelay.error(e.getMessage());
			}
		}
	}

	public void scheduleEvent(double seconds, String eventMethod, Object... objects) {
		ScheduledScriptEvent event = new ScheduledScriptEvent(this.user, seconds, eventMethod, objects);
		this.newScheduledEvents.add(event);
	}

	public PacketScriptEvent serverPacketEvent(Packet packet) throws Exception {
		PacketScriptEvent event = new PacketScriptEvent(this.user, packet);
		PacketManager.onServerPacketEvent(event);
		this.invoke("onServerPacket", event);
		return event;
	}
	
	public void trigger(String eventMethod, Object... objects) {
		this.scheduleEvent(0, eventMethod, objects);
	}

}
