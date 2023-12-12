package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IPostDao;
import models.PostModel;
import models.UserModel;

public class PostDaoImp implements IPostDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public void insert(int uid, String content, String imageLink) {

		try {
			conn = new DBConnection().getConnection();
			String sql = String.format(
					"insert into Post(UserID,Content,CreatedAt,UpdatedAt) values (%s,\"%s\",%s\",%s\")", uid, content,
					LocalDateTime.now().toString(), "NULL");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public List<PostModel> getAll() {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Post");
			rs = ps.executeQuery();
			List<PostModel> list = new ArrayList<PostModel>();

			while (rs.next()) {
				PostModel post = new PostModel(rs.getInt("PostID"), rs.getInt("UserID"), rs.getString("Content"),
						rs.getString("CreatedAt"));
				list.add(post);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi PostService.getAll(): \n\t" + e.getMessage());

		}
		return null;
	}

	@Override
	public List<String> findImageLink(int postID) {
		List<String> list = new ArrayList<String>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM ImageLink WHERE PostID = ?");
			ps.setInt(1, postID);
			rs = ps.executeQuery();

			while (rs.next()) {
				list.add(rs.getString("ImageLink"));
			}
		} catch (Exception e) {
			System.out.println("Lỗi PostService.findImageLink(): \n\t" + e.getMessage());
		}
		return list;
	}

	@Override
	public PostModel update(PostModel post) {
		try {
			String sql = "update Message set UserID = ?, Content = ?, ImageLink = ? where PostID =?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, post.getUserID());
			ps.setString(2, post.getContent());
//			ps.setString(3, post.getListImageLink());
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return post;

	}

	@Override
	public void delete(int postID) {
		try {
			String sql = "detele from Message where postID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, postID);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	@Override
	public PostModel getOneByID(int PostID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Post where PostID = ?");
			ps.setInt(1, PostID);
			rs = ps.executeQuery();
			PostModel post = null;
			while (rs.next()) {
				post = new PostModel(rs.getInt("PostID"), rs.getInt("userID"), rs.getString("Content"),
						rs.getString("UpdateAT"));
			}
			conn.close();
			return post;
		} catch (Exception e) {
			System.out.println("Lỗi PostService.getOneByID(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<PostModel> getAllByUser(UserModel user) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Post WHERE UserID = ?");
			ps.setInt(1, user.getUserID());
			rs = ps.executeQuery();
			List<PostModel> list = new ArrayList<PostModel>();

			while (rs.next()) {
				PostModel post = new PostModel(rs.getInt("PostID"), rs.getInt("UserID"), rs.getString("Content"),
						rs.getString("CreatedAt"));
				list.add(post);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi PostService.getAll(): \n\t" + e.getMessage());

		}
		return null;
	}

}
