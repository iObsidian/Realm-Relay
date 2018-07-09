package rotmg.servers.api;

import alde.flash.utils.Vector;
import rotmg.net.Server;

public interface ServerModel {

	void setServers(Vector<Server> param1);

	Server getServer();

	Boolean isServerAvailable();

	Vector<Server> getServers();

}
