package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IMessageFolderDao;
import models.MessageFolderModel;
import models.UserModel;
import daoImp.UserDaoImp;

public class MessageFolderDaoImp implements IMessageFolderDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public MessageFolderModel create(MessageFolderModel messfo) {
		try {
			String sql = "insert into Messagefolder(MessageFolderName, MessageFolderAvatarLink, LatestMessageID) OUTPUT INSERTED.MessageFolderID values (?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, messfo.getMessageFolderName());
			ps.setString(2, messfo.getMessageFolderAvatarLink());
			ps.setInt(3, messfo.getLatestMessageID());
			rs = ps.executeQuery();
			if(rs.next()) {
				messfo.setMessageFolderID(rs.getInt("MessageFolderID"));
				return messfo;
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi MessageFolderService.create(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public MessageFolderModel update(MessageFolderModel messfo) {
		try {
			String sql = "update Messagefolder set MessageFolderName = ?, MessageFolderAvatarLink = ?, LatestMessageID = ? where MessageFolderID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(4, messfo.getMessageFolderID());
			ps.setString(1, messfo.getMessageFolderName());
			ps.setString(2, messfo.getMessageFolderAvatarLink());
			ps.setInt(3, messfo.getLatestMessageID());
			ps.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi MessageFolderService.update(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(int messfoID) {
		try {
			String sql = "delete from Messfolder where MessFoID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messfoID);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi MessageFolderService.delete(): \n\t" + e.getMessage());
		}
	}

	@Override
	public List<MessageFolderModel> getAll() {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Messfolder");
			rs = ps.executeQuery();
			List<MessageFolderModel> list = new ArrayList<MessageFolderModel>();

			while (rs.next()) {
				MessageFolderModel messfo = new MessageFolderModel();

				messfo.setMessageFolderID(rs.getInt("MessageFolderID"));
				messfo.setMessageFolderName(rs.getString("MessageFolderName"));
				messfo.setMessageFolderAvatarLink(rs.getString("MessageFolderAvatarLink"));
				messfo.setLatestMessageID(rs.getInt("LatestMessageID"));

				list.add(messfo);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi MessageFolderService.getAll(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public MessageFolderModel getOneByID(int messfoID) {
		MessageFolderModel messfo = new MessageFolderModel();
		try {

			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from MessageFolder where MessageFolderID = ?");
			ps.setInt(1, messfoID);
			rs = ps.executeQuery();

			if (rs.next()) {
				messfo.setMessageFolderID(messfoID);
				messfo.setMessageFolderAvatarLink(rs.getString("MessageFolderAvatarLink"));
				messfo.setLatestMessageID(rs.getInt("LatestMessageID"));
			}
		} catch (Exception e) {
			messfo = null;
			e.printStackTrace();
		}
		return messfo;
	}

	@Override
	public List<MessageFolderModel> findByUserID(int userID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select MessageFolderID from Participants where UserID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			List<MessageFolderModel> list = new ArrayList<MessageFolderModel>();
			while (rs.next()) {
				
				MessageFolderModel messfo = new MessageFolderDaoImp().getOneByID(rs.getInt("MessageFolderID"));
				list.add(messfo);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserModel> getAllMember(MessageFolderModel messfo) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select UserID from Participants where MessageFolderID = ?");
			ps.setInt(1, messfo.getMessageFolderID());
			rs = ps.executeQuery();
			List<UserModel> list = new ArrayList<UserModel>();
			while (rs.next()) {
				UserModel u = new UserDaoImp().getOneByID(rs.getInt("UserID"));
				list.add(u);
			}
			conn.close();
			return list;
		} catch (

		Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getLatestMessageID(MessageFolderModel messfo) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select top(1) * from Messages where MessageFolderID = ? order by CreatedAt desc");
			ps.setInt(1, messfo.getMessageFolderID());
			rs = ps.executeQuery();
			int messid = 0;
			while (rs.next()) {
				messid = rs.getInt("MessageID");
			}
			conn.close();
			return messid;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
