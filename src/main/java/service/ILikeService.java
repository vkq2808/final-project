package service;

import java.util.List;

import models.LikeModel;
import models.PostModel;
import models.UserModel;

public interface ILikeService {
	LikeModel create(LikeModel like);
	List<LikeModel> getAllByPostID(int postID);
	LikeModel getOneByUserPost(UserModel user, PostModel post);
	LikeModel delete(LikeModel likemodel);
}
