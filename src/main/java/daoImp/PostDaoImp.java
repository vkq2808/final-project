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
	public void insert(int uid, String content, String imageLink) throws Exception {
		
		try {
			conn = new DBConnection().getConnection();
			String sql = String.format("insert into Post(UserID,Content,CreatedAt,UpdatedAt) values (%s,\"%s\",%s\",%s\")",uid,content,LocalDateTime.now().toString(),"NULL");
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			conn.close();
		}
		catch (Exception e) {
			if (!conn.isClosed()) {
				conn.close();
			}
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
					PostModel post = new PostModel(
							rs.getInt("PostID"),
							rs.getInt("UserID"),
							rs.getString("Content"),	
							LocalDateTime.parse(rs.getString("CreatedAt").subSequence(0, 19),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
							);
					list.add(post);
				}
				conn.close();
				return list;
			}
			catch (Exception e) {
				System.out.println("Lỗi PostService.getAll(): \n\t"+e.getMessage());
				
			}
		return null;
	}

	@Override
	public List<String> findImageLink(int postID) {
		List<String> list = new ArrayList<String>();
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM PostImage WHERE PostID = ?");
			ps.setInt(1, postID);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("ImageLink"));
			}
		}
		catch (Exception e) {
			System.out.println("Lỗi PostService.findImageLink(): \n\t"+e.getMessage());
		}
		return list;
	}

}
