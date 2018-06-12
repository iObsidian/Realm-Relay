package rotmg.application.model;


import flash.display.DisplayObjectContainer;
import flash.system.Capabilities;

public class PlatformModel {

	private static PlatformType platform;
	private final String DESKTOP = "Desktop";
	public DisplayObjectContainer root;

	public PlatformModel() {
		super();
	}

	public boolean isWeb() {
		return !Capabilities.playerType.equals(this.DESKTOP);
	}

	public boolean isDesktop() {
		return Capabilities.playerType.equals(this.DESKTOP);
	}

	public PlatformType getPlatform() {
		if (platform == null) {
			platform = this.determinePlatform();
		}
		return platform;
	}

	private PlatformType determinePlatform() {
		/**Object loc1 = LoaderInfo(this.root.stage.root.loaderInfo).parameters;
		 if (this.isKongregate(loc1)) {
		 return PlatformType.KONGREGATE;
		 }
		 if (this.isSteam(loc1)) {
		 return PlatformType.STEAM;
		 }
		 if (this.isKabam(loc1)) {
		 return PlatformType.KABAM;
		 }*/
		return PlatformType.WEB;
	}

	/*private boolean isKongregate(Object param1) {
		return param1.kongregate_api_path != null;
	}

	private boolean isSteam(Object param1) {
		return param1.steam_api_path != null;
	}

	private boolean isKabam(Object param1) {
		return param1.kabam_signed_request != null;
	}*/


}
