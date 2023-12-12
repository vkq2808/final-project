package controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.UserModel;
import service.IUserService;
import serviceImp.UserServiceImp;

@WebServlet(urlPatterns = { "/login", "/sign-up", "/sign-out" })
public class LoginAndRegist extends HttpServlet {

	private static final long serialVersionUID = -3591195820058714336L;
	IUserService userservice = new UserServiceImp();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String url = req.getRequestURL().toString();
		if (url.contains("login")) {
			req.setAttribute("isSignin", "checked");
			req.setAttribute("isSignup", "");
			req.getRequestDispatcher("/views/users/sign-in.jsp").forward(req, resp);
			
		}
		else if (url.contains("sign-up")) {
			req.setAttribute("isSignin", "");
			req.setAttribute("isSignup", "checked");
			req.getRequestDispatcher("/views/users/sign-in.jsp").forward(req, resp);
		}
		else {
			HttpSession session = req.getSession();
			req.removeAttribute("isSignin");
			req.removeAttribute("isSignup");
			session.removeAttribute("username");
			session.removeAttribute("user_logged");
			resp.sendRedirect("trang-chu");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");
		
		String username = req.getParameter("username");
		String repeat_password = req.getParameter("repeat-password"); 
		
		if (repeat_password == null)
			login(req, resp, phone, password);
		else
			regist(req, resp, phone,username, password);
	}

	protected void login(HttpServletRequest req, HttpServletResponse resp, String phone, String password)
			throws ServletException, IOException {
		UserModel user = userservice.login(phone, password);
		if (user != null) {
			HttpSession session = req.getSession();
			session.setAttribute("user_logged", user);
			session.setAttribute("username", user.getUserName());
			resp.sendRedirect("trang-chu");
		} else {
			req.setAttribute("message", "Đăng nhập thất bại");
			req.setAttribute("isSignin", "checked");
			req.getRequestDispatcher("/views/users/sign-in.jsp").forward(req, resp);
		}
	}

	protected void regist(HttpServletRequest req, HttpServletResponse resp, String phone, String username,
			String password) throws ServletException, IOException {
		UserModel user = new UserModel();
		
		user.setNumPhone(phone);
		user.setUserName(username);
		user.setPassword(password);
		
		user = userservice.create(user);
		if (user!= null) {
			req.setAttribute("message", "Đăng ký thành công");
			req.setAttribute("isSignin", "checked");
			req.getRequestDispatcher("/views/users/sign-in.jsp").forward(req, resp);
		} else {
			req.setAttribute("isSignup", "checked");
			req.setAttribute("message", "Đăng ký thất bại");
			req.getRequestDispatcher("/views/users/sign-in.jsp").forward(req, resp);
		}
	}
}
