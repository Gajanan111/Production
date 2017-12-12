package com.billdiary.service;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.Mapper;
import com.billdiary.dao.LoginDAO;
import com.billdiary.dao.ProductDAO;
import com.billdiary.model.User;
import com.billdiary.model.User1;


@Service
public class LoginService {
	
	//final static Logger LOGGER = Logger.getLogger(LoginService.class);
	
	@Autowired
	public LoginDAO loginDAO;
	@Autowired
	public ProductDAO productDAO;
	public boolean doLogin(User1 user)
	{
		//LOGGER.debug("In method LoginService:doLogin Entry ");
		boolean userLogged=false;
		
		User u=Mapper.getUserEntity(user);
		
		
		if(loginDAO.doLogin(u))
		{
			userLogged=true;
		}else {
			userLogged=false;
		}
		
		productDAO.fetchProducts();
		//loginDAO.fetchProducts();
		//LOGGER.debug("In method LoginService:doLogin Exit ");
		return userLogged;
		
	}

}
