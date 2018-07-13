package rotmg.appengine.impl;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Vector;
import flash.events.Event;
import flash.events.IOErrorEvent;
import flash.net.*;
import org.osflash.OnceSignal;
import rotmg.appengine.api.RetryLoader;
import rotmg.packages.models.SecurityErrorEvent;

public class AppEngineRetryLoader implements RetryLoader {

	private final OnceSignal _complete = new OnceSignal<Boolean>();

	private int maxRetries;

	private String dataFormat;

	private String url;

	private Vector<String> params;

	private URLRequest urlRequest;

	private URLLoader urlLoader;

	private int retriesLeft;

	private boolean inProgress;

	public AppEngineRetryLoader() {
		super();
		this.inProgress = false;
		this.maxRetries = 0;
		this.dataFormat = URLLoaderDataFormat.TEXT;
	}

	public OnceSignal complete() {
		return this._complete;
	}

	public boolean isInProgress() {
		return this.inProgress;
	}

	public void setDataFormat(String param1) {
		this.dataFormat = param1;
	}

	public void setMaxRetries(int param1) {
		this.maxRetries = param1;
	}

	public void sendRequest(String param1, Vector param2) {
		this.url = param1;
		this.params = param2;
		this.retriesLeft = this.maxRetries;
		this.inProgress = true;
		this.internalSendRequest();
	}

	private void internalSendRequest() {
		this.cancelPendingRequest();
		this.urlRequest = this.makeUrlRequest();
		this.urlLoader = this.makeUrlLoader();
		this.urlLoader.load(this.urlRequest);
	}

	private URLRequest makeUrlRequest() {
		URLRequest loc1 = new URLRequest(this.url);
		loc1.method = URLRequestMethod.POST;
		loc1.data = this.makeUrlVariables();
		return loc1;
	}

	// not a 100% match
	private URLVariables makeUrlVariables() {
		URLVariables loc1 = new URLVariables();
		loc1.set(this.params);
		return loc1;
	}

	private URLLoader makeUrlLoader() {
		URLLoader loc1 = new URLLoader();
		loc1.dataFormat = this.dataFormat;
		loc1.addEventListener(IOErrorEvent.IO_ERROR, new EventConsumer<>(this::onIOError));
		loc1.addEventListener(SecurityErrorEvent.SECURITY_ERROR, new EventConsumer<>(this::onSecurityError));
		loc1.addEventListener(Event.COMPLETE, new EventConsumer<>(this::onComplete));
		return loc1;
	}

	private void onIOError(IOErrorEvent param1) {
		this.inProgress = false;
		String loc2 = this.urlLoader.data;
		if (loc2.length() == 0) {
			loc2 = "Unable to contact server";
		}
		this.retryOrReportError(loc2);
	}

	private void onSecurityError(SecurityErrorEvent param1) {
		this.inProgress = false;
		this.cleanUpAndComplete(false, "Security Error");
	}

	private void retryOrReportError(String param1) {
		if (this.retriesLeft-- > 0) {
			this.internalSendRequest();
		} else {
			this.cleanUpAndComplete(false, param1);
		}
	}

	private void onComplete(Event param1) {
		this.inProgress = false;
		if (this.dataFormat == URLLoaderDataFormat.TEXT) {
			this.handleTextResponse(this.urlLoader.data);
		} else {
			this.cleanUpAndComplete(true, this.urlLoader.data);
		}
	}

	private void handleTextResponse(String param1) {
		if (param1.substring(0, 7).equals("<Error>")) {
			this.retryOrReportError(param1);
		} else if (param1.substring(0, 12).equals("<FatalError>")) {
			this.cleanUpAndComplete(false, param1);
		} else {
			this.cleanUpAndComplete(true, param1);
		}
	}

	private void cleanUpAndComplete(boolean param1, String param2) {
		if (!param1 && param2 instanceof String) {
			param2 = this.parseXML(param2);
		}
		this.cancelPendingRequest();
		//this._complete.dispatch(param1, param2);
		this._complete.dispatch(param1);
	}

	private String parseXML(String param1) {/*
		Array loc2 = param1.match("<.*>(.*)</.*>");
		return loc2 && loc2.length > 1 ? loc2[1] : param1;*/
		return param1;
	}

	private void cancelPendingRequest() {
		if (this.urlLoader != null) {
			this.urlLoader.removeEventListener(IOErrorEvent.IO_ERROR, new EventConsumer<>(this::onIOError));
			this.urlLoader.removeEventListener(SecurityErrorEvent.SECURITY_ERROR, new EventConsumer<>(this::onSecurityError));
			this.urlLoader.removeEventListener(Event.COMPLETE, new EventConsumer<>(this::onComplete));
			this.closeLoader();
			this.urlLoader = null;
		}
	}

	private void closeLoader() {
		try {
			this.urlLoader.close();
			return;
		} catch (Error e) {
			return;
		}
	}
}
