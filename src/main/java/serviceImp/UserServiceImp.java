package serviceImp;

import dao.IUserDao;
import daoImp.UserDaoImp;
import models.UserModel;
import service.IUserService;

public class UserServiceImp implements IUserService {

	IUserDao userDao = new UserDaoImp();
	
	@Override
	public UserModel getOneByID(int userID) {
		return userDao.getOneByID(userID);
	}

}
