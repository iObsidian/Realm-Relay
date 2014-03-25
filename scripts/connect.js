// connect.js
// authored by Nisuxen

var ID_PLAYER_TEXT = $.findPacketId("PLAYERTEXT");

var servers = { "usw"	: "54.241.208.233",
				"usw2"	: "54.193.168.4",
				"use"	: "4.224.68.81",
				"use2"	: "54.204.50.57",
				"use3"  : "54.226.214.216",
				"uss"	: "23.22.180.212",
				"uss2"	: "50.19.7.133",
				"uss3"	: "54.80.250.47",
				"usmw"	: "54.80.67.112",
				"usmw2"	: "50.17.143.165",
				"ussw"	: "54.219.44.205",
				"usnw"	: "50.18.24.120",
				"euw"	: "54.195.57.43",
				"euw2"	: "54.195.154.140",
				"eue"	: "46.137.30.179",
				"eus"	: "54.195.179.215",
				"eun"	: "54.195.96.152",
				"eun2"	: "54.216.200.98",
				"eusw"	: "54.217.63.70",
				"ae"	: "175.41.201.80",
				"ase"	: "54.255.15.39" };

var usage = 
"Usage: /con <server>\n \
\t\tusw   	\n \
\t\tusw2	\n \
\t\tuse		\n \
\t\tuse2	\n \
\t\tuse3	\n \
\t\tuss		\n \
\t\tuss2	\n \
\t\tuss3	\n \
\t\tusmw	\n \
\t\tusmw2	\n \
\t\tussw	\n \
\t\tusnw	\n \
\t\teuw		\n \
\t\teuw2	\n \
\t\teue		\n \
\t\teus		\n \
\t\teun		\n \
\t\teun2	\n \
\t\teusw	\n \
\t\tae		\n \
\t\tase		\n";

function onClientPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_PLAYER_TEXT: {
			var text = packet.text.toLowerCase();
			if(text.length() >= 4 && text.substring(0,4) == "/con"){
				event.cancel();

				if(text.length() <= 5){
					event.echo(usage)
					break;
				}
				var server = text.substring(5, text.length());

				if(servers[server] == null)
					event.echo("Server " + server + " not found.");
				else{
					event.setGameIdSocketAddress(-2, servers[server], event.getRemotePort());
					event.kickUser();
				}
			}
			break;
		}
	}
}