package realmrelay.game.net.api;

import java.util.function.Consumer;

import realmrelay.game.net.impl.MessagePool;

/**
 * Complete, not sure about 'Class' for messages
 */
public interface MessageMapping {

	MessageMapping setID(int param1);

	MessageMapping toMessage(Class param1);

	MessageMapping toHandler(Class param1);

	MessageMapping toMethod(Consumer param1);

	MessageMapping setPopulation(int param1);

	MessagePool makePool();

}
