package rotmg.account.core;

/**
 * 10% match (very loose implementation for debugging)
 */
public class WebAccount implements Account {

	public static final String NETWORKNAME = "rotmg";
	public static final String WEBUSERID = "";
	public static final String WEBPLAYPLATFORMNAME = "rotmg";

	public String signedRequest = "";
	public String kabamId = "";

	public boolean isVerifiedEmail = true;
	public String platformToken = "";
	public String userDisplayName = "";

	public String entryTag = "";
	public boolean rememberMe = true;
	public String paymentProvider = "";
	public String paymentData = "";

	String userName = "";

	public String userId = "";

	public String password = "";

	public String token = "";

	public String secret = "";

	public Object credentials;

	boolean isRegistered;

	String requestPrefix = "";

	public String gameNetworkUserId = "";

	public String gameNetwork = "";

	public String playPlatform = "";

	boolean isVerified = true;

	String moneyUserId = "";

	String moneyAccessToken = "";

	public static Account getInstance() {
		WebAccount account = new WebAccount("fliphcc@gmail.com", "ati3SmaQ3de");
		account.userId = "Null";
		return account;
	}

	public WebAccount() {
		super();
		/*try {
		 this.entryTag = ExternalInterface.call("rotmg.UrlLib.getParam", "entrypt");
		 return;
		 }
		 catch (error:Error) {
		 return;
		 }*/
	}

	public WebAccount(String email, String password) {
		this.userId = email;
		this.password = password;
	}

	public String getUserName() {
		return this.userId;
	}

	public String getUserId() {
		if (this.userId == null) {
			//this.userId = GUID.create();

		}
		return this.userId;
	}

	public String getPassword() {

		if (this.password != null) {
			return this.password;
		} else {
			return "";
		}

	}

	public String getToken() {
		return "";
	}

	public boolean isRegistered() {
		return true;
	}

	public void updateUser(String param1, String param2, String param3) {
		/**   SharedObject loc4 = null;
		 this.userId = param1;
		 this.password = param2;
		 this.token = param3;
		 try {
		 if (this.rememberMe) {
		 loc4 = SharedObject.getLocal("RotMG", "/");
		 loc4.data["GUID"] = param1;
		 loc4.data["Token"] = param3;
		 loc4.data["Password"] = param2;
		 loc4.flush();
		 }
		 return;
		 }
		 catch (error:Error) {
		 return;
		 }*/
	}

	public void clear() {
		this.rememberMe = true;
		//this.updateUser(GUID.create(), null, null);
		/*Parameters.sendLogin = true;
		Parameters.data.charIdUseMap={};
		
		Parameters.save();**/
	}

	public void reportIntStat(String param1, int param2) {
	}

	public String getRequestPrefix() {
		return "/credits";
	}

	public String gameNetworkUserId() {
		return WEBUSERID;
	}

	public String gameNetwork() {
		return NETWORKNAME;
	}

	public String playPlatform() {
		return WEBPLAYPLATFORMNAME;
	}

	public String getEntryTag() {
		if (this.entryTag != null) {
			return this.entryTag;
		} else {
			return "";
		}
	}

	public String getSecret() {
		return "";
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	public void verify(boolean param1) {
		this.isVerifiedEmail = param1;
	}

	public boolean isVerified() {
		return this.isVerifiedEmail;
	}

	public String getPlatformToken() {

		if (this.platformToken != null) {
			return this.platformToken;
		} else {
			return "";
		}

	}

	public void setPlatformToken(String param1) {
		this.platformToken = param1;
	}

	public String getMoneyAccessToken() {
		return this.signedRequest;
	}

	public String getMoneyUserId() {
		return this.kabamId;
	}

	public String getUserDisplayName() {
		return this.userDisplayName;
	}

	public void setUserDisplayName(String param1) {
		this.userDisplayName = param1;
	}

	public boolean getRememberMe() {
		return this.rememberMe;
	}

	public void setRememberMe(boolean param1) {
		this.rememberMe = param1;
	}

	public String getPaymentProvider() {
		return this.paymentProvider;
	}

	public void setPaymentProvider(String param1) {
		this.paymentProvider = param1;
	}

	public String getPaymentData() {
		return this.paymentData;
	}

	public void setPaymentData(String param1) {
		this.paymentData = param1;
	}


}
