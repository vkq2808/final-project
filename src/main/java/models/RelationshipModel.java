package models;

public class RelationshipModel {
	int RelationshipID;
	int FromUserID;
	int ToUserID;
	boolean isFriend = false;
	boolean isBlocking = false;
	boolean isPending = false;
	
	public RelationshipModel() {
		super();
	}
	public RelationshipModel(int fromUserID, int toUserID, boolean isFriend, boolean isBlocking,
			boolean isPending) {
		super();
		FromUserID = fromUserID;
		ToUserID = toUserID;
		this.isFriend = isFriend;
		this.isBlocking = isBlocking;
		this.isPending = isPending;
	}
	public int getRelationshipID() {
		return RelationshipID;
	}
	public void setRelationshipID(int relationshipID) {
		RelationshipID = relationshipID;
	}
	public int getFromUserID() {
		return FromUserID;
	}
	public void setFromUserID(int fromUserID) {
		FromUserID = fromUserID;
	}
	public int getToUserID() {
		return ToUserID;
	}
	public void setToUserID(int toUserID) {
		ToUserID = toUserID;
	}
	public boolean isFriend() {
		return isFriend;
	}
	public void setFriend(boolean isFriend) {
		this.isFriend = isFriend;
	}
	public boolean isBlocking() {
		return isBlocking;
	}
	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}
	public boolean isPending() {
		return isPending;
	}
	public void setPending(boolean isPending) {
		this.isPending = isPending;
	}
}
