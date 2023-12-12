package models;

import daoImp.MessageFolderDaoImp;
import daoImp.UserDaoImp;

public class MessageModel {
	int MessageID;
	int SenderID;
	int MessageFolderID;
	String Content;
	String CreatedAt;

	UserModel Sender;
	MessageFolderModel MessageFolder;

	public MessageFolderModel getMessageFolder() {
		return MessageFolder;
	}

	public void setMessageFolder(MessageFolderModel messageFolder) {
		MessageFolder = messageFolder;
	}

	public MessageModel() {
		super();
	}

	public int getMessageID() {
		return MessageID;
	}

	public void setMessageID(int messageID) {
		MessageID = messageID;
	}

	public int getSenderID() {
		return SenderID;
	}

	public void setSenderID(int senderID) {
		SenderID = senderID;
		Sender = new UserDaoImp().getOneByID(senderID);
	}

	public int getMessageFolderID() {
		return MessageFolderID;
	}

	public void setMessageFolderID(int messageFolderID) {
		MessageFolderID = messageFolderID;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}

	public UserModel getSender() {
		return Sender;
	}

	public void setSender(UserModel sender) {
		Sender = sender;
	}
}