package models;

import java.time.ZonedDateTime;

public class ConversationModel {
	int ConversationID;
	int CallerID;
	int ReceiverID;
	ZonedDateTime CreateAt;
	ZonedDateTime UpdateAt;
	ZonedDateTime DeleteAt;
	public ConversationModel(int conversationID, int callerID, int receiverID, ZonedDateTime createAt) {
		super();
		ConversationID = conversationID;
		CallerID = callerID;
		ReceiverID = receiverID;
		CreateAt = createAt;
	}
	
	public ConversationModel() {
		super();
	}

	public int getConversationID() {
		return ConversationID;
	}
	public void setConversationID(int conversationID) {
		ConversationID = conversationID;
	}
	public int getCallerID() {
		return CallerID;
	}
	public void setCallerID(int callerID) {
		CallerID = callerID;
	}
	public int getReceiverID() {
		return ReceiverID;
	}
	public void setReceiverID(int receiverID) {
		ReceiverID = receiverID;
	}
	public ZonedDateTime getCreateAt() {
		return CreateAt;
	}
	public void setCreateAt(ZonedDateTime createAt) {
		CreateAt = createAt;
	}
	public ZonedDateTime getUpdateAt() {
		return UpdateAt;
	}
	public void setUpdateAt(ZonedDateTime updateAt) {
		UpdateAt = updateAt;
	}
	public ZonedDateTime getDeleteAt() {
		return DeleteAt;
	}
	public void setDeleteAt(ZonedDateTime deleteAt) {
		DeleteAt = deleteAt;
	}
}
