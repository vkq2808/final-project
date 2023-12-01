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
}
