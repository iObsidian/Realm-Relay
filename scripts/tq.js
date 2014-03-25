// tq.js
// authored by Nisuxen

var ID_CREATE_SUCCESS = $.findPacketId("CREATE_SUCCESS");
var ID_PLAYER_TEXT = $.findPacketId("PLAYERTEXT");
var ID_UPDATE = $.findPacketId("UPDATE");
var ID_TELEPORT = $.findPacketId("TELEPORT");
var ID_NEW_TICK = $.findPacketId("NEW_TICK");
var ID_QUEST_OBJECTID = $.findPacketId("QUESTOBJID");

var questObjectId = -1;
var questLoc = null;
var playerLocs = {};
var myId = -1;

function onClientPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_PLAYER_TEXT: {
			var text = packet.text.toLowerCase();
			if(text == "/tq"){
				event.cancel();

				if(questObjectId == -1 || questLoc == null){
					break;
				}
				var minDist = questLoc.distanceSquaredTo(playerLocs[myId]);
				var toTp = myId;
				for(var player in playerLocs){
					var distToQuest = questLoc.distanceSquaredTo(playerLocs[player]);
					
					if(distToQuest < minDist){
						minDist = distToQuest;
						toTp = player;
					}
				}
				if (toTp != myId){
					//send tp packet
					var tp_packet = event.createPacket(ID_TELEPORT);
					tp_packet.objectId = toTp;
					event.sendToServer(tp_packet);
					break;
				}
			}
			break;
		}
	}
}

function onServerPacket(event) {
	var packet = event.getPacket();
	switch (packet.id()) {
		case ID_CREATE_SUCCESS: {
			myId = packet.objectId;
			break;
		}
		case ID_UPDATE: {
			// New objects
			for (var i = 0; i < packet.newObjs.length; i++) {
				var objectData = packet.newObjs[i];

				var type = objectData.objectType;
				if(type == 768 || type == 775 || type == 782 || type == 784 || (type >= 797 && type <= 806)){ // player classes
					playerLocs[objectData.status.objectId] = objectData.status.pos;
				}else{
					if(objectData.status.objectId == questObjectId){
						questLoc = objectData.status.pos;
					}
				}
			}

			// Removed objects
			for (var i = 0; i < packet.drops.length; i++) {
				var droppedObjectId = packet.drops[i];

				if(playerLocs[droppedObjectId] != null){
					delete playerLocs[droppedObjectId];		
				}
				else if(droppedObjectId == questObjectId){
					questObjectId = -1;
					questLoc = null;
				}
			}
			break;
		}
		case ID_NEW_TICK: {
			for (var i = 0; i < packet.statuses.length; i++) {
				var status = packet.statuses[i];

				for (player in playerLocs){
					if(status.objectId == player){
						playerLocs[player] = status.pos;
						break;
					}
				}
			}
			break;
		}
		case ID_QUEST_OBJECTID: {
			questObjectId = packet.objectId;
			break;
		}
	}
}