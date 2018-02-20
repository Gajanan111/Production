package com.billdiary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.model.Customer;
import com.billdiary.utility.Constants;

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
	
	public List<CustomerEntity> fetchActiveCustomers(){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CustomerEntity> criteriaQuery = criteriaBuilder.createQuery(CustomerEntity.class);
		Root<CustomerEntity> from = criteriaQuery.from(CustomerEntity.class);
		criteriaQuery.select(from);
		
		ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
	    criteriaQuery.where(criteriaBuilder.equal(from.get("status"),params));
	    
	    TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(criteriaQuery);
	    typedQuery.setParameter(params, Constants.ACTIVE);
	    
		List<CustomerEntity> customerEntityList =typedQuery.getResultList();
		return customerEntityList;
	}
	
	@Transactional
	public boolean deleteCustomer(int id)
	{

		boolean customerDeleted=false;
		CustomerEntity customerEntity=findEntity(id);
		customerEntity.setStatus(Constants.INACTIVE);
		update(customerEntity);
		//delete(customerEntity);
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
