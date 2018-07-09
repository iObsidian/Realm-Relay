package rotmg.account.core;

/**
 * From interface with set and get
 * to abstract class
 */
public abstract class Account {

	public String getPlatformToken;

	abstract void setPlatformToken(String param1);

	abstract void updateUser(String param1, String param2, String param3);

	String userName;

	public String userId;

	public String password;

	public String token;

	public String secret;

	public Object credentials;

	boolean isRegistered;

	abstract void clear();

	public abstract void reportIntStat(String param1, int param2);

	String getRequestPrefix;

	public String gameNetworkUserId;

	public String gameNetwork;

	public String playPlatform;

	public String getEntryTag;

	public abstract void verify(Boolean aram1);

	boolean isVerified;

	String getMoneyUserId;

	String getMoneyAccessToken;

	public static Account getInstance() {
		return new WebAccount("fliphcc@gmail.com", "ati3SmaQ3de");
	}
}
