package rotmg.account.core;

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

	void verify(Boolean aram1);

	boolean isVerified();

	String getMoneyUserId();

	String getMoneyAccessToken();

	static Account getInstance() {
		return new Account() {
			@Override
			public String getPlatformToken() {
				return null;
			}

			@Override
			public void setPlatformToken(String param1) {

			}

			@Override
			public void updateUser(String param1, String param2, String param3) {

			}

			@Override
			public String getUserName() {
				return null;
			}

			@Override
			public String getUserId() {
				return null;
			}

			@Override
			public String getPassword() {
				return null;
			}

			@Override
			public String getToken() {
				return null;
			}

			@Override
			public String getSecret() {
				return null;
			}

			@Override
			public Object getCredentials() {
				return null;
			}

			@Override
			public boolean isRegistered() {
				return false;
			}

			@Override
			public void clear() {

			}

			@Override
			public void reportIntStat(String param1, int param2) {

			}

			@Override
			public String getRequestPrefix() {
				return null;
			}

			@Override
			public String gameNetworkUserId() {
				return null;
			}

			@Override
			public String gameNetwork() {
				return null;
			}

			@Override
			public String playPlatform() {
				return null;
			}

			@Override
			public String getEntryTag() {
				return null;
			}

			@Override
			public void verify(Boolean aram1) {

			}

			@Override
			public boolean isVerified() {
				return false;
			}

			@Override
			public String getMoneyUserId() {
				return null;
			}

			@Override
			public String getMoneyAccessToken() {
				return null;
			}
		};
	}
}
