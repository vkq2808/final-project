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
						LocalDateTime.parse(rs.getString("CreatedAt").subSequence(0, 19),DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
						);
				list.add(com);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
