package serviceImp;

import java.util.ArrayList;
import java.util.List;
import models.RelationshipModel;
import models.UserModel;
import service.IRelationshipService;
import dao.IRelationshipDao;
import dao.IUserDao;
import daoImp.RelationshipDaoImpl;
import daoImp.UserDaoImp;

public class RelationshipServiceImpl implements IRelationshipService {

	IRelationshipDao relateDao = new RelationshipDaoImpl();
	IUserDao userDao = new UserDaoImp();

	@Override
	public RelationshipModel create(RelationshipModel relate) {
		return relateDao.create(relate);
	}

	@Override
	public RelationshipModel update(RelationshipModel relate) {
		return relateDao.update(relate);
	}

	@Override
	public void delete(int relationID) {
		relateDao.delete(relationID);
	}

	@Override
	public List<RelationshipModel> getAll() {
		return relateDao.getAll();
	}

	@Override
	public RelationshipModel getOneByID(int relateID) {
		return relateDao.getOneByID(relateID);
	}
	@Override
	public List<UserModel> findByToUserID(int ToUserID, boolean isFriend, boolean isBlocking, boolean isPending){
		List<RelationshipModel> list = relateDao.findByToUserID(ToUserID);
		if (list == null) {
			return null;
		}
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (RelationshipModel r : list) {
			if (r.isBlocking() != isBlocking || r.isFriend() != isFriend || r.isPending() != isPending) {
			} else {
				listUser.add(userDao.getOneByID(r.getToUserID()));
			}
		}
		return listUser;
	}
	@Override
	public List<UserModel> findByFromUserID(int FromUserID, boolean isFriend, boolean isBlocking, boolean isPending) {
		List<RelationshipModel> list = relateDao.findByFromUserID(FromUserID);
		if (list == null) {
			return null;
		}
		List<UserModel> listUser = new ArrayList<UserModel>();
		for (RelationshipModel r : list) {
			if (r.isBlocking() != isBlocking || r.isFriend() != isFriend || r.isPending() != isPending) {
			} else {
				listUser.add(userDao.getOneByID(r.getToUserID()));
			}
		}
		return listUser;
	}

	@Override
	public RelationshipModel filter(int FromUserID, int ToUserID, boolean isFriend, boolean isBlocking,
			boolean isPending) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RelationshipModel findBy2User(UserModel FromUser, UserModel ToUser) {
		RelationshipModel re = relateDao.findBy2UserID(FromUser.getUserID(), ToUser.getUserID());
		if (re == null) {
			re = new RelationshipModel();
			re.setFromUserID(FromUser.getUserID());
			re.setToUserID(ToUser.getUserID());
		}
		return re;
	}
}
