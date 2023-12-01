package dao;

import java.util.List;

import models.LikeModel;

public interface ILikeDao {

	void create(int uid, int pid);

	List<LikeModel> getAllByPostID(int postID);

}
