package com.pizza.entity;

import java.io.Serializable;

public class UserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int id;
	public String nickName;
	public String phone;
	public String pwd;
	public int status;//当前状态
	public String Address;//用户地址
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		   if(obj==null) return false;
		    if(!(obj instanceof UserVo )) return false;
		    return ((UserVo)obj).id == this.id;
	}
}
