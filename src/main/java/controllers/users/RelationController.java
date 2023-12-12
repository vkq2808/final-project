package controllers.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.storage.Acl.User;

import models.UserModel;
import service.IRelationshipService;
import serviceImp.RelationshipServiceImpl;

@WebServlet(urlPatterns = { "/friends", "/blocks", "/pending-friend-invitation" })
public class RelationController extends HttpServlet {

	private static final long serialVersionUID = -1054725813114902914L;

	IRelationshipService relationshipService = new RelationshipServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		UserModel user = null;
		String url = req.getRequestURI();

		try {
			user = (UserModel) req.getSession().getAttribute("user_logged");
			if (user == null) {
				throw new Exception("NULL USER");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			resp.sendRedirect("login");
			return;
		}
		List<UserModel> list = null;
		if (url.contains("friends")) {
			
			try{
				int userID = Integer.parseInt(req.getParameter("userID"));
				list = relationshipService.findByFromUserID(userID, true, false, false);
			}catch (Exception e) {
				list = relationshipService.findByFromUserID(user.getUserID(), true, false, false);
			}
			if(list == null)
				list = new ArrayList<UserModel>();
			req.setAttribute("FriendList", list);
			req.getRequestDispatcher("/views/users/listUsers.jsp").forward(req, resp);
		} else if (url.contains("blocks")) {
			list = relationshipService.findByFromUserID(user.getUserID(), false, true, false);
		} else {
			
		}
	}

}
