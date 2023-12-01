package models;

import java.time.LocalDateTime;

public class LikeModel {
	int PostID;
	int UserID;
	LocalDateTime CreatedAt;
	public LikeModel(int postID, int userID, LocalDateTime createdAt) {
		super();
		PostID = postID;
		UserID = userID;
		CreatedAt = createdAt;
	}
	public LikeModel() {
		super();
	}
	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
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
	
}
