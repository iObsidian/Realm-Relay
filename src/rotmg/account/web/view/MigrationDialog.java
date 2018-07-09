package rotmg.account.web.view;

import flash.utils.timer.Timer;
import rotmg.util.components.SimpleButton;
import org.osflash.signals.Signal;
import rotmg.account.core.Account;
import rotmg.account.core.WebAccount;
import rotmg.appengine.api.AppEngineClient;

/**
 * Not implemented
 */
public class MigrationDialog {

	public Signal done;

	private Signal okButton;

	private Account account;

	private AppEngineClient client;

	private AppEngineClient progressCheckClient;

	private double lastPercent = 0;

	private double total = 100;

	private ProgressBar progBar;

	protected SimpleButton leftButton;

	protected SimpleButton rightButton;

	private Timer timerProgressCheck;

	private double status = 0;

	private boolean isClosed;
	public Signal<Object> cancel;

	public MigrationDialog(WebAccount account, double loc3) {
	}
}

