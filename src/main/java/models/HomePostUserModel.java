package models;

public class HomePostUserModel {
	PostModel Post;
	UserModel User;
	boolean hasImage;
	public HomePostUserModel(PostModel post, UserModel user) {
		super();
		this.Post = post;
		this.User = user;
	}
	public HomePostUserModel() {
		super();
		Post = new PostModel();
		User = new UserModel();
	}
	public PostModel getPost() {
		return Post;
	}
	public void setPost(PostModel post) {
		this.Post = post;
	}
	public UserModel getUser() {
		return User;
	}
	public void setUser(UserModel user) {
		this.User = user;
	}
	public boolean isHasImage() {
		return hasImage;
	}
	public void setHasImage(boolean hasImage) {
		this.hasImage = hasImage;
	}
}
