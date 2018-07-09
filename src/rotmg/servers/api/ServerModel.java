package rotmg.servers.api;

import alde.flash.utils.Vector;
<<<<<<< HEAD:src/kabam/rotmg/servers/api/ServerModel.java
import kabam.rotmg.net.Server;
=======
import kabam.rotmg.servers.model.FixedIPServerModel;
import rotmg.core.signals.SetScreenSignal;
import rotmg.net.Server;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/servers/api/ServerModel.java
import rotmg.servers.api.model.LiveServerModel;

public interface ServerModel {

	static ServerModel getInstance() {
		return LiveServerModel.getInstance();
	}

	Server getServer();

	boolean isServerAvailable();

	Vector<Server> getServers();

	void setServers(Vector<Server> param1);
}
