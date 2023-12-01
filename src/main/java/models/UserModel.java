package models;

public class UserModel {
	int UserID;
	String UserName;
	String Email;
	String NumPhone;
	String AvatarLink;
	
	public UserModel() {
		super();
	}
	public UserModel(int userID, String userName, String numPhone, String email, String avatarLink) {
		super();
		UserID = userID;
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
		return AvatarLink;
	}
	public void setAvatarLink(String avatarLink) {
		AvatarLink = avatarLink;
	}
}
