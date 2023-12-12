package service;

import java.util.List;

import models.MessageFolderModel;
import models.UserModel;

public interface IMessageFolderService {
	MessageFolderModel create(MessageFolderModel messfo);

	MessageFolderModel update(MessageFolderModel messfo);

	void delete(int messfoID);

	List<MessageFolderModel> getAll();

	MessageFolderModel getOneByID(int messfoID);

	List<MessageFolderModel> findByUserID(int userID);

	List<UserModel> getAllMember(MessageFolderModel messfo);
}
