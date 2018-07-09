package kabam.rotmg.account.web.view;

import org.osflash.signals.Signal;

import alde.flash.utils.Timer;
import javafx.scene.control.ProgressBar;
import kabam.rotmg.account.core.Account;
import kabam.rotmg.account.core.WebAccount;
import kabam.rotmg.appengine.api.AppEngineClient;
import kabam.rotmg.util.components.SimpleButton;

/**
 * Not implemented
 */
public class MigrationDialog {

	public Signal done;
	public Signal<Object> cancel;
	protected SimpleButton leftButton;
	protected SimpleButton rightButton;
	private Signal okButton;
	private Account account;
	private AppEngineClient client;
	private AppEngineClient progressCheckClient;
	private double lastPercent = 0;
	private double total = 100;
	private ProgressBar progBar;
	private Timer timerProgressCheck;
	private double status = 0;
	private boolean isClosed;

	public MigrationDialog(WebAccount account, double loc3) {
	}
}

