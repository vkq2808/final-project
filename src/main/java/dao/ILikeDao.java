package dao;

import java.util.List;

import models.LikeModel;
import models.PostModel;
import models.UserModel;

public interface ILikeDao {


	List<LikeModel> getAllByPostID(int postID);

	LikeModel create(LikeModel like);

	LikeModel getOneByUserPost(UserModel user, PostModel post);

	LikeModel delete(LikeModel likemodel);

}
