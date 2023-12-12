package dao;

import java.util.List;

import models.MessageFolderModel;
import models.PostModel;
import models.UserModel;

public interface IPostDao {

	void insert(int uid, String content, String imageLink);


	List<PostModel> getAll();

	List<String> findImageLink(int PostID);

	PostModel update(PostModel post);
	void delete(int PostID);
	PostModel getOneByID (int PostID);


	List<PostModel> getAllByUser(UserModel user);
	
}
