package serviceImp;

import java.util.List;

import dao.IConversationDao;
import daoImp.ConversationDaoImp;
import models.ConversationModel;
import service.IConversationService;

public class ConversationServiceImp implements IConversationService {
	IConversationDao converDao = new ConversationDaoImp();
	@Override
	public ConversationModel insert(ConversationModel conver) {
		return converDao.insert(conver);
	}

	@Override
	public ConversationModel update(ConversationModel conver) {
		return converDao.update(conver);
	}

	@Override
	public void delete(int ConversationID) {
		converDao.delete(ConversationID);
		
	}

	@Override
	public ConversationModel getOneByID(int ConversationID) {
		return converDao.getOneByID(ConversationID);
	}

	@Override
	public List<ConversationModel> getAll() {
		return converDao.getAll();
	}
}
