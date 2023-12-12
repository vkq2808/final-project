package serviceImp;

import service.IPostService;
import service.IRelationshipService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dao.IPostDao;
import daoImp.PostDaoImp;
import models.PostModel;
import models.RelationshipModel;
import models.UserModel;

public class PostServiceImp implements IPostService {

	IRelationshipService relationshipservice = new RelationshipServiceImpl();
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


	@Override
	public PostModel update(PostModel post) {
		return postDao.update(post);
	}

	@Override
	public void delete(int postID) {
		postDao.delete(postID);
		
	}

	@Override
	public PostModel getOneByID(int PostID) {
		return postDao.getOneByID(PostID);
	}

	@Override
	public List<PostModel> getAllByUser(UserModel user) {
		return postDao.getAllByUser(user);
	}

	@Override
	public List<PostModel> filterByRelationShip(List<PostModel> list, UserModel user) {
		List<PostModel> filtered = new ArrayList<PostModel>();
		for (PostModel postModel : list) {
			RelationshipModel op_rel = relationshipservice.findBy2User(postModel.getUser(), user);
			RelationshipModel rel = relationshipservice.findBy2User(user, postModel.getUser());
			
			if(!op_rel.isBlocking() && !rel.isBlocking() ) {
				filtered.add(postModel);
			}
		}
		return filtered;
	}

}
