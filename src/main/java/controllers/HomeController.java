package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.LikeModel;
import models.PostModel;
import models.RelationshipModel;
import models.UserModel;
import service.*;
import serviceImp.*;

@WebServlet(urlPatterns = { "/trang-chu", "/home" })
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
		UserModel user = null;
		try {
			user = (UserModel) req.getSession().getAttribute("user_logged");
			if (user == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			resp.sendRedirect("login");
			return;
		}
		req.setAttribute("user", user);
		List<PostModel> list = postservice.getAll();
		List<LikeModel> listLike = new ArrayList<LikeModel>();
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				PostModel postModel = list.get(i);
				postModel.setUser(userservice.getOneByID(postModel.getUserID()));
				postModel.setListLikes(likeservice.getAllByPostID(postModel.getPostID()));
				postModel.setListComments(commentservice.getAllByPostID(postModel.getPostID()));
				postModel.setListImageLink(postservice.findImageLink(postModel.getPostID()));
				LikeModel like = likeservice.getOneByUserPost(user, postModel);
				if (like != null) {
					listLike.add(like);
				} else {
					listLike.add(new LikeModel());
				}
			}
		}
		list = postservice.filterByRelationShip(list, user);
		req.setAttribute("PostsList", list);
		req.setAttribute("listLike", listLike);
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}