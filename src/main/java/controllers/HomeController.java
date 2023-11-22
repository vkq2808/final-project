package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.HomePostUserModel;
import service.IPostService;
import serviceImp.PostServiceImp;

@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = -5092581875966576535L;
	
	IPostService ips = new PostServiceImp();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		List<HomePostUserModel> list = ips.getAll();
		System.out.println("test");
		
		req.setAttribute("PostsList", list);
		req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
	}
}