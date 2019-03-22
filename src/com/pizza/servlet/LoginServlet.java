package com.pizza.servlet;

import java.io.IOException;


import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



public class LoginServlet extends HttpServlet{
															
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		//
		 
		
		/*从前台获取用户名，密码，权限*/
		String userName=request.getParameter("userName");//获取用户名
		String password=request.getParameter("password");//获取密码
	
	
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		session.setAttribute("currentUser",userName);
		response.sendRedirect("main.jsp");
		
		
		
		
		
	}
}
