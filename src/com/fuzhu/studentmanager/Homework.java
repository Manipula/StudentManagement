package com.fuzhu.studentmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.user.Assistance;

public class Homework {
	private PreparedStatement preparedStatement = null;
	
	public String gethomework() throws Exception {
		return findhomework();
	}
	
	public String findhomework() throws Exception {
		Connection connection = JDBCTools.getConnection();
		try {
			String sql= " SELECT content FROM homework as a where  time=(select max(b.time)  from homework as b)";  
			this.preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = this.preparedStatement.executeQuery();
			if(rs.next()) {
				String content=rs.getString(1);
				return content;
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
		System.out.println("QWRAF");
		return null;
	}


}
