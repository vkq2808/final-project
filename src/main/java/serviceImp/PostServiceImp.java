package serviceImp;

import service.IPostService;

import java.util.List;

import dao.IPostDao;
import daoImp.PostDaoImp;
import models.HomePostUserModel;

public class PostServiceImp implements IPostService {

	IPostDao postDao = new PostDaoImp();
	
	@Override
	public void create(int uid, String content, String imageLink) throws Exception {
		postDao.insert(uid, content, imageLink);
	}

	@Override
	public List<HomePostUserModel> getAll() {
		List<HomePostUserModel> list = postDao.getAll();
		for (int i = 0; i < list.size(); i++) {
			HomePostUserModel pu = list.get(i);
			String imageLink = pu.getPost().getImageLink();
			if ( imageLink != null) {
				pu.setHasImage(true);
			}
			else {
				pu.setHasImage(false);
			}
			list.set(i, pu);
		}
		return list;
	}

}
