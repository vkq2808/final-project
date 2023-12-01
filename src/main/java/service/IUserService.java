package service;

import models.UserModel;

public interface IUserService {
//	void create ();
//	void update ();
//	void delete ();
	UserModel getOneByID(int userID);
//	getAll();
}
