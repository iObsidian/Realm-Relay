package rotmg.net.api;

public interface MessageMapping {

	MessageMapping setID(int param1);

	MessageMapping toMessage(Class param1);

	MessageMapping toMethod(MessageConsumer messageConsumer);

	MessageMapping setPopulation(int param1);

}

