package realmrelay.game.account.core;

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

	Boolean isRegistered();

	void clear();

	void reportIntStat(String param1, int param2);

	String getRequestPrefix();

	String gameNetworkUserId();

	String gameNetwork();

	String playPlatform();

	String getEntryTag();

	void verify(Boolean aram1);

	Boolean isVerified();

	String getMoneyUserId();

	String getMoneyAccessToken();

}
