package rotmg.net.api;

import alde.flash.utils.MessageConsumer;

public interface MessageMapping {

	MessageMapping setID(int param1); //(set ID (int))

	MessageMapping toMessage(Class param1); //ID (int) to Message packet (Class)

	MessageMapping toHandler(Class param1); //Handler class (execute())

	MessageMapping toMethod(MessageConsumer messageConsumer);

	MessageMapping setPopulation(int param1);

}

