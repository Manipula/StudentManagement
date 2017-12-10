package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.fuzhu.studentmanager.JDBCTools;

public class UserDAOImpl implements IUserDao {
	private Connection conn = null;
	private PreparedStatement preparedStatement = null;

	public UserDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public boolean findLogin(Userstudent user) throws Exception {
		boolean flag = true;
		try {
			String sql = "SELECT token FROM token";
			this.preparedStatement = this.conn.prepareStatement(sql);
			ResultSet rs = this.preparedStatement.executeQuery();
			if(rs.next()) {
				String todayToken=rs.getString(1);
				if(!todayToken.equals(user.getPassword())) {
					flag=false;
				}
			}else {
				flag=false;
			}
			
			sql = "SELECT userid FROM userstudent where userid=?";
			this.preparedStatement = this.conn.prepareStatement(sql);
			this.preparedStatement.setString(1, user.getUserid());
			rs = this.preparedStatement.executeQuery();
			if(!rs.next()) {
				flag=false;
			}
			
			if(flag) {
				sql = "INSERT INTO signrecord(userid,time) VALUES (?,?)";
				this.preparedStatement = this.conn.prepareStatement(sql);
				this.preparedStatement.setObject(1, user.getUserid());
				this.preparedStatement.setObject(2, Assistance.getTime());
				preparedStatement.executeUpdate();
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if (this.preparedStatement != null) {
				try {
					this.preparedStatement.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw e2;
				}
			}
		}
		return flag;
	}


	public boolean findTeacherLogin(Userstudent user) throws Exception {
		boolean flag = true;
		try {
			
			String sql = "SELECT password FROM teacher where teacherid=?";
			this.preparedStatement = this.conn.prepareStatement(sql);
			this.preparedStatement.setString(1, user.getUserid());
			ResultSet rs = this.preparedStatement.executeQuery();
			if(rs.next()) {
				if(!rs.getString(1).equals(user.getPassword())) {
					flag=false;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		} finally {
			if (this.preparedStatement != null) {
				try {
					this.preparedStatement.close();
				} catch (Exception e2) {
					// TODO: handle exception
					throw e2;
				}
			}
		}
		return flag;
	}

	
	// ×¢²á--²åÈëÊý¾Ý
	@Override
	public void update(Userstudent user) {
		// TODO Auto-generated method stub
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "INSERT INTO userstudent(userid,name,password) " + "VALUES(?,?,?)";

		try {
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, user.getUserid());
			preparedStatement.setObject(2, user.getName());
			preparedStatement.setObject(3, user.getPassword());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCTools.release(null, preparedStatement, connection);
		}
	}


}
