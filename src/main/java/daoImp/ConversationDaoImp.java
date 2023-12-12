package daoImp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import connection.DBConnection;
import dao.IConversationDao;
import models.ConversationModel;

public class ConversationDaoImp implements IConversationDao {

	
	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
//	int ConversationID;
//	int CallerID;
//	int ReceiverID;
//	ZonedDateTime CreateAt;
//	ZonedDateTime UpdateAt;
//	ZonedDateTime DeleteAt;
	@Override
	public ConversationModel insert(ConversationModel conver) {
		try {
			String sql = "insert into Conversations values CallerID=?,ReceiverID=?, CreateAt=? ";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, conver.getCallerID());
			ps.setInt(2, conver.getReceiverID());
			ps.setString(3, LocalDateTime.now().toString());
			rs = ps.executeQuery();
			conn.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return conver;
	}


	@Override
	public ConversationModel update(ConversationModel conver) {
		try {
			String sql = "update Message set CallerI = ?, ReceiverID = ?, UpdateAt = ? where ConversationID =?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, conver.getCallerID());
			ps.setInt(2, conver.getReceiverID());
			ps.setString(3, LocalDateTime.now().toString());
			rs = ps.executeQuery();
			conn.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return conver;
	}

	@Override
	public void delete(int conversationID) {
		try {
			String sql = "detele from Conversations where ConversationID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, conversationID);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

	@Override
	public ConversationModel getOneByID(int conversationID) {
		String sql = "SELECT * FROM Conversations where ConverID=?";
		ConversationModel conver = new ConversationModel();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, conversationID);
			rs = ps.executeQuery();
			while (rs.next()) {
				conver.setConversationID(rs.getInt("ConversationID"));
				conver.setCallerID(rs.getInt("CallerID"));
				conver.setReceiverID(rs.getInt("ReceiverID"));
				LocalDateTime.parse(
					    rs.getString("CreatedAt").subSequence(0, 19),
					    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
					    );

			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi ConversationService.getOne(): \n\t"+e.getMessage());
		}
		return conver;
	}

	@Override
	public List<ConversationModel> getAll() {
		List<ConversationModel> listconver = new ArrayList<>();
		String sql = "SELECT * FROM Conversations";

		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ConversationModel conver = new ConversationModel();
				conver.setConversationID(rs.getInt("ConverID"));
				conver.setCallerID(rs.getInt("CallerID"));
				conver.setReceiverID(rs.getInt("ReceiverID"));
				LocalDateTime.parse(
					    rs.getString("CreatedAt").subSequence(0, 19),
					    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
					);
		listconver.add(conver);
			}

		} catch (Exception e) {
			System.out.println("Lỗi ConversationService.getAll(): \n\t"+e.getMessage());
		}

		return listconver;
	}
}
