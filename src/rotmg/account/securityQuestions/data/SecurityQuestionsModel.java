package rotmg.account.securityQuestions.data;

import alde.flash.utils.Vector;

/**
 * 100% match
 */
public class SecurityQuestionsModel {

	public boolean showSecurityQuestionsOnStartup = false;
	public Vector securityQuestionsAnswers;
	private Vector securityQuestionsList;

	public SecurityQuestionsModel() {
		super();
		this.securityQuestionsList = new Vector<>();
		this.securityQuestionsAnswers = new Vector<>();
	}

	public boolean getShowSecurityQuestionsOnStartup() {
		return this.showSecurityQuestionsOnStartup;
	}

	public void setShowSecurityQuestionsOnStartup(boolean param1) {
		this.showSecurityQuestionsOnStartup = param1;
	}

	public Vector getSecurityQuestionsList() {
		return this.securityQuestionsList;
	}

	public void clearQuestionsList() {
		this.securityQuestionsList = new Vector();
	}

	public void addSecurityQuestion(String param1) {
		this.securityQuestionsList.add(param1);
	}


}
