package serviceImp;

import java.util.List;

import models.ParticipantModel;
import service.IParticipantService;
import dao.IParticipantDao;
import daoImp.ParticipantDaoImp;

public class ParticipantServiceImp implements IParticipantService {

	IParticipantDao parDao = new ParticipantDaoImp();
	
	@Override
	public ParticipantModel create(ParticipantModel par) {
		return parDao.create(par);
	}

	@Override
	public ParticipantModel update(ParticipantModel par) {
		return parDao.update(par);
	}

	@Override
	public void delete(int parID) {
		parDao.delete(parID);
	}

	@Override
	public List<ParticipantModel> getAll() {
		return parDao.getAll();
	}

	@Override
	public ParticipantModel getOneByID(int parID) {
		return parDao.getOneByID(parID);
	}
	
}
