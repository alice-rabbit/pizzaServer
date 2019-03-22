package com.pizza.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.pizza.dao.DAOFactory;
import com.pizza.dao.DishesDAO;
import com.pizza.dao.impl.DishesDAOimpl;
import com.pizza.entity.DishesVo;
import com.pizza.pager.PageBean;
import com.pizza.util.JsonUtils;
import com.pizza.util.ResponseUtil;

public class DishesServlet extends HttpServlet {
	DAOFactory daoFactory;
	
	public void  doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");// 
		response.setCharacterEncoding("utf-8");
		
		daoFactory = new DAOFactory();
		String op = request.getParameter("op");
		System.out.println(op);
		if(op.equals("add")){
			addDishes(request, response);
		}else if(op.equals("update")){
			updateDishes(request, response);
		}else if(op.equals("del")){
			delDishes(request, response);
		}else if(op.equals("list")){
			listDishes(request, response);
		}else if(op.equals("combo")){
			comboListDishes(request, response);
		}
		
	}
	private void listDishes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		
		String page	= request.getParameter("page");
		String rows = request.getParameter("rows");
		
		String userid   = request.getParameter("s_c_user");
		String status		= request.getParameter("s_s_user");
		
		
		if(userid==null)
			userid="";
		if(status==null)
			status="";
		
		DishesDAO dishesDAO = new DishesDAOimpl();
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),1000);
		
		String jsonStr = JsonUtils.createJsonString(dishesDAO.DishesList(pageBean, userid, status));
		
		PrintWriter out=response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
		
		
		
	}
	private void delDishes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		String delIds=request.getParameter("delIds");
		System.out.println(delIds+"::");
		DishesDAO dishesDAO = new DishesDAOimpl();
		
		String str[]=delIds.split(",");
		for(int i=0; i<str.length; i++){
			dishesDAO.del(str[i]);
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
	private void updateDishes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		int id	= Integer.parseInt(request.getParameter("id"));
		String  name	= request.getParameter("name");
		String describe	=   request.getParameter("describe");
		float price	= Float.parseFloat(request.getParameter("price"));
		DishesDAO dishesDAO = new DishesDAOimpl();
		DishesVo p = dishesDAO.findById(""+id);
		p.name = name;
		p.describe = describe;
		p.price = price;
		dishesDAO.edit(p);
	
		try {
			JSONObject result = new JSONObject();
			result.put("success", "true");
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void addDishes(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		
		int user_id	= Integer.parseInt(request.getParameter("user_id"));
		String  start_loc	= request.getParameter("start_loc");
		String end_loc	=   request.getParameter("end_loc");
		String time	=   request.getParameter("time");
		String good	=   request.getParameter("good");
		int status	= Integer.parseInt(request.getParameter("status"));
		int express_id	= Integer.parseInt(request.getParameter("express_id"));
		
		DishesVo p = new DishesVo();
	
	
	
		DishesDAO dishesDAO = new DishesDAOimpl();
		dishesDAO.add(p);
		
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
	
	
	private void comboListDishes(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException{
		
		
		DishesDAO dishesDAO = new DishesDAOimpl();
	
		
		String jsonStr = JsonUtils.createJsonString(dishesDAO.listAll());
		PrintWriter out=response.getWriter();
		out.println(jsonStr);
		out.flush();
		out.close();
	}
	
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}


}
