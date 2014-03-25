// tp.js
// authored by Nisuxen

var ID_PLAYER_TEXT = $.findPacketId("PLAYERTEXT");
var ID_UPDATE = $.findPacketId("UPDATE");
var ID_TELEPORT = $.findPacketId("TELEPORT");

var players = {};

function onClientPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_PLAYER_TEXT: {
			var text = packet.text.toLowerCase();
			if(text.length() >= 3 && text.substring(0,3) == "/tp"){
				event.cancel();

				if(text.length() <= 4){
					break;
				}
				var toTp = text.substring(4, text.length());

				for(var player in players){
					var name = players[player].toLowerCase();
					if(name.length() < toTp.length())
						continue;
					if (toTp == name.substring(0,toTp.length())){
						//send tp packet
						var tp_packet = event.createPacket(ID_TELEPORT);
						tp_packet.objectId = player;
						event.sendToServer(tp_packet);
						break;
					}
				}
			}
			break;
		}
	}
}

function onServerPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_UPDATE: {
			// New objects
			for (var i = 0; i < packet.newObjs.length; i++) {
				var objectData = packet.newObjs[i];

				var type = objectData.objectType;
				if(type == 768 || type == 775 || type == 782 || type == 784 || (type >= 797 && type <= 806)){ // player classes
					for (var j = 0; j < objectData.status.data.length; j++) {
						var statData = objectData.status.data[j];

						if(statData != null && statData.obf0 == 31){
							players[objectData.status.objectId] = statData.obf2;
							break;
						}
					}
				}
			}

			// Removed objects
			for (var i = 0; i < packet.drops.length; i++) {
				var droppedObjectId = packet.drops[i];

				if(players[droppedObjectId] != null){
					delete players[droppedObjectId];			
				}
			}
			break;
		}
	}
}