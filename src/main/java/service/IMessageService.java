package service;

import java.util.List;

import models.MessageModel;

public interface IMessageService {

	MessageModel create(MessageModel mess);
	
	MessageModel update(MessageModel mess);
	
	void delete(int messID);
	
	MessageModel getOneByID(int messID);

	List<MessageModel> getAllByMessageFolderID(int messageFolderID);
	
}
