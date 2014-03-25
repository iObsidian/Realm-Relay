// spamfilter.js

var ID_TEXT = $.findPacketId("TEXT");

var keywords = [
	".info",
	".net",
	".org",
	".us",
	"% off",
	"24/7",
	"cheap",
	"client",
	"customer",
	"del|very",
	"delivery",
	"dellvery",
	"pric", // pricing/prices/price
	"prize",
	"satis", // satisfaction/satisfactory/satisfied
	"service",
	"stock",
	"www"
];

function onServerPacket(event) {
	// get the packet involved in this event
	var packet = event.getPacket();
	
	// if this event's packet is a TEXT packet...
	if (packet.id() == ID_TEXT) {
	
		// make text lowercase to match the keyword list
		var text = packet.text.toLowerCase();
		
		// loop through every keyword for testing
		for (var i = 0; i < keywords.length; i++) {
		
			// if keyword exists in the text...
			if (text.indexOf(keywords[i]) != -1) {
				
				// cancel the event to stop the packet from being sent to the client
				event.cancel();
				
				// break the loop because we already know the packet is spam
				break;
			}
		}
	}
}