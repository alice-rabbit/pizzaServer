package com.pizza.dao;

import com.pizza.dao.impl.UserDAOimpl;

 

public class DAOFactory {

	public DAOFactory(){
		
	}
	
	
	
	public static UserDAO getUserDAO(){
		return new UserDAOimpl();
	}
	
}
