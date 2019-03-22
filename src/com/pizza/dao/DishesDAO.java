package com.pizza.dao;

import java.util.List;
import com.pizza.entity.DishesVo;
import com.pizza.pager.PageBean;
/**
比萨菜单信息的增删改查的接口
*/


public interface DishesDAO {


	
	
	public boolean add(DishesVo dishes);

	public boolean edit(DishesVo Dishes);

	public boolean del(String id);
	
	public DishesVo findById(String id);
	
	public List<DishesVo> listAll();
	
	public List<DishesVo> listAllInFactory();
	
	public List<DishesVo> DishesList(PageBean pageBean, String userid,  String status);
	public List<DishesVo> DishesList(String userid);
	public List<DishesVo> DishesList(String userid,int type);
	public List<DishesVo> DishesList(String userid,String classify,String key);
}
