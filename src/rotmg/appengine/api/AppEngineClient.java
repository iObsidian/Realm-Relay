package rotmg.appengine.api;

import org.osflash.OnceSignal;

public interface AppEngineClient {

	OnceSignal getComplete();

	void setDataFormat(String param1);

	void setSendEncrypted(Boolean param1);

	void setMaxRetries(int param1);

	void sendRequest(String param1, Object param2);

	Boolean requestInProgress();

}
