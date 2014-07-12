// connect.js
// authored by Nisuxen

var ID_PLAYER_TEXT = $.findPacketId("PLAYERTEXT");
var ID_RECONNECT = $.findPacketId("RECONNECT");
var ID_ESCAPE = $.findPacketId("ESCAPE");

var servers = { "usw" : "54.241.208.233",
        "usw2"  : "54.193.168.4",
        "usw3"  : "54.241.223.240",
        "use" : "54.224.68.81",
        "use2"  : "50.19.7.133",
        "use3"  : "54.226.214.216",
        "uss" : "23.22.180.212",
        "uss2"  : "107.20.55.255",
        "uss3"  : "54.80.250.47",
        "usmw"  : "54.80.67.112",
        "usmw2" : "50.17.143.165",
        "ussw"  : "54.219.44.205",
        "usnw"  : "50.18.24.120",
        "euw" : "54.195.57.43",
        "euw2"  : "54.195.154.140",
        "eue" : "46.137.30.179",
        "eus" : "54.195.179.215",
        "eun" : "54.195.96.152",
        "eun2"  : "54.216.200.98",
        "eusw"  : "54.217.63.70",
        "ae"  : "175.41.201.80",
        "ase" : "54.255.15.39" };

var usage = 
"Usage: /con <server>\n \
\t\tusw     \n \
\t\tusw2  \n \
\t\tusw3  \n \
\t\tuse   \n \
\t\tuse2  \n \
\t\tuse3  \n \
\t\tuss   \n \
\t\tuss2  \n \
\t\tuss3  \n \
\t\tusmw  \n \
\t\tusmw2 \n \
\t\tussw  \n \
\t\tusnw  \n \
\t\teuw   \n \
\t\teuw2  \n \
\t\teue   \n \
\t\teus   \n \
\t\teun   \n \
\t\teun2  \n \
\t\teusw  \n \
\t\tae    \n \
\t\tase   \n";

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
      var server = text.substring(5, text.length());

      if(servers[server] == null){
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
