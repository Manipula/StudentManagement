package com.fuzhu.studentmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.user.Assistance;

public class HomeworkDAOImpl implements IHomeworkDAO{

	private PreparedStatement preparedStatement = null;
	

	@Override
	public List<String> getHomework() throws Exception {
		Connection connection = JDBCTools.getConnection();
		try {
			String sql= " SELECT content FROM homework as a where  time=(select max(b.time)  from homework as b)";  
			this.preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = this.preparedStatement.executeQuery();
			List<String> homeworklist=new ArrayList<String>();
			while(rs.next()) {
				String content=rs.getString(1);
				homeworklist.add(content);	
			}
			return homeworklist;
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
	}


	@Override
	public boolean setHomework(String content) throws Exception {
		Connection connection = JDBCTools.getConnection();
		try {
			String sql= " SELECT content FROM homework as a where  time=?";  
			this.preparedStatement=connection.prepareStatement(sql);
			this.preparedStatement.setString(1, Assistance.getTimeDay());
			ResultSet rs = this.preparedStatement.executeQuery();
			
			while(rs.next()) {
				String earlycontent=rs.getString(1);
				if(earlycontent.equals(content)){
					return false;
				}
			}
			
			sql = "INSERT INTO homework(content,time) VALUES (?,?)";
			this.preparedStatement = connection.prepareStatement(sql);
			this.preparedStatement.setObject(1, content);
			this.preparedStatement.setObject(2, Assistance.getTimeDay());
			preparedStatement.executeUpdate();
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
		return true;
	}


}
