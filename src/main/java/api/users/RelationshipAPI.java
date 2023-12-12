package api.users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.RelationshipModel;
import service.IRelationshipService;
import serviceImp.RelationshipServiceImpl;
import utils.HttpUtil;

@WebServlet(urlPatterns = { "/api/relationship" })
public class RelationshipAPI extends HttpServlet {

	private static final long serialVersionUID = -6324646674188139404L;

	IRelationshipService relationshipservice = new RelationshipServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RelationshipModel relationshipModel = HttpUtil.of(req.getReader()).toModel(RelationshipModel.class);
		relationshipModel = relationshipservice.create(relationshipModel);
		if (relationshipModel != null)
			mapper.writeValue(resp.getOutputStream(), relationshipModel.getRelationshipID());
		else
			mapper.writeValue(resp.getOutputStream(), "Failed");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RelationshipModel relationshipModel = HttpUtil.of(req.getReader()).toModel(RelationshipModel.class);

		RelationshipModel isCreated = relationshipservice.getOneByID(relationshipModel.getRelationshipID());
		if (isCreated == null) {
			relationshipModel = relationshipservice.create(relationshipModel);
			System.out.println("test create");
		}
		else {
			relationshipModel = relationshipservice.update(relationshipModel);
			System.out.println("test update");
		}

		if (relationshipModel != null)
			mapper.writeValue(resp.getOutputStream(), relationshipModel.getRelationshipID());
		else
			mapper.writeValue(resp.getOutputStream(), "Failed");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RelationshipModel relationshipModel = HttpUtil.of(req.getReader()).toModel(RelationshipModel.class);
		relationshipservice.delete(relationshipModel.getRelationshipID());
		mapper.writeValue(resp.getOutputStream(), "Success");
	}
}
