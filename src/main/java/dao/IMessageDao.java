package dao;

import java.util.List;

import models.MessageFolderModel;
import models.MessageModel;

public interface IMessageDao {
	
	MessageModel create(MessageModel mess);
	
	MessageModel update(MessageModel mess);
	
	void delete(int messID);
	
	MessageModel getOneByID(int messID);

	List<MessageModel> getAllByMessageFolderID(int messageFolderID);

}
