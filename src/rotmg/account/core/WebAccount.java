package rotmg.account.core;

public class WebAccount implements Account {

	private static WebAccount instance;

	public static Account getInstance() {
		/**if (instance == null) {
			instance = new WebAccount();
		}
		return instance;*/

		return new WebAccount("rotmgiobsidian@gmail.com", "JtiTdzTP");
	}

	public static final String NETWORKNAME = "rotmg";

	private static final String WEBUSERID = "";

	private static final String WEBPLAYPLATFORMNAME = "rotmg";

	private String userId = "";

	private String password;

	private String token = "";

	private String entryTag = "";

	private boolean isVerifiedEmail;

	private String platformToken;

	private String userDisplayName = "";

	private boolean rememberMe = true;

	private String paymentProvider = "";

	private String paymentData = "";

	public String signedRequest;

	public String kabamId;

	public WebAccount() {
		super();
		/**try {
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
		return this.getPassword() != "" || this.getToken() != "";
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

	public void setRememberMe(boolean param1) {
		this.rememberMe = param1;
	}

	public boolean getRememberMe() {
		return this.rememberMe;
	}

	public void setPaymentProvider(String param1) {
		this.paymentProvider = param1;
	}

	public String getPaymentProvider() {
		return this.paymentProvider;
	}

	public void setPaymentData(String param1) {
		this.paymentData = param1;
	}

	public String getPaymentData() {
		return this.paymentData;
	}

	@Override
	public Object getCredentials() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void verify(Boolean aram1) {
		// TODO Auto-generated method stub

	}

}
