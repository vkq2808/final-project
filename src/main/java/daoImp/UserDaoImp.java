package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
				user = new UserModel();

				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setNumPhone(rs.getString("NumPhone"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAvatarLink(rs.getString("Avatar"));
				user.setAdmin(rs.getBoolean("isAdmin"));
			}
			conn.close();
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public UserModel create(UserModel user) {
		try {
			String sql = "insert into Users( UserName, Password, NumPhone, Avatar, isAdmin) values (?,?,?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNumPhone());
			ps.setString(4, user.getAvatarLink());
			ps.setBoolean(5, false);
			ps.execute();
			conn.close();
			
			return user;
		} catch (Exception e) {
			System.out.println("Lỗi UserService.create(): \n\t" + e.getMessage());
			;
		}
		return null;
	}

	@Override
	public UserModel update(UserModel user) {
		try {
			String sql = "update Users set UserName = ?, Password = ?, NumPhone = ?, Avatar = ? where UserID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(5, user.getUserID());
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNumPhone());
			ps.setString(4, user.getAvatarLink());
			ps.execute();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi UserService.update(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(int userID) {
		try {
			String sql = "delete from Users where UserID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userID);
			rs = ps.executeQuery();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi UserService.delete(): \n\t" + e.getMessage());
			;
		}
	}

	@Override
	public List<UserModel> getAll() {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Users");
			rs = ps.executeQuery();
			List<UserModel> list = new ArrayList<UserModel>();

			while (rs.next()) {
				UserModel user = new UserModel();

				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setNumPhone(rs.getString("NumPhone"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAvatarLink(rs.getString("Avatar"));
				user.setAdmin(rs.getBoolean("isAdmin"));

				list.add(user);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi UserService.getAll(): \n\t" + e.getMessage());

		}
		return null;
	}

	@Override
	public boolean regist(String username, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserModel findByAccount(String phone) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Users WHERE NumPhone = ?");
			ps.setString(1, phone);
			rs = ps.executeQuery();

			UserModel user = null;

			while (rs.next()) {
				user = new UserModel();

				user.setUserID(rs.getInt("UserID"));
				user.setUserName(rs.getString("UserName"));
				user.setNumPhone(rs.getString("NumPhone"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setAvatarLink(rs.getString("Avatar"));
				user.setAdmin(rs.getBoolean("isAdmin"));
			}

			conn.close();
			return user;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
