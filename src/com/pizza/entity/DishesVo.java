package com.pizza.entity;

import java.io.Serializable;

public class DishesVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int id;
	public String classify;
	public String name;
	public String describe;
	public String pic;
	public float price;
	public int userid;
	public int receive_id;
	public int status;
	public String create_time;
	public String update_time;
	
	
}
