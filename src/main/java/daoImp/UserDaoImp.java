package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import connection.DBConnection;
import dao.IUserDao;
import models.UserModel;

public class UserDaoImp implements IUserDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public UserModel getOneByID(int userID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Users where UserID = ?");
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			UserModel user = null;
			while (rs.next()) {
				user = new UserModel(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("NumPhone"),
						rs.getString("Email"), rs.getString("Avatar"));
			}
			conn.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
