package service;

import java.util.List;

import models.LikeModel;

public interface ILikeService {
	void create(int uid, int pid);
	List<LikeModel> getAllByPostID(int postID);
}
