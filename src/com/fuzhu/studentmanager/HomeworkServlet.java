package com.fuzhu.studentmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.user.DAOFactory;
import com.user.Userstudent;

@WebServlet("/HomeworkServlet")
public class HomeworkServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private IHomeworkDAO homeworkproxy=null;
	 public HomeworkServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	 }
	 
	 /**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			String success_path="teacherdata.jsp";
			request.setCharacterEncoding("utf-8");
			String content=request.getParameter("content");
			//String content=new String(request.getParameter("content").getBytes("iso-8859-1"), "utf-8"); 
			List<String> info = new ArrayList<>();
			if(content==null || "".equals(content)){
				info.add("请输入账号");
				request.setAttribute("info", info);
				request.getRequestDispatcher(success_path).forward(request, response);
			}
			
	
				try {
					if(!content.trim().equals("")) {
						if(DAOHomeworkFactory.getIHomeworkInstance().setHomework(content)){
							info.add("作业发布成功");
							request.setAttribute("info", info);
							request.setAttribute("homework", DAOHomeworkFactory.getIHomeworkInstance().getHomework());
							request.getRequestDispatcher(success_path).forward(request, response);
						}else {
							info.add("请勿重复发布");
							request.setAttribute("info", info);
							request.setAttribute("homework", DAOHomeworkFactory.getIHomeworkInstance().getHomework());
							request.getRequestDispatcher(success_path).forward(request, response);
						}
						
					}
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
			
		}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}


}
