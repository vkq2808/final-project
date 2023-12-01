package dao;

import java.util.List;

import models.CommentModel;

public interface ICommentDao {

	List<CommentModel> getAllByPostID(int postID);

}
