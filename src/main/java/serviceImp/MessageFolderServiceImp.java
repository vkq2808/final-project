package serviceImp;

import java.util.List;

import dao.IMessageFolderDao;
import daoImp.MessageFolderDaoImp;
import models.MessageFolderModel;
import models.UserModel;
import service.IMessageFolderService;

public class MessageFolderServiceImp implements IMessageFolderService {

	IMessageFolderDao messageFolderDao = new MessageFolderDaoImp();
	IMessageFolderDao messfoDao = new MessageFolderDaoImp();

	@Override
	public MessageFolderModel create(MessageFolderModel messfo) {
		return messfoDao.create(messfo);
	}

	@Override
	public MessageFolderModel update(MessageFolderModel messfo) {
		return messfoDao.update(messfo);
	}

	@Override
	public void delete(int messfoID) {
		messfoDao.delete(messfoID);
	}

	@Override
	public List<MessageFolderModel> getAll() {
		return messfoDao.getAll();
	}

	@Override
	public MessageFolderModel getOneByID(int messfoID) {
		return messfoDao.getOneByID(messfoID);
	}

	@Override
	public List<MessageFolderModel> findByUserID(int userID) {

		return messageFolderDao.findByUserID(userID);
	}

	@Override
	public List<UserModel> getAllMember(MessageFolderModel messfo) {
		return messageFolderDao.getAllMember(messfo);
	}

}
