package dao;

import java.util.List;

import models.ParticipantModel;



public interface IParticipantDao {

	ParticipantModel create(ParticipantModel par);
	
	ParticipantModel update(ParticipantModel par);
	
	void delete(int parID);
	
	List<ParticipantModel> getAll();
	
	ParticipantModel getOneByID(int parID);
}
