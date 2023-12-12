package service;

import java.util.List;

import models.RelationshipModel;
import models.UserModel;

public interface IRelationshipService {

	RelationshipModel create(RelationshipModel relate);
	
	RelationshipModel update(RelationshipModel relate);
	
	void delete(int relationID);
	
	List<RelationshipModel> getAll();
	
	RelationshipModel getOneByID(int relateID);
	
	List<UserModel> findByFromUserID(int FromUserID, boolean isFriend, boolean isBlocking, boolean isPending);
	
	RelationshipModel filter(int FromUserID, int ToUserID, boolean isFriend, boolean isBlocking, boolean isPending);
	
	RelationshipModel findBy2User(UserModel FromUser, UserModel ToUser);

	List<UserModel> findByToUserID(int ToUserID, boolean isFriend, boolean isBlocking, boolean isPending);
}
