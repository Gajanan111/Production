package com.billdiary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.model.Customer;

@Repository
public class CustomerDAO extends AbstractJpaDAO< CustomerEntity >{

	@PersistenceContext
	EntityManager entityManager;
	
	public CustomerDAO()
	{
		setClazz(CustomerEntity.class);
	}
	public List<CustomerEntity> fetchCustomers()
	{
		List<CustomerEntity> customerEntityList =findAll();
		return customerEntityList;
	}
	
	@Transactional
	public boolean deleteCustomer(long id)
	{

		boolean customerDeleted=false;
		
		//deleteById(id);
		int i=(int)id;
		CustomerEntity c=entityManager.find(CustomerEntity.class, i);
		
		entityManager.remove(c);
		
		customerDeleted=true;
		return customerDeleted;
	}
	
	@Transactional
	public List<CustomerEntity> saveCustomer(List<CustomerEntity> customerEntityList)
	{

		List<CustomerEntity> updatedCustEntities = new ArrayList<>();
		for(CustomerEntity custEntity:customerEntityList)
		{
			CustomerEntity customerEntity=entityManager.merge(custEntity);
			updatedCustEntities.add(customerEntity);
		}
		
		
		
		
		return updatedCustEntities;
	}
	
	@Transactional
	public boolean addCustomer(CustomerEntity cust)
	{
		boolean customerAdded=false;
		create(cust);
		customerAdded=true;
		return customerAdded;
	}
	
	@Transactional
	public CustomerEntity updateCustomer(CustomerEntity custEntity) {
		// TODO Auto-generated method stub
		CustomerEntity updatedCustomer=null;
		updatedCustomer=update(custEntity);
		return updatedCustomer;
	}
}
