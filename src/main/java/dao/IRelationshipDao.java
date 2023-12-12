package dao;

import java.util.List;

import models.RelationshipModel;

public interface IRelationshipDao {

	RelationshipModel create(RelationshipModel relate);
	
	RelationshipModel update(RelationshipModel relate);
	
	void delete(int relationID);
	
	List<RelationshipModel> getAll();
	
	RelationshipModel getOneByID(int relateID);

	List<RelationshipModel> findByFromUserID(int fromUserID);

	RelationshipModel findBy2UserID(int fromUserID, int toUserID);

	List<RelationshipModel> findByToUserID(int toUserID);
}
