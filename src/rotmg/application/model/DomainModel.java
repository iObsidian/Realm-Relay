package rotmg.application.model;

import alde.flash.utils.Vector;
import flash.net.LocalConnection;
import flash.system.Security;

public class DomainModel {

	private final String LOCALHOST = "localhost";

	private final Vector<String> PRODUCTION_WHITELIST = new Vector<String>("www.realmofthemadgod.com", "realmofthemadgodhrd.appspot.com", "realmofthemadgod.appspot.com");

	private final Vector<String> TESTING_WHITELIST = new Vector<String>("testing.realmofthemadgod.com", "rotmgtesting.appspot.com", "rotmghrdtesting.appspot.com");

	private final Vector<String> TESTING2_WHITELIST = new Vector<String>("realmtesting2.appspot.com");

	private final Vector<String> TRANSLATION_WHITELIST = new Vector<String>("xlate.kabam.com");

	private final Vector<String> WHITELIST = this.PRODUCTION_WHITELIST.concat(this.TESTING_WHITELIST).concat(this.TRANSLATION_WHITELIST).concat(this.TESTING2_WHITELIST);

	public PlatformModel client;

	private String localDomain;

	public DomainModel() {
		super();
	}

	public void applyDomainSecurity() {
		for (String loc1 : this.WHITELIST) {
			Security.allowDomain(loc1);
		}
	}

	public boolean isLocalDomainValid() {
		return this.client.isDesktop() || this.isLocalDomainInWhiteList();
	}

	public boolean isLocalDomainProduction() {
		String loc1 = this.getLocalDomain();
		return this.PRODUCTION_WHITELIST.contains(loc1);
	}

	private boolean isLocalDomainInWhiteList() {
		String loc1 = this.getLocalDomain();
		boolean loc2 = loc1.equals(this.LOCALHOST);
		for (String loc3 : this.WHITELIST) {
			loc2 = loc2 || loc1.equals(loc3);
		}
		return loc2;
	}

	private String getLocalDomain() {

		if (this.localDomain == null) {
			this.localDomain = new LocalConnection().domain;
		}

		return this.localDomain;
	}
}
