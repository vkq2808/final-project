package models;

import java.time.LocalDateTime;

public class LikeModel {
	int LikeID;
	int PostID;
	int UserID;
	String CreatedAt;
	public LikeModel(int postID, int userID, String createdAt) {
		super();
		PostID = postID;
		UserID = userID;
		CreatedAt = createdAt;
	}
	
	public LikeModel(int postID, int userID) {
		super();
		PostID = postID;
		UserID = userID;
	}

	public LikeModel() {
		super();
	}
	public String getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
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

	public int getLikeID() {
		return LikeID;
	}

	public void setLikeID(int likeID) {
		LikeID = likeID;
	}
}
