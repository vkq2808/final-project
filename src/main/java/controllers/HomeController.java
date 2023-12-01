package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PostModel;
import service.*;
import serviceImp.*;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -5092581875966576535L;
	
	IPostService postservice = new PostServiceImp();
	IUserService userservice = new UserServiceImp();
	ICommentService commentservice = new CommentServiceImp();
	ILikeService likeservice = new LikeServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		List<PostModel> list = postservice.getAll();
		
		for (PostModel postModel : list) {
			postModel.setUser(userservice.getOneByID(postModel.getUserID()));
			postModel.setListLikes(likeservice.getAllByPostID(postModel.getPostID()));
			postModel.setListComments(commentservice.getAllByPostID(postModel.getPostID()));
			postModel.setListImageLink(postservice.findImageLink(postModel.getPostID()));
		}
		req.setAttribute("PostsList", list);
		
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}