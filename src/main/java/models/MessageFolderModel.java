package models;

import daoImp.MessageDaoImp;

public class MessageFolderModel {
	int MessageFolderID;
	String MessageFolderName;
	String MessageFolderAvatarLink;
	int LatestMessageID;

	MessageModel LatestMessage;

	public MessageFolderModel() {
		super();
	}

	public int getMessageFolderID() {
		return MessageFolderID;
	}

	public void setMessageFolderID(int messageFolderID) {
		MessageFolderID = messageFolderID;
	}

	public String getMessageFolderName() {
		return MessageFolderName;
	}

	public void setMessageFolderName(String messageFolderName) {
		MessageFolderName = messageFolderName;
	}

	public String getMessageFolderAvatarLink() {
		return MessageFolderAvatarLink;
	}

	public void setMessageFolderAvatarLink(String messageFolderAvatarLink) {
		MessageFolderAvatarLink = messageFolderAvatarLink;
	}

	public int getLatestMessageID() {
		return LatestMessageID;
	}

	public void setLatestMessageID(int latestMessageID) {
		if (LatestMessageID != latestMessageID) {
			LatestMessage = new MessageDaoImp().getOneByID(latestMessageID);
			LatestMessageID = latestMessageID;
		}
	}

	public MessageModel getLatestMessage() {
		return LatestMessage;
	}

	public void setLatestMessage(MessageModel latestMessage) {
		LatestMessage = latestMessage;
	}

}
