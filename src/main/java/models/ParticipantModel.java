package models;

import service.IUserService;
import serviceImp.UserServiceImp;

public class ParticipantModel {

	int ParID;
	int MessageFolderID;
	int UserID;
	
	UserModel User;
	
	public ParticipantModel(int parID, int messageFolderID, int userID) {
		super();
		ParID = parID;
		MessageFolderID = messageFolderID;
		UserID = userID;
	}
	
	public ParticipantModel() {
		super();
	}

	public int getParID() {
		return ParID;
	}

	public void setParID(int parID) {
		ParID = parID;
	}
	public int getMessageFolderID() {
		return MessageFolderID;
	}

	public void setMessageFolderID(int messageFolderID) {
		MessageFolderID = messageFolderID;
	}

	public UserModel getUser() {
		return User;
	}

	public void setUser(UserModel user) {
		User = user;
	}

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
		IUserService userservice = new UserServiceImp();
		User = userservice.getOneByID(userID);
	}
}
