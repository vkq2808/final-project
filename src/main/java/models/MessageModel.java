package models;

import java.time.ZonedDateTime;

public class MessageModel {
	int MessID;
	int SenderID;
	int ReceiverID;
	String Content;
	ZonedDateTime SentDateTime;
	public MessageModel(int messID, int senderID, int receiverID, String content, ZonedDateTime sentDateTime) {
		super();
		MessID = messID;
		SenderID = senderID;
		ReceiverID = receiverID;
		Content = content;
		SentDateTime = sentDateTime;
	}
	public MessageModel() {
		super();
	}
	public int getMessID() {
		return MessID;
	}
	public void setMessID(int messID) {
		MessID = messID;
	}
	public int getSenderID() {
		return SenderID;
	}
	public void setSenderID(int senderID) {
		SenderID = senderID;
	}
	public int getReceiverID() {
		return ReceiverID;
	}
	public void setReceiverID(int receiverID) {
		ReceiverID = receiverID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public ZonedDateTime getSentDateTime() {
		return SentDateTime;
	}
	public void setSentDateTime(ZonedDateTime sentDateTime) {
		SentDateTime = sentDateTime;
	}
}