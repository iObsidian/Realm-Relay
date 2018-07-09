package rotmg.servers.api.model;

import alde.flash.utils.Vector;
<<<<<<< HEAD:src/kabam/rotmg/servers/api/model/LiveServerModel.java
import kabam.rotmg.core.model.PlayerModel;
import kabam.rotmg.net.LatLong;
import kabam.rotmg.net.Server;
import kabam.rotmg.parameters.Parameters;
import kabam.rotmg.servers.api.ServerModel;
<<<<<<< HEAD:src/rotmg/servers/api/model/LiveServerModel.java
=======
=======
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/servers/api/model/LiveServerModel.java
import rotmg.core.model.PlayerModel;
import rotmg.net.LatLong;
import rotmg.net.Server;
import rotmg.parameters.Parameters;
import rotmg.servers.api.ServerModel;
<<<<<<< HEAD:src/rotmg/servers/api/model/LiveServerModel.java
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/servers/api/model/LiveServerModel.java
=======
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/servers/api/model/LiveServerModel.java

/**
 * 100% match
 */
public class LiveServerModel implements ServerModel {

	public static LiveServerModel instance;

	public static LiveServerModel getInstance() {
		if (instance == null) {
			instance = new LiveServerModel();
		}
		return instance;
	}

	public PlayerModel model = PlayerModel.getInstance();

	private boolean _descendingFlag;

	private final Vector<Server> servers = new Vector<Server>(0);

	public LiveServerModel() {
		super();
	}

	public void setServers(Vector<Server> param1) {
		this.servers.length = 0;
		for (Server loc2:param1) {
			this.servers.add(loc2);
		}
		this._descendingFlag = false;
		this.servers.sort(this::compareServerName);
	}

	public Vector<Server> getServers() {
		return this.servers;
	}

	public Server getServer() {
		int loc7 = 0;
		double loc8 = 0;
		boolean loc1 = this.model.isAdmin();
		LatLong loc2 = this.model.getMyPos();
		Server loc3 = null;
		double loc4 = Double.MAX_VALUE;
		int loc5 = Integer.MAX_VALUE;
		for (Server loc6: this.servers) {
			if (!(loc6.isFull() && !loc1)) {
				if (loc6.name.equals(Parameters.data.preferredServer)) {
					return loc6;
				}
				loc7 = loc6.priority();
				loc8 = LatLong.distance(loc2, loc6.latLong);
				if (loc7 < loc5 || loc7 == loc5 && loc8 < loc4) {
					loc3 = loc6;
					loc4 = loc8;
					loc5 = loc7;
					Parameters.data.bestServer = loc3.name;
					Parameters.save();
				}
			}
		}
		return loc3;
	}

	public String getServerNameByAddress(String param1) {
		for (Server loc2: this.servers) {
			if (loc2.address.equals(param1)) {
				return loc2.name;
			}
		}
		return "";
	}

	public boolean isServerAvailable() {
		return this.servers.length > 0;
	}

	private int compareServerName(Server param1, Server param2) {
		/**if (param1.name < param2.name) {
			return !!this._descendingFlag ? -1 : 1;
		}
		if (param1.name > param2.name) {
			return !!this._descendingFlag ? 1 : -1;
		}*/
		return 0;
	}
}
