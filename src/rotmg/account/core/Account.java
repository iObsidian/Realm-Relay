package rotmg.account.core;

/**
 * 100% match
 */
public interface Account {

	String getPlatformToken();

	void setPlatformToken(String param1);

	void updateUser(String param1, String param2, String param3);

	String getUserName();

	String getUserId();

	String getPassword();

	String getToken();

	String getSecret();

	Object getCredentials();

	boolean isRegistered();

	void clear();

	void reportIntStat(String param1, int param2);

	String getRequestPrefix();

	String gameNetworkUserId();

	String gameNetwork();

	String playPlatform();

	String getEntryTag();

	void verify(boolean param1);

	boolean isVerified();

	String getMoneyUserId();

	String getMoneyAccessToken();

}
