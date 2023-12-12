package serviceImp;

import models.MessageFolderModel;
import models.MessageModel;
import service.IMessageService;

import java.util.List;

import dao.IMessageDao;
import dao.IMessageFolderDao;
import daoImp.MessageDaoImp;
import daoImp.MessageFolderDaoImp;

public class MessageServiceImp implements IMessageService {

	IMessageDao messDao = new MessageDaoImp();
	IMessageFolderDao messfoDao = new MessageFolderDaoImp();

	@Override
	public MessageModel create(MessageModel mess) {
		mess = messDao.create(mess);
		// Cập nhật LatestMessage cho MessageFolder
		MessageFolderModel messfo = messfoDao.getOneByID(mess.getMessageFolderID());
		messfo.setLatestMessageID(messfoDao.getLatestMessageID(messfo));
		messfoDao.update(messfo);
		// Trả về model để thông báo thành c
		return mess;
	}

	@Override
	public MessageModel update(MessageModel mess) {
		return messDao.update(mess);
	}

	@Override
	public void delete(int messID) {
		messDao.delete(messID);
	}

	@Override
	public MessageModel getOneByID(int messID) {
		return messDao.getOneByID(messID);
	}

	@Override
	public List<MessageModel> getAllByMessageFolderID(int messageFolderID) {
		return messDao.getAllByMessageFolderID(messageFolderID);
	}
}
