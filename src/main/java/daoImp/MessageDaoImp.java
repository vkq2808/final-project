package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IMessageDao;
import models.MessageModel;

public class MessageDaoImp implements IMessageDao {
	
	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public MessageModel create(MessageModel mess) {
		try {
			String CreatedAt =LocalDateTime.now().toString();
			String sql = "insert into Messages( SenderID, MessageFolderID, Content, CreatedAt) values (?,?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, mess.getSenderID());
			ps.setInt(2, mess.getMessageFolderID());
			ps.setString(3, mess.getContent());
			ps.setString(4, CreatedAt);
			
			ps.execute();
			conn.close();
			return mess;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public MessageModel update(MessageModel mess) {
		try {
			String UpdatedAt =LocalDateTime.now().toString();
			String sql = "update Messages set SenderID = ?, ReceiverID = ?, Content = ? where MessageID =?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(5, mess.getMessageID());
			ps.setInt(1, mess.getSenderID());
			ps.setInt(2, mess.getMessageFolderID());
			ps.setString(3, mess.getContent());
			ps.setString(4, UpdatedAt);
			
			ps.execute();
			conn.close();
			return mess;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public void delete(int messID) {
		try {
			String sql = "detele from Messages where MessageID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, messID);
			ps.execute();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public MessageModel getOneByID(int messID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Messages where MessageID = ?");
			ps.setInt(1, messID);
			rs = ps.executeQuery();
			MessageModel mess = null;
			while (rs.next()) {
				mess = new MessageModel();
				mess.setMessageID( rs.getInt("MessageID"));
				mess.setSenderID(rs.getInt("SenderID"));
				mess.setMessageFolderID(rs.getInt("MessageFolderID"));
				mess.setContent(rs.getString("Content"));
				mess.setCreatedAt(rs.getString("CreatedAt"));
			}
			conn.close();
			return mess;
		} catch (Exception e) {
			System.out.println("Lỗi MessageService.getOneByID(): \n\t"+e.getMessage());
		}
		return null;
	}

	@Override
	public List<MessageModel> getAllByMessageFolderID(int messageFolderID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Messages where MessageFolderID = ?");
			ps.setInt(1, messageFolderID);
			rs = ps.executeQuery();
			List<MessageModel> list = new ArrayList<MessageModel>();
			while (rs.next()) {
				MessageModel mess = new MessageModel();
				mess.setMessageID( rs.getInt("MessageID"));
				mess.setSenderID(rs.getInt("SenderID"));
				mess.setMessageFolderID(rs.getInt("MessageFolderID"));
				mess.setContent(rs.getString("Content"));
				mess.setCreatedAt(rs.getString("CreatedAt"));
				
				list.add(mess);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi MessageService.getOneByID(): \n\t"+e.getMessage());
		}
		return null;
	}
}
