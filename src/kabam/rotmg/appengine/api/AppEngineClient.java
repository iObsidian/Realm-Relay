package kabam.rotmg.appengine.api;

import org.osflash.OnceSignal;

public abstract class AppEngineClient {

	public OnceSignal complete;

	abstract void setDataFormat(String param1);

	abstract void setSendEncrypted(Boolean param1);

	abstract void setMaxRetries(int param1);

	public abstract void sendRequest(String param1, Object param2);

	Boolean requestInProgress;

}
