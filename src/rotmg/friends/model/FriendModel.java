package rotmg.friends.model;

public class FriendModel {

	static FriendModel instance;
	public int hasInvitations;

	public static FriendModel getInstance() {

		if (instance == null) {
			instance = new FriendModel();
		}

		return instance;
	}
}
