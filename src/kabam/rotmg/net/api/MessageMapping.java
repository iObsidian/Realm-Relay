package kabam.rotmg.net.api;

import alde.flash.utils.MessageConsumer;
import rotmg.net.impl.Message;

import java.util.function.Function;

public interface MessageMapping {

	MessageMapping setID(int param1);

	MessageMapping toMessage(Class param1);

	MessageMapping toMethod(MessageConsumer messageConsumer);

	MessageMapping setPopulation(int param1);

}

