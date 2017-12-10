package com.fuzhu.studentmanager;

public class DAOHomeworkFactory {
	public static IHomeworkDAO getIHomeworkInstance(){
		return new HomeworkDAOImpl();
	}
}
