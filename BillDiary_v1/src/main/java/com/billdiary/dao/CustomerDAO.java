package com.billdiary.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
		CustomerEntity updatedCustomer=null;
		updatedCustomer=update(custEntity);
		return updatedCustomer;
	}
	
	public long getCustomerCount() {
		System.out.println("*********"+ "getCustomerCount");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(CustomerEntity.class)));
		Long count = entityManager.createQuery(countQuery).getSingleResult();
		System.out.println("*********"+ "getCustomerCount: "+count);
		return count;
	}
	public List<CustomerEntity> getCustomers(int pages, int pageNumber,int rowsPerPage) {
		System.out.println("*********"+ "getCustomers");
		List<CustomerEntity> customerEntities=null;
		try {
			if(pageNumber<0)
				return null;
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			/*CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
			countQuery.select(criteriaBuilder.count(countQuery.from(ProductEntity.class)));
			Long count = entityManager.createQuery(countQuery).getSingleResult();*/
			CriteriaQuery<CustomerEntity> criteriaQuery = criteriaBuilder.createQuery(CustomerEntity.class);
			Root<CustomerEntity> from = criteriaQuery.from(CustomerEntity.class);
			CriteriaQuery<CustomerEntity> select = criteriaQuery.select(from);
			TypedQuery<CustomerEntity> typedQuery = entityManager.createQuery(select);
			typedQuery.setFirstResult((pageNumber*rowsPerPage));
		    typedQuery.setMaxResults(rowsPerPage);
		    customerEntities=typedQuery.getResultList();
			/*while (pageNumber < count.intValue()) {
			    typedQuery.setFirstResult(pageNumber - 1);
			    typedQuery.setMaxResults(rowsPerPage);
			    //System.out.println("Current page: " + typedQuery.getResultList());
			    productEntities=typedQuery.getResultList();
			    pageNumber += rowsPerPage;
			}*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("*********"+ "getCustomers : end");
		return customerEntities;
	}
	
	@Transactional
	public CustomerEntity updateCustomerBalance(Integer customerID, Double amount) {
		// TODO Auto-generated method stub
		CustomerEntity custEntity=entityManager.find(CustomerEntity.class, customerID);
		custEntity.setBalance(custEntity.getBalance()+amount);
		custEntity=entityManager.merge(custEntity);
		return custEntity;
	}
	
	/**
	 * Function is written for returning Default customer
	 * @return CustomerEntity
	 */
	
	@Transactional
	public CustomerEntity getDefaultCustomer() {
		CustomerEntity customerEntity;
		Long count=(Long) entityManager.createNamedQuery("CustomerEntity.findDefaultCustomer")
				.setParameter("customerName", Constants.DEFAULT_CUSTOMER_NAME)
				.setParameter("address", Constants.DEFAULT_CUSTOMER_ADDRESS)
				.setParameter("mobile_no", Constants.DEFAULT_CUSTOMER_MOBILE_NO)
				.getSingleResult();
		if(count>0) {
			customerEntity=(CustomerEntity) entityManager.createNamedQuery("CustomerEntity.getDefaultCustomer")
					.setParameter("customerName", Constants.DEFAULT_CUSTOMER_NAME)
					.setParameter("address", Constants.DEFAULT_CUSTOMER_ADDRESS)
					.setParameter("mobile_no", Constants.DEFAULT_CUSTOMER_MOBILE_NO)
					.getSingleResult();
			System.out.println("Default Customer:");
			System.out.println("Customer Name:"+customerEntity.getCustomerID());
			System.out.println("Customer Name:"+customerEntity.getCustomerName());
			System.out.println("Customer Address:"+customerEntity.getAddress());
			System.out.println("Customer Mobile No:"+customerEntity.getMobile_no());
		}else {
			customerEntity=new CustomerEntity();
			customerEntity.setCustomerID(0);
			customerEntity.setCustomerName(Constants.DEFAULT_CUSTOMER_NAME);
			customerEntity.setAddress(Constants.DEFAULT_CUSTOMER_ADDRESS);
			customerEntity.setMobile_no(Constants.DEFAULT_CUSTOMER_MOBILE_NO);
			/*DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
			String strDate = dateFormat.format(new Date());*/
			customerEntity.setRegDate(new Date());
			customerEntity.setStatus(Constants.ACTIVE);
			create(customerEntity);
			Query query=entityManager.createNativeQuery("select max(customer_id) from customer");
			int id=(int) query.getSingleResult();
			customerEntity=findEntity(id);
		}
		return customerEntity;
	}
	
	@Transactional
	public CustomerEntity addNewCustomerByInvoive(CustomerEntity custEntity) {
		create(custEntity);
		Query query=entityManager.createNativeQuery("select max(customer_id) from customer");
		int maxId=(int) query.getSingleResult();
		CustomerEntity customerEntity=findEntity(maxId);
		return customerEntity;
	}
	public CustomerEntity getCustomerById(int custID) {
		CustomerEntity customerEntity=findEntity(custID);
		return customerEntity;
	}
	
}
