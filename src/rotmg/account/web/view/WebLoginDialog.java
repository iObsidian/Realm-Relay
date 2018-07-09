package rotmg.account.web.view;

import rotmg.ui.Frame;

public class WebLoginDialog extends Frame {


	private String email;
	private String error;

	public void setError(String error) {
		this.error = error;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
