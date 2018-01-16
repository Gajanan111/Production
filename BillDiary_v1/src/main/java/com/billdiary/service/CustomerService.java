package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.EntityTOModelMapper;
import com.billdiary.DAOUtility.ModelTOEntityMapper;
import com.billdiary.dao.CustomerDAO;
import com.billdiary.entities.CustomerEntity;
import com.billdiary.model.Customer;
import javafx.collections.ObservableList;

@Service
public class CustomerService {

	@Autowired
	CustomerDAO customerDAO;
	
	@Autowired
	ModelTOEntityMapper modelTOEntityMapper;
	
	@Autowired
	EntityTOModelMapper entityTOModelMapper;
	
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
		customerList=entityTOModelMapper.getCustomerModels(customerEntityList);
		
		return customerList;
	}
	
	
	public boolean deleteCustomer(int id)
	{
		boolean customerDeleted=false;
		customerDAO.deleteCustomer(id);
		customerDeleted=true;
		return customerDeleted;
	}
	public List<Customer> saveCustomer(ObservableList<Customer> obcustomerList)
	{
		List<CustomerEntity>  customerEntityList = modelTOEntityMapper.getCustEntitiesFromObservableList(obcustomerList);
		List<CustomerEntity> updatedCustEntities = new ArrayList<>();
		updatedCustEntities=customerDAO.saveCustomer(customerEntityList);
		List<Customer> customerList =new ArrayList<>();
		customerList=entityTOModelMapper.getCustomerModels(updatedCustEntities);
		
		return customerList;
	}
	
	public boolean addCustomer(Customer cust)
	{
		
		boolean customerAdded=false;
		CustomerEntity custEntity=modelTOEntityMapper.getCustomerEntity(cust);
		customerAdded=customerDAO.addCustomer(custEntity);
		return customerAdded;
	}
	
	public Customer updateCustomer(Customer cust)
	{
		Customer updatedCustomer=null;
		CustomerEntity updatedCustEnitity=null;
		CustomerEntity custEntity=modelTOEntityMapper.getCustomerEntity(cust);
		custEntity.setCustomerID(cust.getCustomerID());
		updatedCustEnitity=customerDAO.updateCustomer(custEntity);
		updatedCustomer=entityTOModelMapper.getCustomerOneModel(updatedCustEnitity);
		return updatedCustomer;
	}
}
