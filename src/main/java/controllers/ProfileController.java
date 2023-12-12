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
import service.ICommentService;
import service.ILikeService;
import service.IPostService;
import service.IRelationshipService;
import service.IUserService;
import serviceImp.CommentServiceImp;
import serviceImp.LikeServiceImp;
import serviceImp.PostServiceImp;
import serviceImp.RelationshipServiceImpl;
import serviceImp.UserServiceImp;

@WebServlet(urlPatterns = { "/profile" })
public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = -6957775481722745584L;

	IPostService postservice = new PostServiceImp();
	IUserService userservice = new UserServiceImp();
	ICommentService commentservice = new CommentServiceImp();
	ILikeService likeservice = new LikeServiceImp();
	IRelationshipService relationshipservice = new RelationshipServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		UserModel user = null;
		UserModel profileUser = null;
		String profileID = req.getParameter("profileID");
		RelationshipModel relation = new RelationshipModel();

		try {
			user = (UserModel) req.getSession().getAttribute("user_logged");
			if (user == null) {
				throw new Exception("NULL USER");
			}
			if (profileID != null) {
				profileUser = userservice.getOneByID(Integer.parseInt(profileID));
			} else {
				profileUser = user;
			}
		} catch (Exception e) {
			resp.sendRedirect("login");
			return;
		}

		RelationshipModel op_relation = relationshipservice.findBy2User(profileUser, user);
		if (op_relation.isBlocking()) {
			resp.sendRedirect("you-have-been-blocked-from-this-user-lets-try-to-make-them-happy");
			return;
		}

		List<PostModel> list = postservice.getAllByUser(profileUser);

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
		relation = relationshipservice.findBy2User(user, profileUser);

		req.setAttribute("listLike", listLike);
		req.setAttribute("op_relation", op_relation);
		req.setAttribute("relation", relation);
		req.setAttribute("PostsList", list);
		req.setAttribute("profile", profileUser);
		req.getRequestDispatcher("/views/users/profile/right.jsp").forward(req, resp);
	}
}
