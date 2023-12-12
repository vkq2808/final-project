package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.ILikeDao;
import models.LikeModel;
import models.PostModel;
import models.UserModel;

public class LikeDaoImp implements ILikeDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public LikeModel create(LikeModel like) {
		try {
			String CreatedAt = LocalDateTime.now().toString();
			String sql = "insert into Likes(UserID,PostID,CreatedAt) OUTPUT INSERTED.LikeID values (?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, like.getPostID());
			ps.setInt(2, like.getPostID());
			ps.setString(3, CreatedAt);
			rs = ps.executeQuery();
			if (rs.next()) {
				like.setLikeID(rs.getInt("LikeID"));
				return like;
			}
			conn.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public List<LikeModel> getAllByPostID(int postID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Likes WHERE PostID = ?");
			ps.setInt(1, postID);
			rs = ps.executeQuery();
			List<LikeModel> list = new ArrayList<LikeModel>();

			while (rs.next()) {
				LikeModel like = new LikeModel(rs.getInt("UserID"), postID,
						rs.getString("CreatedAt"));
				list.add(like);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LikeModel getOneByUserPost(UserModel user, PostModel post) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT top(1) * FROM Likes WHERE PostID = ? and UserID = ?");
			ps.setInt(1, post.getPostID());
			ps.setInt(2, user.getUserID());
			rs = ps.executeQuery();
			LikeModel like = null;

			while (rs.next()) {
				like = new LikeModel(user.getUserID(), post.getPostID(),rs.getString("CreatedAt"));
				like.setLikeID(rs.getInt("LikeID"));
			}
			return like;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public LikeModel delete(LikeModel likemodel) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("DELETE FROM Likes WHERE LikeID = ?");
			ps.setInt(1, likemodel.getLikeID());
			int success = ps.executeUpdate();
			conn.close();
			if(success!=0)
				return likemodel;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
