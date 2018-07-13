package rotmg.appengine;

import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Vector;
import flash.display.BitmapData;
import flash.utils.Date;
import ion.utils.png.PNGDecoder;
import mx.logging.ILogger;
import rotmg.util.BitmapDataSpy;

/**
 * 30% match
 */
public class RemoteTexture {

	private static final String URL_PATTERN = "http://{DOMAIN}/picture/get";

	private static final String ERROR_PATTERN = "Remote Texture Error: {ERROR} (id:{ID}, instance:{INSTANCE})";

	private static final int START_TIME = new Date().getTime();

	public String id;

	public String instance;

	public SignalConsumer callback;

	private ILogger logger;

	public RemoteTexture(String param1, String param2, SignalConsumer param3) {
		super();
		this.id = param1;
		this.instance = param2;
		this.callback = param3;
		this.logger = ILogger.getInstance();
	}

	public void run() {
		/*String loc1 = this.instance.equals("testing") ? "rotmghrdtesting.appspot.com" : "realmofthemadgodhrd.appspot.com";
		String loc2 = URL_PATTERN.replace("{DOMAIN}", loc1);
		Vector loc3 = new Object();
		loc3.id = this.id;
		loc3.time = START_TIME;
		RetryLoader loc4 = new AppEngineRetryLoader();
		loc4.setDataFormat(URLLoaderDataFormat.BINARY);
		loc4.complete.addOnce(new SignalConsumer<>(this::onComplete));
		loc4.sendRequest(loc2, loc3);*/
	}

	private void onComplete(byte param2) {
		this.makeTexture(param2);
	}

	/*private void onComplete(boolean param1, Object param2) {
		if (param1) {
			this.makeTexture((byte) param2);
		} else {
			this.reportError((String) param2);
		}
	}*/

	public void makeTexture(byte param1) {
		BitmapData loc2 = PNGDecoder.decodeImage(param1);
		this.callback.dispatch(loc2);
	}

	public void reportError(String param1) {
		param1 = ERROR_PATTERN.replace("{ERROR}", param1).replace("{ID}", this.id).replace("{INSTANCE}", this.instance);
		this.logger.warn("RemoteTexture.reportError: {0}", new Vector<String>(param1));
		BitmapData loc2 = new BitmapDataSpy(1, 1);
		this.callback.dispatch(loc2);
	}

}
