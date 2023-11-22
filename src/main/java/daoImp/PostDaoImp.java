package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IPostDao;
import models.HomePostUserModel;
import models.PostModel;
import models.UserModel;

public class PostDaoImp implements IPostDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public void insert(int uid, String content, String imageLink) throws Exception {
		
		String sql = "select top (1) from Post";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			int nextPostID;
			int curPostID = rs.getInt("PostID"); 
			
			if( curPostID == 0) {
				nextPostID = 2808;
			}
			else {
				nextPostID = curPostID + 1;
			}
			
			sql = String.format("insert into Post value (%s,\"%s\",%s\",%s\")",nextPostID,uid,content,imageLink);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			conn.close();
		}
		catch (Exception e) {
			if (!conn.isClosed()) {
				conn.close();
			}
			System.out.println(e.getMessage());
		}
	}

	@Override
	public List<HomePostUserModel> getAll() {
		String sql = "select * from Post, Users where Post.UserID = Users.UserID";
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			List<HomePostUserModel> list = new ArrayList<HomePostUserModel>();
			while (rs.next()) {
				
				HomePostUserModel PostUser = new HomePostUserModel();
				PostModel post = PostUser.getPost();
				UserModel user = PostUser.getUser();
				
				post.setPostID(rs.getInt("PostID"));
				post.setContent(rs.getString("Content"));
				post.setImageLink(rs.getString("Image"));
				
				user.setUserName(rs.getString("UserName"));
				
				PostUser.setPost(post);
				PostUser.setUser(user);
				
				list.add(PostUser);
//				System.out.println(user.getUserName());
			}
			conn.close();
			return list;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}
