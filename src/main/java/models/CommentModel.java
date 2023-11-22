package models;

import java.time.ZonedDateTime;

public class CommentModel {
	int CommentID;
	int UserID;
	int PostID;
	String Content;
	ZonedDateTime CreateAt;
	ZonedDateTime UpdateAt;
	public CommentModel(int commentID, int userID, int postID, String content, ZonedDateTime dateTimeCreate) {
		super();
		CommentID = commentID;
		UserID = userID;
		PostID = postID;
		Content = content;
		CreateAt = dateTimeCreate;
	}
	public CommentModel(int commentID, int userID, int postID, String content, ZonedDateTime createAt,
			ZonedDateTime updateAt) {
		super();
		CommentID = commentID;
		UserID = userID;
		PostID = postID;
		Content = content;
		CreateAt = createAt;
		UpdateAt = updateAt;
	}
	public CommentModel() {
		super();
	}
	public int getCommentID() {
		return CommentID;
	}
	public void setCommentID(int commentID) {
		CommentID = commentID;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getPostID() {
		return PostID;
	}
	public void setPostID(int postID) {
		PostID = postID;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public ZonedDateTime getCreateDateTime() {
		return CreateAt;
	}
	public void setCreateDateTime (ZonedDateTime createAt) {
		CreateAt = createAt;
	}
	public ZonedDateTime getUpdateDateTime() {
		return UpdateAt;
	}
	public void setUpdateDateTime(ZonedDateTime updateAt) {
		UpdateAt = updateAt;
	}
}