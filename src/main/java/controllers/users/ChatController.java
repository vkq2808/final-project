package controllers.users;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoImp.ParticipantDaoImp;
import models.MessageFolderModel;
import models.MessageModel;
import models.ParticipantModel;
import models.UserModel;
import service.IMessageFolderService;
import service.IMessageService;
import serviceImp.MessageFolderServiceImp;
import serviceImp.MessageServiceImp;
import serviceImp.UserServiceImp;

@WebServlet(urlPatterns = { "/chat" })
public class ChatController extends HttpServlet {

	private static final long serialVersionUID = 5122301399376182537L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		IMessageFolderService messFoService = new MessageFolderServiceImp();
		IMessageService messageService = new MessageServiceImp();

		// Lấy ID của người dùng và cuộc trò chuyện
		UserModel user = null;
		String MessageFolderID = "";
		String String_ToUserID = "";
		int ToUserID = 0;
		boolean isValidMessageFolderID = false;
		int messfoID;
		try {
			MessageFolderID = req.getParameter("messfo");
			String_ToUserID = req.getParameter("toUserID");
		} catch (Exception e) {
		}
		try {
			messfoID = Integer.parseInt(MessageFolderID);

		} catch (Exception e) {
			messfoID = 0;
		}
		try {
			ToUserID = Integer.parseInt(String_ToUserID);
		} catch (Exception e) {
			ToUserID = 0;
		}
		// Nếu không có người dùng thì chuyển sang trang sign-in
		try {
			user = (UserModel) req.getSession().getAttribute("user_logged");
			if (user == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			resp.sendRedirect("login");
			return;
		}
		UserModel toUser = null;
		List<MessageFolderModel> listmessfo = messFoService.findByUserID(user.getUserID());
		for (MessageFolderModel mf : listmessfo) {
			if (mf != null) {
				if (mf.getMessageFolderID() == messfoID) {
					isValidMessageFolderID = true;
				}
				List<UserModel> listuser = messFoService.getAllMember(mf);
				for (UserModel um : listuser) {
					if (um.getUserID() == ToUserID) {
						isValidMessageFolderID = true;
						MessageFolderID = String.valueOf(mf.getMessageFolderID());
					}
				}
				if (listuser.size() == 2) {
					if (mf.getMessageFolderName() == null) {
						if (listuser.get(0).getUserID() == user.getUserID()) {
							mf.setMessageFolderName(listuser.get(1).getUserName());
						} else {
							mf.setMessageFolderName(listuser.get(0).getUserName());
						}
					}
					if (mf.getMessageFolderAvatarLink() == null) {
						if (listuser.get(0).getUserID() == user.getUserID()) {
							mf.setMessageFolderAvatarLink(listuser.get(1).getAvatarLink());
						} else {
							mf.setMessageFolderAvatarLink(listuser.get(0).getAvatarLink());
						}
					}
				}
			}
		}
		if (ToUserID != 0) {
			toUser = new UserServiceImp().getOneByID(ToUserID);
			System.out.println(toUser.getUserName());
			MessageFolderModel newMessageFolder = messFoService.create(new MessageFolderModel());

			MessageFolderID = String.valueOf(newMessageFolder.getMessageFolderID());
			ParticipantModel participants = new ParticipantModel();
			participants.setMessageFolderID(newMessageFolder.getMessageFolderID());
			participants.setUserID(toUser.getUserID());
			new ParticipantDaoImp().create(participants);
			participants.setUserID(user.getUserID());
			new ParticipantDaoImp().create(participants);
			resp.sendRedirect("/FinalProject/chat?messfo=" + newMessageFolder.getMessageFolderID());
			return;
		}
		req.setAttribute("listmessfo", listmessfo);
		if (!isValidMessageFolderID) {
			req.getRequestDispatcher("/views/users/chat.jsp").forward(req, resp);
		} else {
			try {
				List<MessageModel> listmess = messageService.getAllByMessageFolderID(Integer.parseInt(MessageFolderID));
				if (listmess == null) {
					listmess = new ArrayList<MessageModel>();
				}
				req.setAttribute("listmsg", listmess);
				req.setAttribute("hasMessFo", "true");
			} catch (Exception e) {
			}
			req.setAttribute("user", user);
			req.setAttribute("messfo", MessageFolderID);
			req.getRequestDispatcher("/views/users/chat.jsp").forward(req, resp);
		}
	}
}
