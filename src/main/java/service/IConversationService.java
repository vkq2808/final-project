package service;

import java.util.List;

import models.ConversationModel;

public interface IConversationService {
	ConversationModel insert(ConversationModel conver);
	ConversationModel update(ConversationModel conver);
	void delete (int ConversationID);
	ConversationModel getOneByID(int ConversationID);
	List<ConversationModel> getAll();
}
