package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.ILikeDao;
import models.LikeModel;

public class LikeDaoImp implements ILikeDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;
	
	@Override
	public void create(int uid, int pid) {
		try {
			String CreatedAt =LocalDateTime.now().toString();
			String sql = String.format("insert into Likes(UserID,PostID,CreatedAt) values (%s,%s,'%s')",uid,pid,CreatedAt);
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			conn.close();
		}
		catch (Exception e) {
			System.out.println(e);
		}
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
				LikeModel like = new LikeModel(
						rs.getInt("UserID"),
						postID,
						LocalDateTime.parse(rs.getString("CreatedAt").subSequence(0, 19),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
						);
				list.add(like);
			}
			return list;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
