package com.pizza.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pizza.dao.RequestDAO;
import com.pizza.entity.RequestVo;
import com.pizza.pager.PageBean;
import com.pizza.util.JdbcTmpl;
/**
实现对比萨菜单信息的增删改查
*/


public class DishesDAOimpl implements DishesDAO{
	
	JdbcTmpl jt = new JdbcTmpl();
	
	

	public boolean add(DishesVo p) {
		// TODO Auto-generated method stub
		String  sql =  "insert into db_goods values(null,?,?,?,?,?,?,?,?,?,?)";
		Object[] o = new Object[]{p.classify,p.name,p.describe,p.pic,p.price,p.userid,p.receive_id,
				p.status,p.create_time,p.update_time};
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

	public boolean edit(DishesVo p) {
		// TODO Auto-generated method stub
		String sql = " update db_goods set classify=?,name=?,describe=?,pic=?,price = ?" +
				",userid=? ,receive_id = ?,status=? ,create_time = ?,update_time=?  where id = ?";
		Object[] o = new Object[]{p.classify,p.name,p.describe,p.pic,p.price,p.userid,p.receive_id,
				p.status,p.create_time,p.update_time,p.id};
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
		String sql="delete from db_goods where id=?";
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
	
	public DishesVo findById(String id) {
		String sql = "select * from db_goods where id = ?";
		Object[] params = new Object[]{id};
		ResultSet rs = jt.find(sql, params);
		DishesVo p = null;
		try {
			if(rs.next()){
				p = new DishesVo();
			
				
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return p;
	}

	

	
	public List<DishesVo> listAll() {
		// TODO Auto-generated method stub
		List<DishesVo> list = new ArrayList<DishesVo>();
		String sql = "select * from db_goods";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				DishesVo p = new DishesVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
		
	}
	
	public List<DishesVo> listAllInFactory() {
		// TODO Auto-generated method stub
		List<DishesVo> list = new ArrayList<DishesVo>();
		String sql = "select * from db_goods where status = 1";
		
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				RequestVo p = new RequestVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}

	public List<DishesVo> dishesList(PageBean pageBean, String userid,
			String receive_id) {
		// TODO Auto-generated method stub
		String sql;
		List<DishesVo> list = new ArrayList<DishesVo>();
		sql = "select * from db_goods where userid like '%"+userid+"%' and receive_id like '%"+receive_id+"%'";
		
		sql = sql +"  limit  "+pageBean.getStart()+","+pageBean.getRows()+"";
		ResultSet rs = jt.findAll(sql);
		
		try {
			while(rs.next()){
				DishesVo p = new DishesVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}

	public List<DishesVo> dishesList(String user_id) {
		// TODO Auto-generated method stub
		String sql;
		List<DishesVo> list = new ArrayList<DishesVo>();
		sql = "select * from db_goods where userid = "+user_id +" or receive_id = " + user_id ;
		ResultSet rs = jt.findAll(sql);
		try {
			while(rs.next()){
				DishesVo p = new DishesVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}
	public List<DishesVo> dishesList(String user_id,int type) {
		// TODO Auto-generated method stub
		String sql;
		List<DishesVo> list = new ArrayList<DishesVo>();
		if(type == 1){
			sql = "select * from db_goods where userid = "+user_id ;
		}else {
			sql = "select * from db_goods where receive_id = "+user_id +" and status  > 0 ";
		}
		
		ResultSet rs = jt.findAll(sql);
		try {
			while(rs.next()){
				DishesVo p = new DishesVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			jt.close();
		}
		return list;
	}
	public List<DishesVo> dishesList(String userid,String classify,String key) {
		// TODO Auto-generated method stub
		String sql;
		List<DishesVo> list = new ArrayList<DishesVo>();
		if("".equals(key)){
			sql = "select * from db_goods where userid != "+ userid +" and  status = 0  ";
		}else {
			sql = "select * from db_goods where userid != "+ userid +" and  status = 0  and  (classify ="+ classify + " or " +
					" db_goods.describe like '%"+key+"%')";
		}
		
		ResultSet rs = jt.findAll(sql);
		try {
			while(rs.next()){
				DishesVo p = new DishesVo();
				p.id = (rs.getInt("id"));
				p.classify = (rs.getString("classify"));
				p.name = (rs.getString("name"));
				p.describe = (rs.getString("describe"));
				p.pic = (rs.getString("pic"));
				p.price = (rs.getFloat("price"));
				p.userid = (rs.getInt("userid"));
				p.receive_id = (rs.getInt("receive_id"));
				p.status = (rs.getInt("status"));
				p.create_time = (rs.getString("create_time"));
				p.update_time = (rs.getString("update_time"));
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
