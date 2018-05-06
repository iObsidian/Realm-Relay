// spamfilter.js

var ID_TEXT = $.findPacketId("TEXT");
var ID_PLAYERTEXT = $.findPacketId("PLAYER_TEXT");

var keywords = [
	"oryxsh0p",
	".net",
];

function onServerPacket(event) {
	var packet = event.getPacket();
	
	if (packet.id() == ID_TEXT) {
	
		// make text lowercase to match the keyword list
		var text = packet.text.toLowerCase();
		var name = packet.name.toLowerCase();

		// loop through every keyword for testing
		for (var i = 0; i < keywords.length; i++) {
		
			// if keyword exists in the text...
			if (text.indexOf(keywords[i]) != -1) {
				
				playerTextPacket = event.createPacket(ID_PLAYERTEXT);
				playerTextPacket.text = "/ignore "+name;
				event.sendToServer(playerTextPacket);

				event.echo("Ignored "+name+".");
					
				event.cancel();
				break;
			}
		}
	}
}
