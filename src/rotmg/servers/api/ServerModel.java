package rotmg.servers.api;

import alde.flash.utils.Vector;
<<<<<<< HEAD:src/kabam/rotmg/servers/api/ServerModel.java
import kabam.rotmg.net.Server;
<<<<<<< HEAD:src/rotmg/servers/api/ServerModel.java
=======
import kabam.rotmg.servers.model.FixedIPServerModel;
import rotmg.core.signals.SetScreenSignal;
import rotmg.net.Server;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/servers/api/ServerModel.java
=======
import kabam.rotmg.servers.model.FixedIPServerModel;
import rotmg.core.signals.SetScreenSignal;
import rotmg.net.Server;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/servers/api/ServerModel.java
import rotmg.servers.api.model.LiveServerModel;

public interface ServerModel {

	void setServers(Vector<Server> param1);

	Server getServer();

	boolean isServerAvailable();

	Vector<Server> getServers();

	static ServerModel getInstance() {
		return LiveServerModel.getInstance();
	}
}
