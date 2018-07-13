package rotmg.servers.api;

import alde.flash.utils.Vector;
import rotmg.net.Server;
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
