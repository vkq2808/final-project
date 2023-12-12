package service;

import java.util.List;

import models.ParticipantModel;

public interface IParticipantService {

	ParticipantModel create(ParticipantModel par);
	
	ParticipantModel update(ParticipantModel par);
	
	void delete(int parID);
	
	List<ParticipantModel> getAll();
	
	ParticipantModel getOneByID(int parID);
}
