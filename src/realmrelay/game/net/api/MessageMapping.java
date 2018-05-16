package realmrelay.game.net.api;

public interface MessageMapping {

	MessageMapping setID(int param1);

	MessageMapping toMessage(Class param1);

	MessageMapping toHandler(Class param1);

	MessageMapping toMethod(Function param1);

	MessageMapping setPopulation(int param1);

	MessagePool makePool();
}

