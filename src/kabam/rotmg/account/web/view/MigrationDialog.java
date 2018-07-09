package kabam.rotmg.account.web.view;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.SignalConsumer;
import alde.flash.utils.Timer;
import alde.flash.utils.XML;
import flash.events.MouseEvent;
import flash.events.TimerEvent;
import javafx.scene.control.ProgressBar;
import kabam.rotmg.account.core.view.EmptyFrame;
import kabam.rotmg.util.components.SimpleButton;
import org.osflash.signals.Signal;
import rotmg.account.core.Account;
import rotmg.appengine.api.AppEngineClient;
import rotmg.dialogs.CloseDialogsSignal;

public class MigrationDialog extends EmptyFrame {

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

	public MigrationDialog(Account param1, double param2) {
		super(500, 220, "Maintenance Needed");
		this.timerProgressCheck = new Timer(2000, 0);
		this.isClosed = false;
		setDesc("Press OK to begin maintenance on \n\n" + param1.getUserName() + "\n\nor cancel to login with a different account", true);
		this.makeAndAddLeftButton("Cancel");
		this.makeAndAddRightButton("OK");
		this.account = param1;
		this.status = param2;
		this.client = AppEngineClient.getInstance();
		this.okButton = new NativeMappedSignal(this.rightButton, MouseEvent.CLICK);
		cancel = new NativeMappedSignal(this.leftButton, MouseEvent.CLICK);
		this.done = new Signal();
		this.okButton.addOnce(new SignalConsumer<>(this::okButton_doMigrate));
		this.done.addOnce(new SignalConsumer<>(this::closeMyself));
		cancel.addOnce(new SignalConsumer<>(this::removeMigrateCallback));
		cancel.addOnce(new SignalConsumer<>(this::closeMyself));
	}

	private void okButton_doMigrate() {
		Object loc1 = null;
		this.rightButton.setEnabled(false);
		if (this.status == 1) {
			loc1 = this.account.getCredentials();
			this.client.complete.addOnce(this::onMigrateStartComplete);
			this.client.sendRequest("/migrate/doMigration", loc1);
		}
	}

	private void startPercentLoop() {
		this.timerProgressCheck.addEventListener(TimerEvent.TIMER, new EventConsumer<>(this::percentLoop));
		if (this.progressCheckClient == null) {
			this.progressCheckClient = SimpleAppEngineClient.getInstance();
		}
		this.timerProgressCheck.start();
		this.updatePercent(0);
	}

	private void stopPercentLoop() {
		this.updatePercent(100);
		this.timerProgressCheck.stop();
		this.timerProgressCheck.removeEventListener(TimerEvent.TIMER, new EventConsumer<>(this::percentLoop));
	}

	private void percentLoop(TimerEvent param1) {
		Object loc2 = this.account.getCredentials();
		this.progressCheckClient.complete.addOnce(this::onUpdateStatusComplete);
		this.progressCheckClient.sendRequest("/migrate/progress", loc2);
	}

	private void onUpdateStatusComplete(boolean param1, XML param2) {
		XML loc3 = null;
		String loc4 = null;
		double loc5 = 0;
		double loc6 = 0;
		if (param1) {
			if (this.isClosed) {
				return;
			}
			loc3 = new XML(param2);
			if (loc3.hasOwnProperty("Percent")) {
				loc4 = loc3.getValue("Percent");
				if (loc5 != this.lastPercent) {
					this.updatePercent(loc5);
				}
			} else if (loc3.hasOwnProperty("MigrateStatus")) {
				loc6 = loc3.getIntValue("MigrateStatus");
				if (loc6 == 44) {
					this.stopPercentLoop();
					this.reset();
				}
			}
		}
	}

	private void updatePercent(double param1) {
		this.progBar.update(param1);
		this.lastPercent = param1;
		setDesc("" + param1 + "%", true);
	}

	private void onMigrateStartComplete(boolean param1, XML param2) {
		XML loc3 = null;
		double loc4 = 0;
		if (this.isClosed) {
			return;
		}
		if (param1) {
			loc3 = new XML(param2);
			if (loc3.hasOwnProperty("MigrateStatus")) {
				loc4 = loc3.getIntValue("MigrateStatus");
				if (loc4 == 44) {
					this.stopPercentLoop();
					this.reset();
				} else if (loc4 == 4) {
					this.addProgressBar();
					setTitle("Migration In Progress", true);
					this.startPercentLoop();
				} else {
					this.stopPercentLoop();
					this.reset();
				}
			}
		} else {
			this.stopPercentLoop();
			this.reset();
		}
	}

	private void reset() {
		setTitle("Error, please try again. Maintenance needed", true);
		setDesc("Press OK to begin maintenance on \n\n" + this.account.getUserName() + "\n\nor cancel to login with a different account", true);
		this.removeProgressBar();
		this.okButton.addOnce(new SignalConsumer(this::okButton_doMigrate));
		this.rightButton.setEnabled(true);
	}

	private void addProgressBar() {
		double loc2 = 0;
		this.removeProgressBar();
		double loc1 = TEXT_MARGIN;
		loc2 = modalHeight / 3;
		double loc3 = modalWidth - loc1 * 2;
		double loc4 = 40;
		this.progBar = new ProgressBar(loc3, loc4);
		addChild(this.progBar);
		this.progBar.x = loc1;
		this.progBar.y = loc2;
	}


	private void removeProgressBar() {
		if (this.progBar != null && this.progBar.parent != null) {
			removeChild(this.progBar);
		}
	}

	private void removeMigrateCallback() {
		this.done.removeAll();
	}

	private void closeMyself() {
		this.isClosed = true;
		CloseDialogsSignal loc1 = CloseDialogsSignal.getInstance();
		loc1.dispatch();
	}

	private void makeAndAddLeftButton(String param1) {
		this.leftButton = new SimpleButton(param1);
		if (!param1.equals("")) {
			this.leftButton.buttonMode = true;
			addChild(this.leftButton);
			this.leftButton.x = modalWidth / 2 - 100 - this.leftButton.width;
			this.leftButton.y = modalHeight - 50;
		}
	}

	private void makeAndAddRightButton(String param1) {
		this.rightButton = new SimpleButton(this.leftButton.text.text);
		this.rightButton.freezeSize();
		this.rightButton.setText(param1);
		if (!param1.equals("")) {
			this.rightButton.buttonMode = true;
			addChild(this.rightButton);
			this.rightButton.x = modalWidth / 2 + 100;
			this.rightButton.y = modalHeight - 50;
		}
	}

}

