package models;

public class UserModel {
	int UserID;
	String UserName;
	String Email;
	String NumPhone;
	String AvatarLink;
	String Password;
	boolean isAdmin;
	
	public UserModel() {
		super();
	}
	public UserModel(String userName, String numPhone, String email, String avatarLink) {
		super();
		UserName = userName;
		Email = email;
		NumPhone = numPhone;
		AvatarLink = avatarLink;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getNumPhone() {
		return NumPhone;
	}
	public void setNumPhone(String numPhone) {
		NumPhone = numPhone;
	}
	public String getAvatarLink() {
		if (AvatarLink == null)
			return "https://static.vecteezy.com/system/resources/previews/009/734/564/original/default-avatar-profile-icon-of-social-media-user-vector.jpg";
		return AvatarLink;
	}
	public void setAvatarLink(String avatarLink) {
		AvatarLink = avatarLink;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public boolean isAdmin() {
		return isAdmin;
	}
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
