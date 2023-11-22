package models;

public class LikeModel {
	int PostID;
	int UserID;
	public LikeModel(int postID, int userID) {
		super();
		PostID = postID;
		UserID = userID;
	}
	public LikeModel() {
		super();
	}
	public int getPostID() {
		return PostID;
	}
	public void setPostID(int postID) {
		PostID = postID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	
}
