package service;

import java.util.List;

import models.CommentModel;

public interface ICommentService {
	CommentModel create (CommentModel comment);
	CommentModel update (CommentModel comment);
	void delete (int commentID);
	CommentModel getOneByID(int commentID);
	List<CommentModel> getAllByPostID(int PostID);
}
