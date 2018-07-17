package rotmg.servers.model;

import alde.flash.utils.Vector;
import rotmg.net.Server;
import rotmg.parameters.Parameters;
import rotmg.servers.api.ServerModel;

public class FixedIPServerModel implements ServerModel {

	public static FixedIPServerModel instance;
	private Server localhost;

	public FixedIPServerModel() {
		super();
		this.localhost = new Server().setName("localhost").setPort(Parameters.PORT);
	}

	public static FixedIPServerModel getInstance() {
		if (instance == null) {
			instance = new FixedIPServerModel();
		}
		return instance;
	}

	public FixedIPServerModel setIP(String param1) {
		this.localhost.setAddress(param1);
		return this;
	}

	public Vector<Server> getServers() {
		return new Vector<Server>(this.localhost);
	}

	public void setServers(Vector<Server> param1) {
	}

	public Server getServer() {
		return this.localhost;
	}

	public boolean isServerAvailable() {
		return true;
	}
}