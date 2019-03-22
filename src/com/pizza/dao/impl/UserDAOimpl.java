package com.pizza.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pizza.dao.UserDAO;
import com.pizza.entity.UserVo;
import com.pizza.pager.PageBean;
import com.pizza.util.JdbcTmpl;



public class UserDAOimpl implements UserDAO{
	
	JdbcTmpl jt = new JdbcTmpl();
	
	public UserVo login(String phone, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from db_user where phone=? and pwd=?";
		Object[] params = new Object[]{phone,password};
		ResultSet rs = jt.find(sql, params);
		
        UserVo p = null;
		
		try {
			if(rs.next()){
				p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p ;
	}

	public boolean add(UserVo p) {
		// TODO Auto-generated method stub
		String  sql =  "insert into db_user values(null,?,?,?,?,?,?)";
		Object[] o = new Object[]{p.nickName,p.phone,p.type,p.pwd,p.money,p.status};
		try {
			
			return jt.insert(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		
		return false;
	}

	public boolean edit(UserVo p) {
		// TODO Auto-generated method stub
		String sql = " update db_user set nickName=?,phone=?,type=?,pwd=?,money = ?,status=? where id=?";
		Object[] o = new Object[]{p.nickName,p.phone,p.type,p.pwd,p.money,p.status,p.id};
		try {
			return jt.update(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return false;
	}

	public boolean del(String id) {
		// TODO Auto-generated method stub
		String sql="delete from db_user where id=?";
		Object[] o = new Object[]{id};
		try {
			return jt.delete(sql, o);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return false;
	}
	
	public UserVo findById(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from db_user where id = ?";
		Object[] params = new Object[]{id};
		
		ResultSet rs = jt.find(sql, params);
		UserVo p = null;
		
		try {
			if(rs.next()){
				p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
			
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p;
	}

	public UserVo findByPhone(String phone) {
		// TODO Auto-generated method stub
		String sql = "select * from db_user where phone = ?";
		Object[] params = new Object[]{phone};
		
		ResultSet rs = jt.find(sql, params);
		UserVo p = null;
		
		try {
			if(rs.next()){
				p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p;
	}

	
	public List<UserVo> listAll() {
		// TODO Auto-generated method stub
		List<UserVo> list = new ArrayList<UserVo>();
		String sql = "select * from db_user";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				UserVo p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
			
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
		
	}

	public List<UserVo> listAllInfactory() {
		// TODO Auto-generated method stub
		List<UserVo> list = new ArrayList<UserVo>();
		String sql = "select * from db_user where status = 1";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				UserVo p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}
	
	public List<UserVo> UserList(PageBean pageBean, String phone,
			String type, int status) {
		// TODO Auto-generated method stub
		String sql;
		List<UserVo> list = new ArrayList<UserVo>();
		sql = "select * from db_user where phone like '%"+phone+"%' and type like '%"+type+"%'";
		
		sql = sql +"  limit  "+pageBean.getStart()+","+pageBean.getRows()+"";
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				UserVo p = new UserVo();
				p.id = (rs.getInt("id"));
				p.nickName = (rs.getString("nickName"));
				p.phone = (rs.getString("phone"));
				p.type = (rs.getInt("type"));
				p.pwd = (rs.getString("pwd"));
				p.money = (rs.getFloat("money"));
				p.status = (rs.getInt("status"));
				
			
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}

	

	
}
