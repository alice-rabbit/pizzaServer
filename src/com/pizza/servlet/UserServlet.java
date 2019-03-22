package com.pizza.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.pizza.dao.DAOFactory;
import com.pizza.dao.UserDAO;
import com.pizza.entity.UserVo;
import com.pizza.pager.PageBean;
import com.pizza.util.JsonUtils;
import com.pizza.util.ResponseUtil;

public class UserServlet extends HttpServlet {
	DAOFactory daoFactory;
	
	public void  doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 
		response.setCharacterEncoding("utf-8");
		
		daoFactory = new DAOFactory();
		String op = request.getParameter("op");
		System.out.println(op);
		if(op.equals("add")){
			addUser(request, response);
		}else if(op.equals("update")){
			updateUser(request, response);
		}else if(op.equals("del")){
			delUser(request, response);
		}else if(op.equals("list")){
			listUser(request, response);
		}else if(op.equals("combo")){
			comboListUser(request, response);
		}else if(op.equals("combo2")){
			combo2ListUser(request, response);
		}
		
	}
	private void listUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String page	= request.getParameter("page");
		String rows = request.getParameter("rows");
		
		String phone   = request.getParameter("s_phone");
		String type		= request.getParameter("s_type");
		
		
		if(phone==null)
			phone="";
		if(type==null)
			type="";
		UserDAO userDAO = daoFactory.getUserDAO();
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<UserVo> userVos = userDAO.UserList(pageBean, phone, type, -1);
		String jsonStr = JsonUtils.createJsonString(userVos);
		
		PrintWriter out=response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
		
		
		
	}
	private void delUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String delIds=request.getParameter("delIds");
		UserDAO userDAO = daoFactory.getUserDAO();
		
		String str[]=delIds.split(",");
		for(int i=0; i<str.length; i++){
			userDAO.del(str[i]);
		}
		
		try {
			JSONObject result=new JSONObject();
			result.put("success", "true");
			result.put("delNums", str.length);
			ResponseUtil.write(response, result);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void updateUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		String id	 	= request.getParameter("id");
		String nickName	 	= request.getParameter("nickName");
		String phone	= request.getParameter("phone");
		String pwd	= request.getParameter("pwd");
		String money	= request.getParameter("money");
		UserDAO userDAO = daoFactory.getUserDAO();
		UserVo p = userDAO.findById(id);
	
		p.nickName = nickName ;
		p.pwd = pwd ;
		p.phone = phone ;
		p.money = Integer.parseInt(money) ;
		
		userDAO.edit(p);
	
		try {
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		//String user_id		= request.getParameter("user_id");
		String nickName	 	= request.getParameter("nickName");
		String phone	= request.getParameter("phone");
		String pwd	= request.getParameter("pwd");
		String money	= request.getParameter("money");
		UserVo p = new UserVo();
	
		p.nickName = nickName ;
		p.pwd = pwd ;
		p.phone = phone ;
		p.money = Integer.parseInt(money) ;
		UserDAO userDAO = daoFactory.getUserDAO();
		userDAO.add(p);
		
		System.out.println(p.toString());
		try {
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void comboListUser(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		
		
		UserDAO userDAO = daoFactory.getUserDAO();
	
		
		String jsonStr = JsonUtils.createJsonString(userDAO.listAll());
		PrintWriter out=response.getWriter();
		System.out.println(jsonStr);
		out.println(jsonStr);
		out.flush();
		out.close();
	}
	
	private void combo2ListUser(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
			
			
		   UserDAO userDAO = daoFactory.getUserDAO();
			
			List<UserVo> userList = userDAO.listAllInfactory();
			
			String jsonStr = JsonUtils.createJsonString(patientList);
			PrintWriter out=response.getWriter();
			System.out.println(jsonStr);
			out.println(jsonStr);
			out.flush();
			out.close();
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
