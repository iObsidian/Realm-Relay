package rotmg.packages.models;

import alde.flash.utils.EventConsumer;
import flash.display.Loader;
import flash.events.Event;
import flash.events.IOErrorEvent;
import flash.events.SecurityError;
import flash.net.URLRequest;
import org.osflash.signals.Signal;
import rotmg.shop.genericBox.data.GenericBoxInfo;

import java.util.function.Consumer;

public class PackageInfo extends GenericBoxInfo {

	public static final String PURCHASE_TYPE_MIXED = "PURCHASE_TYPE_MIXED";

	public static final String PURCHASE_TYPE_SLOTS_ONLY = "PURCHASE_TYPE_SLOTS_ONLY";

	public static final String PURCHASE_TYPE_CONTENTS_ONLY = "PURCHASE_TYPE_CONTENTS_ONLY";

	public String image;

	public String popupImage = "";

	public boolean showOnLogin;

	public int charSlot;

	public int vaultSlot;

	public Signal imageLoadedSignal;

	public Signal popupImageLoadedSignal;

	public Loader loader;

	public Loader popupLoader;

	public PackageInfo() {
		super();
		this.imageLoadedSignal = new Signal();
		this.popupImageLoadedSignal = new Signal();
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String param1) {
		this.image = param1;
		this.loader = new Loader();
		this.loadImage(this.image, this.loader, this::onComplete);
	}

	public String getPurchaseType() {
		if (contents != "") {
			if (this.charSlot > 0 || this.vaultSlot > 0) {
				return PURCHASE_TYPE_MIXED;
			}
			return PURCHASE_TYPE_CONTENTS_ONLY;
		}
		return PURCHASE_TYPE_SLOTS_ONLY;
	}

	private void loadImage(String param1, Loader param2, Consumer<Event> param3) {
		param2.contentLoaderInfo.addEventListener(Event.COMPLETE, new EventConsumer<>(param3));
		param2.contentLoaderInfo.addEventListener(IOErrorEvent.IO_ERROR, new EventConsumer<>(this::onIOError));
		param2.contentLoaderInfo.addEventListener(SecurityErrorEvent.SECURITY_ERROR, new EventConsumer<>(this::onSecurityEventError));
		try {
			param2.load(new URLRequest(param1));
			return;
		} catch (SecurityError error) {
			return;
		}
	}

	private void unbindLoaderEvents(Loader param1, Consumer<? extends Event> param2) {
		if (param1 != null && param1.contentLoaderInfo != null) {
			param1.contentLoaderInfo.removeEventListener(Event.COMPLETE, new EventConsumer<>(param2));
			param1.contentLoaderInfo.removeEventListener(IOErrorEvent.IO_ERROR, new EventConsumer<>(this::onIOError));
			param1.contentLoaderInfo.removeEventListener(SecurityErrorEvent.SECURITY_ERROR, new EventConsumer<>(this::onSecurityEventError));
		}
	}

	private void onIOError(IOErrorEvent param1) {
	}

	private void onSecurityEventError(SecurityErrorEvent param1) {
	}

	private void onComplete(Event param1) {
		this.imageLoadedSignal.dispatch();
		this.unbindLoaderEvents(this.loader, this::onComplete);
	}

	private void onCompletePopup(Event param1) {
		this.popupImageLoadedSignal.dispatch();
		this.unbindLoaderEvents(this.popupLoader, this::onCompletePopup);
	}

	public void dispose() {
	}

	public Loader getLoader() {
		return this.loader;
	}

	public String getPopupImage() {
		return this.popupImage;
	}

	public void setPopupImage(String param1) {
		this.popupImage = param1;
		this.popupLoader = new Loader();
		this.loadImage(this.popupImage, this.popupLoader, this::onCompletePopup);
	}

	public Loader getPopupLoader() {
		return this.popupLoader;
	}

	public boolean getShowOnLogin() {
		return this.showOnLogin;
	}

	public void setShowOnLogin(boolean param1) {
		this.showOnLogin = param1;
	}

	public int getCharSlot() {
		return this.charSlot;
	}

	public void setCharSlot(int param1) {
		this.charSlot = param1;
	}

	public int getVaultSlot() {
		return this.vaultSlot;
	}

	public void setVaultSlot(int param1) {
		this.vaultSlot = param1;
	}

}
