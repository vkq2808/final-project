package service;

import java.util.List;

import models.HomePostUserModel;

public interface IPostService {
	public void create(int uid, String content, String imageLink) throws Exception;
//	void update ();
//	void delete ();
//	getOneByID();
	List<HomePostUserModel> getAll();
}
