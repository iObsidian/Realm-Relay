package rotmg.net.impl;

<<<<<<< HEAD:src/rotmg/net/impl/MessageCenter.java
import alde.flash.utils.MessageConsumer;
<<<<<<< HEAD:src/kabam/rotmg/net/impl/MessageCenter.java
import kabam.rotmg.net.api.MessageMap;
import kabam.rotmg.net.api.MessageMapping;
import kabam.rotmg.net.api.MessageProvider;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
=======
import rotmg.net.api.MessageMap;
import rotmg.net.api.MessageMapping;
import rotmg.net.api.MessageProvider;
>>>>>>> parent of 5927bf7... Migrated to kabam.rotmg:src/rotmg/net/impl/MessageCenter.java
=======
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.function.Consumer;

import alde.flash.utils.MessageConsumer;
import kabam.rotmg.net.api.MessageMapping;
import rotmg.net.api.MessageMap;
import rotmg.net.api.MessageMapping;
import rotmg.net.api.MessageProvider;
>>>>>>> parent of 5791e6e... Commit before reverting refactoring:src/kabam/rotmg/net/impl/MessageCenter.java

public class MessageCenter implements MessageMap, MessageProvider {

	private static final int MAXID = 256;
	private static MessageCenter instance;
	private final HashMap<Integer, MessageCenterMapping> maps = new HashMap<Integer, MessageCenterMapping>(MAXID);

	public MessageCenter() {
		super();
	}

	public static MessageCenter getInstance() {
		if (instance == null) {
			instance = new MessageCenter();
		}
		return instance;
	}

	public MessageMapping map(int param1) {
		if (this.maps.get(param1) == null) {
			this.maps.put(param1, this.makeMapping(param1));
		}
		return this.maps.get(param1);
	}

	public void unmap(int param1) {
		this.maps.put(param1, null);
	}

	private MessageCenterMapping makeMapping(int param1) {
		return (MessageCenterMapping) new MessageCenterMapping().setID(param1);
	}

	@Override
	public Message require(int param1) {

		MessageCenterMapping m = maps.get(param1);

		if (m != null) {
			Class classToLoad = m.messageType;

			Class[] cArg = new Class[2]; //Our constructor has 3 arguments
			cArg[0] = Integer.TYPE; //First argument is of *object* type Long
			cArg[1] = MessageConsumer.class; //Second argument is of *object* type String

			try {
				return (Message) classToLoad.getDeclaredConstructor(cArg).newInstance(param1, m);
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}

		}

		System.err.println("Null mapping");

		return null;

	}
}