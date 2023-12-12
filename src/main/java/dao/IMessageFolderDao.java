package dao;

import java.util.List;

import models.MessageFolderModel;
import models.UserModel;

public interface IMessageFolderDao {
	List<MessageFolderModel> findByUserID(int userID);

	MessageFolderModel getOneByID(int messfoID);

	List<UserModel> getAllMember(MessageFolderModel messfo);

	List<MessageFolderModel> getAll();

	void delete(int messfoID);

	MessageFolderModel update(MessageFolderModel messfo);

	MessageFolderModel create(MessageFolderModel messfo);
	int getLatestMessageID(MessageFolderModel messfo);
}
