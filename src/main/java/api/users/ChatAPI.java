package api.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.MessageModel;
import service.IMessageService;
import serviceImp.MessageServiceImp;
import utils.HttpUtil;

@WebServlet(urlPatterns = {"/api/send-message"})
public class ChatAPI extends HttpServlet {

	IMessageService messageservice = new MessageServiceImp();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("test");
		doPost(req, resp);
	}
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		MessageModel messageModel = HttpUtil.of(req.getReader()).toModel(MessageModel.class);
		messageModel = messageservice.create(messageModel);
		if(messageModel != null)
			mapper.writeValue(resp.getOutputStream(), "Success");
		else
			mapper.writeValue(resp.getOutputStream(), "Failed: null");
	}
}
