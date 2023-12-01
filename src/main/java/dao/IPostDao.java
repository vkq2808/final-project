package dao;

import java.util.List;

import models.PostModel;

public interface IPostDao {

	void insert(int uid, String content, String imageLink) throws Exception;


	List<PostModel> getAll();

	List<String> findImageLink(int postID);

}
