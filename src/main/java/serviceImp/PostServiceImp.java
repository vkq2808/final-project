package serviceImp;

import service.IPostService;

import java.util.List;

import dao.IPostDao;
import daoImp.PostDaoImp;
import models.PostModel;

public class PostServiceImp implements IPostService {

	IPostDao postDao = new PostDaoImp();

	@Override
	public void create(int uid, String content, String imageLink) throws Exception {
		postDao.insert(uid, content, imageLink);
	}

	@Override
	public List<PostModel> getAll() {
		return postDao.getAll();
	}

	@Override
	public List<String> findImageLink(int PostID) {
		return postDao.findImageLink(PostID);
	}

}
