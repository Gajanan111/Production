package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.Mapper;
import com.billdiary.dao.CustomerDAO;
import com.billdiary.entities.CustomerEntity;
import com.billdiary.model.Customer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@Service
public class CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	public List<Customer> fetchCustomers()
	{
		List<Customer> customerList=new ArrayList<>();
		List<CustomerEntity> customerEntityList=new ArrayList<>();
		try {
		customerEntityList=customerDAO.fetchCustomers();
		}catch(Exception e)
		{
			System.out.println("service "+e.getMessage());
		}
		Mapper m=new Mapper();
		customerList=m.getCustomerModels(customerEntityList);
		
		return customerList;
	}
	
	
	public boolean deleteCustomer(long id)
	{
		boolean customerDeleted=false;
		customerDAO.deleteCustomer(id);
		customerDeleted=true;
		return customerDeleted;
	}
	public List<Customer> saveCustomer(ObservableList<Customer> obcustomerList)
	{
		
		Mapper m=new Mapper();
		List<CustomerEntity>  customerEntityList = m.getCustEntitiesFromObservableList(obcustomerList);
		List<CustomerEntity> updatedCustEntities = new ArrayList<>();
		updatedCustEntities=customerDAO.saveCustomer(customerEntityList);
		List<Customer> customerList =new ArrayList<>();
		customerList=m.getCustomerModels(updatedCustEntities);
		
		return customerList;
	}
	
	public boolean addCustomer(Customer cust)
	{
		Mapper m=new Mapper();
		boolean customerAdded=false;
		CustomerEntity custEntity=m.getCustomerEntity(cust);
		customerAdded=customerDAO.addCustomer(custEntity);
		return customerAdded;
	}
	
	public Customer updateCustomer(Customer cust)
	{
		Customer updatedCustomer=null;
		CustomerEntity updatedCustEnitity=null;
		Mapper m=new Mapper();
		CustomerEntity custEntity=m.getCustomerEntity(cust);
		
		custEntity.setCustomerID(cust.getCustomerID());
		updatedCustEnitity=customerDAO.updateCustomer(custEntity);
		updatedCustomer=m.getCustomerOneModel(updatedCustEnitity);
		return updatedCustomer;
	}
	
	
}
