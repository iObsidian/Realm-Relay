package kabam.rotmg.account.securityQuestions.data;

import alde.flash.utils.Vector;

/**
 * 100% match
 */
public class SecurityQuestionsModel {

	private boolean _showSecurityQuestionsOnStartup = false;

	private Vector _securityQuestionsList;

	public Vector securityQuestionsAnswers;

	public SecurityQuestionsModel() {
		super();
		this._securityQuestionsList = new Vector<>();
		this.securityQuestionsAnswers = new Vector<>();
	}

	public boolean getShowSecurityQuestionsOnStartup() {
		return this._showSecurityQuestionsOnStartup;
	}

	public void setShowSecurityQuestionsOnStartup(boolean param1) {
		this._showSecurityQuestionsOnStartup = param1;
	}

	public Vector getSecurityQuestionsList() {
		return this._securityQuestionsList;
	}

	public void clearQuestionsList() {
		this._securityQuestionsList = new Vector();
	}

	public void addSecurityQuestion(String param1) {
		this._securityQuestionsList.add(param1);
	}


}
