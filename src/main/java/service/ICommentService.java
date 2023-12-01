package service;

import java.util.List;

import models.CommentModel;

public interface ICommentService {
//	void create ();
//	void update ();
//	void delete ();
//	getOneByID();
	List<CommentModel> getAllByPostID(int PostID);
}
