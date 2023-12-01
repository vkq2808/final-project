package serviceImp;

import java.util.List;

import dao.ILikeDao;
import daoImp.LikeDaoImp;
import models.LikeModel;
import service.ILikeService;

public class LikeServiceImp implements ILikeService {

	ILikeDao ild = new LikeDaoImp();
	
	@Override
	public void create(int uid, int pid) {
		ild.create(uid,pid);
	}

	@Override
	public List<LikeModel> getAllByPostID(int postID) {
		return ild.getAllByPostID(postID);
	}

}
