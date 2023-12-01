package dao;

import models.UserModel;

public interface IUserDao {

	UserModel getOneByID(int userID);
	
}
