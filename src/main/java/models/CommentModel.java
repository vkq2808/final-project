package models;

import java.time.LocalDateTime;

import service.IUserService;
import serviceImp.UserServiceImp;

public class CommentModel {
	int CommentID;
	int UserID;
	int PostID;
	String Content;
	LocalDateTime CreatedAt;
	LocalDateTime UpdatedAt;
	
	UserModel User;
	
	public CommentModel(int commentID, int userID, int postID, String content, LocalDateTime dateTimeCreate) {
		super();
		CommentID = commentID;
		UserID = userID;
		PostID = postID;
		Content = content;
		CreatedAt = dateTimeCreate;
		IUserService userservice = new UserServiceImp();
		User = userservice.getOneByID(userID);
	}
	public CommentModel(int commentID, int userID, int postID, String content, LocalDateTime createAt,
			LocalDateTime updateAt) {
		super();
		CommentID = commentID;
		UserID = userID;
		PostID = postID;
		Content = content;
		CreatedAt = createAt;
		UpdatedAt = updateAt;
		IUserService userservice = new UserServiceImp();
		User = userservice.getOneByID(userID);
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
		IUserService userservice = new UserServiceImp();
		User = userservice.getOneByID(userID);
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
	public LocalDateTime getCreatedAt() {
		return CreatedAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		CreatedAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return UpdatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		UpdatedAt = updatedAt;
	}
	public UserModel getUser() {
		return User;
	}
	public void setUser(UserModel user) {
		User = user;
	}
	public CommentModel() {
		super();
	}
}