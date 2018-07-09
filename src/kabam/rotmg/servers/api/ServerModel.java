package kabam.rotmg.servers.api;

import alde.flash.utils.Vector;
import kabam.rotmg.net.Server;
import kabam.rotmg.servers.model.FixedIPServerModel;
import rotmg.core.signals.SetScreenSignal;
import rotmg.net.Server;
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
