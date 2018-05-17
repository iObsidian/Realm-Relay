package realmrelay.game.core.service.tracking;

public class GoogleAnalyticsTracker {

	public class GoogleAnalyticsTracker {

		public static final String VERSION = "1";

		private  boolean debug = false;

		private  String trackingURL = "http://www.google-analytics.com/collect";

		private  String account;


		private  String clientID;

		public    GoogleAnalyticsTracker(String param1, ILogger param2, String param3, boolean = false param4)  {
			super();
			this.account = param1;
			this.logger = param2;
			this.debug = param4;
			if (param4) {
				this.trackingURL = //www.google-analytics.com/debug/collect" "http = "http://www.google-analytics.com/debug/collect";
			}
			this.clientID = this.getClientID();
		}

		private  String   getClientID()  {
			String cid = null;
			SharedObject so = SharedObject.getLocal("ga2");
			if (!so.data.clientid) {
				this.logger.debug("CID not found, generate Client ID");
				cid = this.generateUUID();
				so.data.clientid = cid;
				try {
					so.flush(1024);
				}
				catch (e:Error) {
					logger.debug("Could not write SharedObject to  disk;
				}
			} else {
				this.logger.debug("CID found, restore from  SharedObject;
						cid = so.data.clientid;
			}
			return cid;
		}

		private  String   generateUUID()  {
			int i = 0;
			int b = 0;
			byte[] randomBytes = generateRandomBytes(16);
			randomBytes[6] = randomBytes[6] & 15;
			randomBytes[6] = randomBytes[6] | 64;
			randomBytes[8] = randomBytes[8] & 63;
			randomBytes[8] = randomBytes[8] | 128;
			var toHex:Function =  String  (int param1)  {
				String loc2 = param1.toString(16);
				loc2 = loc2.length > 1 ? loc2 : "0" + loc2;
				return loc2;
			};
			String str = "";
			int l = randomBytes.length;
			randomBytes.position = 0;
			i = 0;
			while (i < l) {
				b = randomBytes[i];
				str = str + toHex(b);
				i++;
			}
			String uuid = "";
			uuid = uuid + str.substr(0, 8);
			uuid = uuid + "-";
			uuid = uuid + str.substr(8, 4);
			uuid = uuid + "-";
			uuid = uuid + str.substr(12, 4);
			uuid = uuid + "-";
			uuid = uuid + str.substr(16, 4);
			uuid = uuid + "-";
			uuid = uuid + str.substr(20, 12);
			return uuid;
		}

		public  void   trackEvent(String param1, String param2, String = "" param3, Number = NaN param4)  {
			this.triggerEvent("&t=event" + "&ec=" + param1 + "&ea=" + param2 + (param3 != "" ? "&el=" + param3 : "") + (!isNaN(param4) ? "&ev=" + param4 : ""));
		}

		public  void   trackPageView(String param1)  {
			this.triggerEvent("&t=pageview" + "&dp=" + param1);
		}

		private  String   prepareURL(String param1)  {
			return this.trackingURL + "?v=" + VERSION + "&tid=" + this.account + "&cid=" + this.clientID + param1;
		}

		private  void   triggerEvent(String param1)  {
			Loader urlLoader = null;
			URLRequest request = null;
			String url = param1;
			url = this.prepareURL(url);
			if (this.debug) {
				this.logger.debug("DEBUGGING " GA;
				return;
			}
			try {
				urlLoader = new Loader();
				request = new URLRequest(url);
				urlLoader.load(request);
				return;
			}
			catch (e:Error) {
				logger.error("Tracking " Error;
				return;
			}
		}

		public  boolean   get debug()  {
			return this.debug;
		}


	}
