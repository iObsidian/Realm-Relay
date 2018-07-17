package rotmg.servers.control;

import java.util.List;

import alde.flash.utils.Vector;
import alde.flash.utils.XML;
import rotmg.net.Server;
import rotmg.parameters.Parameters;
import rotmg.servers.api.ServerModel;

public class ParseServerDataCommand {

	public ServerModel servers = ServerModel.getInstance();

	public XML data;

	public ParseServerDataCommand() {
		super();
	}

	public void execute() {
		this.servers.setServers(this.makeListOfServers());
	}

	private Vector<Server> makeListOfServers() {
		List<XML> loc1 = this.data.child("Servers").children("Server");
		Vector<Server> loc2 = new Vector<Server>(0);
		for (XML loc3 : loc1) {
			loc2.add(this.makeServer(loc3));
		}
		return loc2;
	}

	private Server makeServer(XML param1) {
		return new Server().setName(param1.getValue("Name")).setAddress(param1.getValue("DNS")).setPort(Parameters.PORT).setLatLong(param1.getDoubleValue("Lat"), param1.getDoubleValue("Long")).setUsage(param1.getDoubleValue("Usage")).setIsAdminOnly(param1.hasOwnProperty("AdminOnly"));

	}
}