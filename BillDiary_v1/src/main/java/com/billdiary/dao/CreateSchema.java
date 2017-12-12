package com.billdiary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

//import org.apache.log4j.Logger;


import com.billdiary.utility.Constants;


@Repository
public class CreateSchema {
	
	//final static Logger LOGGER = Logger.getLogger(CreateSchema.class);
	
	/**
	 * Constant for unchecked.
	 */
	private static final String UNCHECKED = "unchecked";

	/**
	 * Constant for rawtypes.
	 */
	private static final String RAW_TYPES = "rawtypes";
	
	
	/**
	 * Constant for batchSize.
	 */
	private static final Integer batchSize = 10;
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	
	public static boolean CreateDatabase() throws ClassNotFoundException, SQLException
	{
	//	LOGGER.debug("Entering Class CreateSchema : method : CreateDatabase.");
		boolean dbCreated=false;
		Class.forName(Constants.DB_DRIVER);
		Connection connection = DriverManager.getConnection(Constants.DB_URL,Constants.DB_USERNAME,Constants.DB_PASSWORD);
		//connection.createStatement().executeQuery("drop table user");
		connection.createStatement().executeQuery("create table if not exists user(id int, username varchar(20),password varchar(20),role varchar(20),primary key(id))");
		
		//connection.createStatement().executeQuery("insert into user values(1,'gajanan','gajanan','admin')");
		 PreparedStatement st = connection.prepareStatement("select * from user");
		    ResultSet r1=st.executeQuery();
		   while(r1.next())
		   {
			   System.out.println(r1.getInt(1)+" "+r1.getString(2));
		   }
		   connection.createStatement().executeQuery("shutdown");
		   
		r1.close();
		st.close();
		
		connection.close();
		
		System.out.println("Tables created successfully.. and record inserted successfully");
		
		return dbCreated;
		
	}
	@SuppressWarnings({ UNCHECKED, RAW_TYPES })
	public  Integer executeNativeSQLQuery(final String nativeSQLQuery) {
		System.out.println("hi");
		int i=0;
		
		
		/**
		 * Check this code what is happing in it.
		 */
		if(entityManager!=null) {
		final Query query = entityManager.createNativeQuery(nativeSQLQuery);
		
		System.out.println("hi1");
		 i=query.executeUpdate();
		System.out.println("hi2");}
		return i;	
	}

}
