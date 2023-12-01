package models;

import java.time.LocalDateTime;
import java.util.List;

public class PostModel {
	int PostID;
	int UserID;
	String Content;
	List<String> ImageLink;
	boolean hasImage;
	LocalDateTime PostDate;

	UserModel user = null;
	List<LikeModel> listLikes = null;
	int numLikes;
	List<CommentModel> listComments = null;
	int numComments;

	public PostModel(int postID, int userID, String content, LocalDateTime postDate) {
		super();
		PostID = postID;
		UserID = userID;
		Content = content;
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

	public List<String> getListImageLink() {
		return ImageLink;
	}

	public void setListImageLink(List<String> imageLink) {
		ImageLink = imageLink;
	}
	
	public void addImageLink(String link) {
		ImageLink.add(link);
	}

	public LocalDateTime getPostDate() {
		return PostDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		PostDate = postDate;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public List<LikeModel> getListLikes() {
		return listLikes;
	}

	public void setListLikes(List<LikeModel> listLikes) {
		this.listLikes = listLikes;
		this.numLikes = listLikes.size();
	}

	public List<CommentModel> getListComments() {
		return listComments;
	}

	public void setListComments(List<CommentModel> listComments) {
		this.listComments = listComments;
		this.numComments = listComments.size();
	}

	public boolean getHasImage() {
		return hasImage;
	}

	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}

	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}

	public int getNumComments() {
		return numComments;
	}

	public void setNumComments(int numComments) {
		this.numComments = numComments;
	}
}
