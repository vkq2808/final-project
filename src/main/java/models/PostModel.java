package models;

import java.time.ZonedDateTime;

public class PostModel {
	int PostID;
	int UserID;
	String Content;
	String ImageLink;
	ZonedDateTime PostDate;
	public PostModel(int postID, int userID, String content, String imageLink, ZonedDateTime postDate) {
		super();
		PostID = postID;
		UserID = userID;
		Content = content;
		ImageLink = imageLink;
		PostDate = postDate;
	}
	public PostModel() {
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
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getImageLink() {
		return ImageLink;
	}
	public void setImageLink(String imageLink) {
		ImageLink = imageLink;
	}
	public ZonedDateTime getPostDate() {
		return PostDate;
	}
	public void setPostDate(ZonedDateTime postDate) {
		PostDate = postDate;
	}
	
}
