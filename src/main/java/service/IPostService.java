package service;

import java.util.List;

import models.PostModel;

public interface IPostService {
	public void create(int uid, String content, String imageLink) throws Exception;
//	void update ();
//	void delete ();
//	getOneByID();
	
	List<String> findImageLink(int PostID);
	List<PostModel> getAll();
}
