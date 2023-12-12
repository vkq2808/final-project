package api.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.*;
import service.*;
import serviceImp.*;
import utils.HttpUtil;

@WebServlet(urlPatterns = { "/api/send-comment", "/api/send-like" })
public class PostAPI extends HttpServlet {

	private static final long serialVersionUID = 4616566188919352745L;

	ICommentService commentservice = new CommentServiceImp();
	ILikeService likeservice = new LikeServiceImp();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		String url = req.getRequestURI();

		if (url.contains("send-comment")) {
			CommentModel commentModel = HttpUtil.of(req.getReader()).toModel(CommentModel.class);
			commentModel = commentservice.create(commentModel);
			if (commentModel != null)
				mapper.writeValue(resp.getOutputStream(), "Success");
			else
				mapper.writeValue(resp.getOutputStream(), "Failed: null");
		} else if (url.contains("send-like")) {
			LikeModel likemodel = HttpUtil.of(req.getReader()).toModel(LikeModel.class);
			likemodel = likeservice.create(likemodel);
			if (likemodel != null)
				mapper.writeValue(resp.getOutputStream(), likemodel.getLikeID());
			else
				mapper.writeValue(resp.getOutputStream(), "Failed: null");
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		LikeModel likemodel = HttpUtil.of(req.getReader()).toModel(LikeModel.class);
		likemodel = likeservice.delete(likemodel);
		if(likemodel != null)
			mapper.writeValue(resp.getOutputStream(), "Success");
		else
			mapper.writeValue(resp.getOutputStream(), "Failed");
	}


}
