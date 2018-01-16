package com.billdiary.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

import com.billdiary.entities.UserEntity;
import com.billdiary.model.Product;


import javafx.scene.control.Hyperlink;


@Repository
public class LoginDAO extends AbstractJpaDAO< UserEntity >{

	//final static Logger LOGGER = Logger.getLogger(LoginDAO.class);
	
//	@Autowired
//	EntityManagerFactory entityManagerFactory;
	@PersistenceContext
	EntityManager entityManager;
	
	public LoginDAO()
	{
		setClazz(UserEntity.class );
	}
	
	PreparedStatement st;
	ResultSet r1;
	Connection connection;
	public boolean doLogin(UserEntity user)
	{
	//	LOGGER.debug("In method LoginDAO:doLogin Entry ");
		boolean userLogged=true;
		
		try {
		UserEntity u=findOne(1);
		if(null!=u)
		{
			System.out.println(u.toString());
			System.out.println(u.getUserName()+" "+u.getPassword());
		}
		
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		
		
		
		//LOGGER.debug("In method LoginDAO:doLogin Exit ");
		return userLogged;
		
	}
	
	
	public Product fetchProducts()
	{
		Product p=null;
		try
		{
		Query q=entityManager.createNativeQuery("select * from product");
		//Object[] o=(Object[]) q.getSingleResult();
		Object[] o =(Object[])q.getSingleResult();
		
		
			//System.out.println(o.get(0).getDescription());
		
		System.out.println(o[0]+" "+o[1]);
		//System.out.println(o.get(0)+ " "+ o.get(1)+ " "+o.get(2)+" "+o.get(3)+" "+o.get(4)+" "+o.get(5)+" "+o.get(6));
		Hyperlink Delete1 = new Hyperlink();
		
		p=new Product((int)o[0], o[1].toString(), (double)o[3], (double)o[4], o[2].toString(), (int)o[6], (double)o[5], Delete1);
		
		
		}
		catch(Exception e)
		{
		System.out.println("Exception "+e.getMessage());
		}
		return p;
	}
	
	
}
