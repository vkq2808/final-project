package dao;

import java.util.List;

import models.UserModel;

public interface IUserDao {

	UserModel create(UserModel user);

	UserModel update(UserModel user);

	void delete(int userID);

	UserModel getOneByID(int userID);

	List<UserModel> getAll();

	boolean regist(String username, String password);

	UserModel findByAccount(String username);

}
