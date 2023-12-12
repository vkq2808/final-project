package daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import dao.IRelationshipDao;
import models.RelationshipModel;

public class RelationshipDaoImpl implements IRelationshipDao {

	Connection conn = null;
	PreparedStatement ps;
	ResultSet rs;

	@Override
	public RelationshipModel create(RelationshipModel relate) {
		try {
			String sql = "insert into Relationship( FromUserID, ToUserID,isFriend,isBlocking, isPending) OUTPUT INSERTED.RelationshipID values (?,?,?,?,?)";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, relate.getFromUserID());
			ps.setInt(2, relate.getToUserID());
			ps.setBoolean(3, relate.isFriend());
			ps.setBoolean(4, relate.isBlocking());
			ps.setBoolean(5, relate.isPending());
			rs = ps.executeQuery();
			
			if(rs.next()) {
				relate.setRelationshipID(rs.getInt("RelationshipID"));
			}
			conn.close();
			return relate;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.create(): \n\t" + e.getMessage());
			return null;
		}
	}

	@Override
	public RelationshipModel update(RelationshipModel relate) {
		try {
			String sql = "update Relationship set FromUserID = ?, ToUserID = ?, isFriend = ?, isPending = ?, isBlocking =? where RelationshipID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(6, relate.getRelationshipID());
			ps.setInt(1, relate.getFromUserID());
			ps.setInt(2, relate.getToUserID());
			ps.setBoolean(3, relate.isFriend());
			ps.setBoolean(4, relate.isPending());
			ps.setBoolean(5, relate.isBlocking());
			
			int success = ps.executeUpdate();
			conn.close();
			if (success!=0)
				return relate;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.update(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public void delete(int relationID) {
		try {
			String sql = "delete from Relationship where RelationshipID = ?";
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, relationID);
			ps.execute();
			conn.close();
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.delete(): \n\t" + e.getMessage());
			;
		}
	}

	@Override
	public List<RelationshipModel> getAll() {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Relationship");
			rs = ps.executeQuery();
			List<RelationshipModel> list = new ArrayList<RelationshipModel>();

			while (rs.next()) {
				RelationshipModel relate = new RelationshipModel();

				relate.setRelationshipID(rs.getInt("RelationshipID"));
				relate.setFromUserID(rs.getInt("FromUserID"));
				relate.setToUserID(rs.getInt("ToUserID"));
				relate.setFriend(rs.getBoolean("IsFriend"));
				relate.setBlocking(rs.getBoolean("IsBlocking"));
				relate.setPending(rs.getBoolean("IsPending"));

				list.add(relate);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.getAll(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public RelationshipModel getOneByID(int relateID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("select * from Relationship where RelationshipID = ?");
			ps.setInt(1, relateID);
			rs = ps.executeQuery();
			RelationshipModel relate = null;
			while (rs.next()) {
				relate = new RelationshipModel();

				relate.setRelationshipID(rs.getInt("RelationshipID"));
				relate.setFromUserID(rs.getInt("FromUserID"));
				relate.setToUserID(rs.getInt("ToUserID"));
				relate.setFriend(rs.getBoolean("IsFriend"));
				relate.setBlocking(rs.getBoolean("IsBlocking"));
				relate.setPending(rs.getBoolean("IsPending"));
			}
			conn.close();
			return relate;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.getOneByID(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<RelationshipModel> findByFromUserID(int fromUserID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Relationship WHERE FromUserID = ?");
			ps.setInt(1, fromUserID);
			rs = ps.executeQuery();
			List<RelationshipModel> list = new ArrayList<RelationshipModel>();

			while (rs.next()) {
				RelationshipModel relate = new RelationshipModel();

				relate.setRelationshipID(rs.getInt("RelationshipID"));
				relate.setFromUserID(rs.getInt("FromUserID"));
				relate.setToUserID(rs.getInt("ToUserID"));
				relate.setFriend(rs.getBoolean("IsFriend"));
				relate.setBlocking(rs.getBoolean("IsBlocking"));
				relate.setPending(rs.getBoolean("IsPending"));

				list.add(relate);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.findByFromUserID(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public RelationshipModel findBy2UserID(int fromUserID, int toUserID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Relationship WHERE FromUserID = ? AND ToUserID = ?");
			ps.setInt(1, fromUserID);
			ps.setInt(2, toUserID);
			rs = ps.executeQuery();

			RelationshipModel relate = null;

			while (rs.next()) {
				relate = new RelationshipModel();
				relate.setRelationshipID(rs.getInt("RelationshipID"));
				relate.setFromUserID(fromUserID);
				relate.setToUserID(toUserID);
				relate.setFriend(rs.getBoolean("IsFriend"));
				relate.setBlocking(rs.getBoolean("IsBlocking"));
				relate.setPending(rs.getBoolean("IsPending"));
			}

			conn.close();
			return relate;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.findByFromUserID(): \n\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<RelationshipModel> findByToUserID(int toUserID) {
		try {
			conn = new DBConnection().getConnection();
			ps = conn.prepareStatement("SELECT * FROM Relationship WHERE ToUserID = ?");
			ps.setInt(1, toUserID);
			rs = ps.executeQuery();
			List<RelationshipModel> list = new ArrayList<RelationshipModel>();

			while (rs.next()) {
				RelationshipModel relate = new RelationshipModel();

				relate.setRelationshipID(rs.getInt("RelationshipID"));
				relate.setFromUserID(rs.getInt("FromUserID"));
				relate.setToUserID(rs.getInt("ToUserID"));
				relate.setFriend(rs.getBoolean("IsFriend"));
				relate.setBlocking(rs.getBoolean("IsBlocking"));
				relate.setPending(rs.getBoolean("IsPending"));

				list.add(relate);
			}
			conn.close();
			return list;
		} catch (Exception e) {
			System.out.println("Lỗi RelationshipService.findByFromUserID(): \n\t" + e.getMessage());
		}
		return null;
	}

}
