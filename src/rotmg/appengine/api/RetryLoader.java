package rotmg.appengine.api;

import alde.flash.utils.Vector;
import org.osflash.OnceSignal;

public interface RetryLoader {

	OnceSignal complete = new OnceSignal();

	void setMaxRetries(int param1);

	void setDataFormat(String param1);

	void sendRequest(String param1, Vector param2);

	boolean isInProgress();

}
