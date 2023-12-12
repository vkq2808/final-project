package service;

import java.util.List;

import models.UserModel;

public interface IUserService {
UserModel create(UserModel user);
	
	UserModel update(UserModel user);
	
	void delete(int userID);
	
	UserModel getOneByID(int userID);
	
	List<UserModel> getAll();

	boolean regist(String username, String password);

	UserModel login(String username, String password);

	List<UserModel> getAllFriend(int senderID);
}
