package serviceImp;

import java.util.List;

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

	@Override
	public UserModel create(UserModel user) {
		return userDao.create(user);
	}

	@Override
	public UserModel update(UserModel user) {
		return userDao.update(user);
	}

	@Override
	public void delete(int userID) {
		userDao.delete(userID);
	}

	@Override
	public List<UserModel> getAll() {
		return userDao.getAll();
	}

	@Override
	public boolean regist(String username, String password) {
		return userDao.regist(username, password);
	}

	@Override
	public UserModel login(String phone, String password) {
		UserModel user = userDao.findByAccount(phone);
		if (user != null && password.equals(user.getPassword()))
			return user;
		return null;
	}
	@Override
	public List<UserModel> getAllFriend(int senderID) {
		// TODO Auto-generated method stub
		return null;
	}
}
