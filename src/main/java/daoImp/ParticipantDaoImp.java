package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IParticipantDao;
import models.ParticipantModel;

public class ParticipantDaoImp implements IParticipantDao{

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public ParticipantModel create(ParticipantModel par) {
		try {
			String sql = "insert into Participants(MessageFolderID, UserID) values (?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, par.getMessageFolderID());
			ps.setInt(2, par.getUserID());
			ps.execute();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi ParticipantService.create(): \n\t" + e.getMessage());
			;
		}
		return null;
	}

	@Override
	public ParticipantModel update(ParticipantModel par) {
		try {
			String sql = "update Participants set MessageFolderID = ?, UserID = ? where ParID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(3, par.getParID());
			ps.setInt(1, par.getMessageFolderID());
			ps.setInt(2, par.getUserID());
			ps.execute();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi ParticipantService.update(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(int parID) {
		try {
			String sql = "delete from Participants where ParID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, parID);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi ParticipantService.delete(): \n\t" + e.getMessage());
		}
	}

	@Override
	public List<ParticipantModel> getAll() {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Participants");
			rs = ps.executeQuery();
			List<ParticipantModel> list = new ArrayList<ParticipantModel>();

			while (rs.next()) {
				ParticipantModel par = new ParticipantModel();

				par.setParID(rs.getInt("ParID"));
				par.setMessageFolderID(rs.getInt("ConverID"));
				par.setUserID(rs.getInt("UserID"));

				list.add(par);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi ParticipantService.getAll(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public ParticipantModel getOneByID(int parID) {
		try {
		conn = new DBConnection().getConnection();
		ps = conn.prepareStatement("select * from Participants where parID = ?");
		ps.setInt(1, parID);
		rs = ps.executeQuery();
		ParticipantModel par = null;
		while (rs.next()) {
			par = new ParticipantModel();

			par.setParID(rs.getInt("ParID"));
			par.setMessageFolderID(rs.getInt("ConverID"));
			par.setUserID(rs.getInt("UserID"));
		}
		conn.close();
		return par;
	} catch (Exception e) {
		System.out.println("Lỗi ParticipantService.getOneByID(): \n\t" + e.getMessage());
	}
	return null;
	}
}
