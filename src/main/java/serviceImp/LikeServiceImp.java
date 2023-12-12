package serviceImp;

import java.util.List;

import dao.ILikeDao;
import daoImp.LikeDaoImp;
import models.LikeModel;
import models.PostModel;
import models.UserModel;
import service.ILikeService;

public class LikeServiceImp implements ILikeService {

	ILikeDao ild = new LikeDaoImp();
	
	@Override
	public LikeModel create(LikeModel like) {
		return ild.create(like);
	}

	@Override
	public List<LikeModel> getAllByPostID(int postID) {
		return ild.getAllByPostID(postID);
	}
	@Override
	public LikeModel getOneByUserPost(UserModel user, PostModel post){
		return ild.getOneByUserPost(user,post);
	}

	@Override
	public LikeModel delete(LikeModel likemodel) {
		return ild.delete(likemodel);
	}
}
