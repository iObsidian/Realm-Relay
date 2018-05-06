var ID_PLAYER_TEXT = $.findPacketId("PLAYERTEXT");
var ID_RECONNECT = $.findPacketId("RECONNECT");
var ID_ESCAPE = $.findPacketId("ESCAPE");

var servers = { 
 	"USWest" : "54.153.32.11"
 	"USEast" : "52.23.232.42"
 	"AsiaSouthEast" : "52.77.221.237"
	"USSouth" : "52.91.68.60"
 	"USSouthWest" : "54.183.179.205"
 	"USEast2" : "52.91.203.118"
 	"USNorthWest" : "54.234.151.78"
	"AsiaEast" : "54.199.197.208"
 	"EUSouthWest" : "52.47.178.13"
 	"USSouth2" : "54.183.236.213"
 	"EUNorth2" : "52.59.198.155"
	"EUSouth" : "52.47.150.186"
	"USSouth3" : "13.57.182.96"
	"EUWest2" : "34.243.37.98"
	"USMidWest" : "18.220.226.127"
	"EUWest" : "52.47.149.74"
	"USEast3" : "54.157.6.58"
	"USWest3" : "54.67.119.179"
	"USMidWest2" : "18.218.255.91"
	"EUEast" : "18.195.167.79"
	"Australia" : "54.252.165.65"
 	"EUNorth" : "54.93.78.148"
 	"USWest2" : "54.215.251.128"
};

var usage = 
"Usage: /con <server>";

var portals = { "{epicspiderDen.The_Crawling_Depths}" : 1,
        "{forestMaze.Forest_Maze_Portal}" : 1,
        "{cave.Treasure_Cave_Portal}" : 1,
        "{shatters.The_Shatters}" : 1,
        "{oryxCastle.OryxAPOSs_Chamber_Portal}" : 1,
        "{epicpirateCave.Deadwater_Docks}" : 1,
        "{epicpirateCave.BilgewaterAPOSs_Grotto_Portal}" : 1,
        "{madLab.Mad_Lab_Portal}" : 1,
        "{objects.Pirate_Cave_Portal}" : 1,
        "{objects.Snake_Pit_Portal}" : 1,
        "{objects.Spider_Den_Portal}" : 1,
        "{objects.Undead_Lair_Portal}" : 1,
        "{objects.Battle_Nexus_Portal}" : 1,
        "{objects.Tomb_of_the_Ancients_Portal}" : 1,
        "{objects.Dreamscape_Labyrinth_Portal}" : 1,
        "{objects.Ocean_Trench_Portal}" : 1,
        "{objects.Abyss_of_Demons_Portal}" : 1,
        "{objects.Forbidden_Jungle_Portal}" : 1,
        "{objects.Manor_of_the_Immortals_Portal}" : 1,
        "{objects.Davy_JonesAPOSs_Locker_Portal}" : 1,
        "{objects.Beachzone_Portal}" : 1,
        "{objects.Candyland_Portal}" : 1,
        "{objects.Haunted_Cemetery_Portal}" : 1,
        "{objects.Glowing_Portal}" : 1,
        "{epicforestMaze.Woodland_Labyrinth}" : 1,
        "{hauntedCemetery.Area1_Portal}" : 1,
        "{hauntedCemetery.Haunted_Cemetery_Gates_Portal}" : 1,
        "{hauntedCemetery.Haunted_Cemetery_Graves_Portal}" : 1,
        "{hauntedCemetery.Haunted_Cemetery_Final_Rest_Portal}" : 1,
        "Oryx\'s Castle" : 1,
        "{oryx.Wine_Cellar}" : 1
      };

function onClientPacket(event) {
  var packet = event.getPacket();
  if(packet.id() == ID_PLAYER_TEXT){
    var text = packet.text.toLowerCase();
    if(text.length() >= 4 && text.substring(0,4) == "/con"){
      event.cancel();

      if(text.length() <= 5){
        event.echo(usage)
        return;
      }
      var server = text.substring(5, text.length()).toLowerCase();

      if (servers[server] == null){
        event.echo("Server " + server + " not found.");
      }else{
        event.setGameIdSocketAddress(-2, servers[server], event.getRemotePort());

        var recon = event.createPacket(ID_RECONNECT);
        recon.name = "Nexus";
        recon.host = "";
        recon.port = 2050;
        recon.gameId = -2;
        event.sendToClient(recon);
      }
      return;
    }
    else if(text == "/rrec") // Connects to realm
      var reconPacket = event.getGlobal("rrec");
    else if(text == "/vrec") // Connects to vault
      var reconPacket = event.getGlobal("vrec");
    else if(text == "/grec") // Connects to ghall
      var reconPacket = event.getGlobal("grec");
    else if(text == "/drec") // Connects to dungeon
      var reconPacket = event.getGlobal("drec");
    else
      return;

    event.cancel();
    if(reconPacket != null)
      event.sendToClient(reconPacket);
    else
      event.echo("Nothing to reconnect to.");
  }
    // Overrides escape so that player stays on same server
  else if(packet.id() == ID_ESCAPE){
    event.cancel();
    var recon = event.createPacket(ID_RECONNECT);
    recon.name = "Nexus";
    recon.host = "";
    recon.port = 2050;
    recon.gameId = -2;
    event.sendToClient(recon);
  }
}

function onServerPacket(event) {
  var packet = event.getPacket();
  if(packet.id() == ID_RECONNECT){
    //event.echo("Reconnect Name: " + packet.name);
    if(packet.name.length() >= 11 && packet.name.substring(0,11) == "NexusPortal")
      event.setGlobal("rrec", packet);
    else if(packet.name == "{\"text\":\"server.vault\"}")
      event.setGlobal("vrec", packet);
    else if(packet.name == "Guild Hall")
      event.setGlobal("grec", packet);
    else if(portals[packet.name] == 1)
      event.setGlobal("drec", packet);
  }
}
