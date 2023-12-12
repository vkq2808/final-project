package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.ICommentDao;
import models.CommentModel;

public class CommentDaoImp implements ICommentDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public List<CommentModel> getAllByPostID(int postID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Comment where PostID = ?");
			ps.setInt(1, postID);
			rs = ps.executeQuery();
			List<CommentModel> list = new ArrayList<CommentModel>();
			while (rs.next()) {
				CommentModel com = new CommentModel(
						rs.getInt("CommentID"),
						rs.getInt("UserID"),
						rs.getInt("PostID"),
						rs.getString("Content"),
						rs.getString("CreatedAt")
						);
				list.add(com);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Loi CommentService.GetAllByPostID()");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public CommentModel create(CommentModel comment) {
		try {
			String CreatedAt =LocalDateTime.now().toString();
			String sql = "insert into Comment(UserID,PostID,Content,CreatedAt) values (?,?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getUserID());
			ps.setInt(2, comment.getPostID());
			ps.setString(3, comment.getContent());
			ps.setString(4, CreatedAt);
			
			ps.execute();
			conn.close();
			return comment;
		}
		catch (Exception e) {
			System.out.println("Loi trong CommentService.create()");
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CommentModel update(CommentModel comment) {
		try {
			String UpdatedAt =LocalDateTime.now().toString();
			String sql = "UPDATE Comment SET UserID = ?, PostID = ?, Content = ?, UpdatedAt = ? WHERE CommentID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, comment.getUserID());
			ps.setInt(2, comment.getPostID());
			ps.setString(3, comment.getContent());
			ps.setString(4, UpdatedAt);
			ps.setInt(5, comment.getCommentID());
			
			ps.execute();
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Loi trong CommentService.update()");
			e.printStackTrace();
			return null;
		}
		return comment;
	}
	@Override
	public void delete(int commentID) {
		try {
			String sql = "DELETE Comment WHERE CommentID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, commentID);
			ps.execute();
			
			conn.close();
		}
		catch (Exception e) {
			System.out.println("Loi trong CommentService.delete()");
			e.printStackTrace();
		}
	}

	@Override
	public CommentModel getOneByID(int commentID) {
		CommentModel com = null;
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Comment where CommentID = ?");
			ps.setInt(1, commentID);
			rs = ps.executeQuery();
			while (rs.next()) {
				com = new CommentModel(
						rs.getInt("CommentID"),
						rs.getInt("UserID"),
						rs.getInt("PostID"),
						rs.getString("Content"),
						rs.getString("CreatedAt")
						);
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Loi CommentService.GetOneByID()");
			e.printStackTrace();
		}
		return com;
	}
}
