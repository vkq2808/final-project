package serviceImp;

import java.util.List;

import dao.ICommentDao;
import daoImp.CommentDaoImp;
import models.CommentModel;
import service.ICommentService;

public class CommentServiceImp implements ICommentService {

	ICommentDao commentDao = new CommentDaoImp();
	
	@Override
	public List<CommentModel> getAllByPostID(int PostID) {
		return commentDao.getAllByPostID(PostID);
	}

	@Override
	public CommentModel create(CommentModel comment) {
		return commentDao.create(comment);
	}

	@Override
	public CommentModel update(CommentModel comment) {
		return commentDao.update(comment);
	}

	@Override
	public void delete(int commentID) {
		commentDao.delete(commentID);
	}

	@Override
	public CommentModel getOneByID(int commentID) {
		return commentDao.getOneByID(commentID);
	}
}
