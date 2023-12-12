package dao;

import java.util.List;

import models.ConversationModel;

public interface IConversationDao {
	ConversationModel insert(ConversationModel conver);

	List<ConversationModel> getAll();

	ConversationModel update(ConversationModel conver);

	void delete(int conversationID);

	ConversationModel getOneByID(int conversationID);
}
