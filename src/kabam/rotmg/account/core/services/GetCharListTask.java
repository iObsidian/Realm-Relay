package kabam.rotmg.account.core.services;

import alde.flash.utils.EventConsumer;
import alde.flash.utils.Timer;
import alde.flash.utils.XML;
import flash.events.TimerEvent;
import kabam.rotmg.account.core.signals.CharListDataSignal;
import kabam.rotmg.account.securityQuestions.data.SecurityQuestionsModel;
import kabam.rotmg.account.web.view.MigrationDialog;
import rotmg.account.core.Account;
import rotmg.account.core.WebAccount;
import rotmg.appengine.api.AppEngineClient;
import rotmg.core.model.PlayerModel;
import rotmg.core.signals.SetLoadingMessageSignal;
import rotmg.dialogs.CloseDialogsSignal;
import rotmg.dialogs.OpenDialogSignal;
import rotmg.parameters.Parameters;
import rotmg.util.TextKey;

public class GetCharListTask {

	private static final int ONE_SECOND_IN_MS = 1000;

	private static final int MAX_RETRIES = 7;

	public Account account;

	public AppEngineClient client;

	public PlayerModel model;

	public SetLoadingMessageSignal setLoadingMessage;

	public CharListDataSignal charListData;

	//public ILogger logger;

	public OpenDialogSignal openDialog;

	public CloseDialogsSignal closeDialogs;

	public SecurityQuestionsModel securityQuestionsModel;

	private Object requestData;

	private Timer retryTimer;

	private int numRetries = 0;

	private boolean fromMigration = false;

	public GetCharListTask() {
		super();
	}

	protected void startTask() {
		this.logger.info("GetUserDataTask start");
		this.requestData = this.makeRequestData();
		this.sendRequest();
		Parameters.sendLogin = false;
	}

	private void sendRequest() {
		this.client.complete.addOnce(this::onComplete);
		this.client.sendRequest("/char/list", this.requestData);
	}

	private void onComplete(boolean param1, *param2) {
		if (param1) {
			this.onListComplete(param2);
		} else {
			this.onTextError(param2);
		}
	}

	public Object makeRequestData() {
		var loc1:Object = {};
		loc1.game_net_user_id = this.account.gameNetworkUserId();
		loc1.game_net = this.account.gameNetwork();
		loc1.play_platform = this.account.playPlatform();
		loc1.do_login = Parameters.sendLogin;
		MoreObjectUtil.addToObject(loc1, this.account.getCredentials());
		return loc1;
	}

	private void onListComplete(String param1) {
		double loc3 = 0;
		MigrationDialog loc4 = null;
		XML loc5 = null;
		XML loc2 = new XML(param1);
		if (loc2.hasOwnProperty("MigrateStatus")) {
			loc3 = loc2.MigrateStatus;
			if (loc3 == 5) {
				this.sendRequest();
			}
			loc4 = new MigrationDialog(this.account, loc3);
			this.fromMigration = true;
			loc4.done.addOnce(this::sendRequest);
			loc4.cancel.addOnce(this::clearAccountAndReloadCharacters);
			this.openDialog.dispatch(loc4);
		} else {
			if (loc2.hasOwnProperty("Account")) {
				if (this.account instanceof WebAccount) {
					WebAccount(this.account).userDisplayName = loc2.Account[0].Name;
					WebAccount(this.account).paymentProvider = loc2.Account[0].PaymentProvider;
					if (loc2.Account[0].hasOwnProperty("PaymentData")) {
						WebAccount(this.account).paymentData = loc2.Account[0].PaymentData;
					}
				}
				if (loc2.Account[0].hasOwnProperty("SecurityQuestions")) {
					this.securityQuestionsModel.showSecurityQuestionsOnStartup = loc2.Account[0].SecurityQuestions[0].ShowSecurityQuestionsDialog[0] == "1";
					this.securityQuestionsModel.clearQuestionsList();
					for (loc5:
					     loc2.Account[0].SecurityQuestions[0].SecurityQuestionsKeys[0].SecurityQuestionsKey) {
						this.securityQuestionsModel.addSecurityQuestion(loc5.toString());
					}
				}
			}
			this.charListData.dispatch(XML(param1));
			completeTask(true);
		}
		if (this.retryTimer != null) {
			this.stopRetryTimer();
		}
	}

	private void onTextError(String param1) {
		WebLoginDialog loc2 = null;
		this.setLoadingMessage.dispatch("error.loadError");
		if (param1 == "Account credentials not valid") {
			if (this.fromMigration) {
				loc2 = new WebLoginDialog();
				loc2.setError(TextKey.WEB_LOGIN_DIALOG_PASSWORD_INVALID);
				loc2.setEmail(this.account.getUserId());
				OpenDialogSignal.getInstance().dispatch(loc2));
			}
			this.clearAccountAndReloadCharacters();
		} else if (param1.equals("Account is under maintenance")) {
			this.setLoadingMessage.dispatch("This account has been banned");
			new TimerCallback(5, this.clearAccountAndReloadCharacters);
		} else {
			this.waitForASecondThenRetryRequest();
		}
	}

	private void clearAccountAndReloadCharacters() {
		this.logger.info("GetUserDataTask invalid credentials");
		this.account.clear();
		this.client.complete.addOnce(this::onComplete);
		this.requestData = this.makeRequestData();
		this.client.sendRequest("/char/list", this.requestData);
	}

	private void waitForASecondThenRetryRequest() {
		this.logger.info("GetUserDataTask error - retrying");
		this.retryTimer = new Timer(ONE_SECOND_IN_MS, 1);
		this.retryTimer.addEventListener(TimerEvent.TIMER_COMPLETE, new EventConsumer<>(this::onRetryTimer));
		this.retryTimer.start();
	}

	private void stopRetryTimer() {
		this.retryTimer.stop();
		this.retryTimer.removeEventListener(TimerEvent.TIMER_COMPLETE, new EventConsumer<>(this::onRetryTimer));
		this.retryTimer = null;
	}

	private void onRetryTimer(TimerEvent param1) {
		this.stopRetryTimer();
		if (this.numRetries < MAX_RETRIES) {
			this.sendRequest();
			this.numRetries++;
		} else {
			this.clearAccountAndReloadCharacters();
			this.setLoadingMessage.dispatch("LoginError.tooManyFails");
		}
	}


}
