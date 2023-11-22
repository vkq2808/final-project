package dao;

import java.util.List;

import models.HomePostUserModel;

public interface IPostDao {

	void insert(int uid, String content, String imageLink) throws Exception;

	List<HomePostUserModel> getAll();

}
