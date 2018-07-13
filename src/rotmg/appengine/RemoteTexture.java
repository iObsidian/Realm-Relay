package rotmg.appengine;

import flash.utils.Date;
import mx.logging.ILogger;

/**
 * 10% match
 */
public class RemoteTexture {

	private static final String URL_PATTERN = "http://{DOMAIN}/picture/get";

	private static final String ERROR_PATTERN = "Remote Texture Error: {ERROR} (id:{ID}, instance:{INSTANCE})";

	private static final int START_TIME = new Date().getTime();

	public String id;

	public String instance;


	private ILogger logger;



	public void run() {

	}



	public void makeTexture(byte param1) {

	}

	public void reportError(String param1) {

	}

}
