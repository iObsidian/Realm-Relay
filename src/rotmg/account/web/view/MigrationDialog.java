package rotmg.account.web.view;

import flash.utils.timer.Timer;
import org.osflash.signals.Signal;
import rotmg.account.core.Account;
import rotmg.account.core.WebAccount;
import rotmg.appengine.api.AppEngineClient;
import rotmg.util.components.SimpleButton;

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

