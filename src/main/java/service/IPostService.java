package service;

import java.util.List;

import models.PostModel;
import models.UserModel;

public interface IPostService {
	public void create(int uid, String content, String imageLink) throws Exception;
	PostModel update(PostModel post);
	void delete (int PostID);
	PostModel getOneByID(int PosID);
	
	List<String> findImageLink(int PostID);
	List<PostModel> getAll();
	List<PostModel> getAllByUser(UserModel user);
	List<PostModel> filterByRelationShip(List<PostModel> list, UserModel user);
}
