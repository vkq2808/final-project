package dao;

import java.util.List;

import models.CommentModel;

public interface ICommentDao {

	List<CommentModel> getAllByPostID(int postID);

	CommentModel create(CommentModel comment);

	CommentModel update(CommentModel comment);

	void delete(int commentID);

	CommentModel getOneByID(int commentID);

}
