package com.billdiary.DAOUtility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.model.Customer;
import com.billdiary.model.Product;
import com.billdiary.model.User;
import com.billdiary.model.User1;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;

public class Mapper {
	
	
	
	public static User getUserEntity(User1 user)
	{
		User userEntity=new User();
		if(null!=user)
		{
			userEntity.setId(user.getId());
			userEntity.setUserName(user.getUserName());
			userEntity.setPassword(user.getPassword());
			userEntity.setRole(user.getRole());
		}
		
		return userEntity;
	}

	
	public List<Product> getProductModels(List<ProductEntity> productEntityList) {
		List<Product> productList=new ArrayList<>();
		
		for(ProductEntity productEntity:productEntityList)
		{
			Product prod=new Product();
			prod.setProductId(new SimpleIntegerProperty(productEntity.getId()));
			prod.setDescription(new SimpleStringProperty(productEntity.getDescription()));
			prod.setDiscount(new SimpleDoubleProperty(productEntity.getDiscount()));
			prod.setName(new SimpleStringProperty(productEntity.getName()));
			prod.setRetailPrice(new SimpleDoubleProperty(productEntity.getRetail_price()));
			prod.setWholesalePrice(new SimpleDoubleProperty(productEntity.getWholesale_price()));
			prod.setStock(new SimpleIntegerProperty(productEntity.getStock()));
			//prod.setDelete(new Hyperlink("Delete"));
			productList.add(prod);
			
			
		}
		
		return productList;
	}
	
	
	/**
	 * Get CustomerEntity List From ObservableCustomerList 
	 * @param obcustomerList
	 * @return List<CustomerEntity>
	 */

	public List<CustomerEntity> getCustEntitiesFromObservableList(ObservableList<Customer> obcustomerList) {
		// TODO Auto-generated method stub
		
		List<CustomerEntity> customerEntityList = new ArrayList<>();
		for(Customer cust:obcustomerList)
		{
			CustomerEntity customerEntity =new CustomerEntity();
			customerEntity.setCustomerID(cust.getCustomerID());
			customerEntity.setCustomerName(cust.getCustomerName());
			customerEntity.setMobile_no(cust.getMobile_no());
			customerEntity.setAddress(cust.getAddress());
			customerEntity.setCity(cust.getCity());
			customerEntity.setCountry(cust.getCountry());
			customerEntity.setEmailID(cust.getEmailID());
			customerEntity.setAddAdditionalInfo(cust.getAddAdditionalInfo());
			customerEntity.setState(cust.getState());
			customerEntity.setCustomerGroup(cust.getCustomerGroup());
			customerEntity.setZipCode(cust.getZipCode());
			customerEntityList.add(customerEntity);
		}
		return customerEntityList;
	}
	
	/**
	 * Get Customer List From List of CustomerEntityList
	 * @param customerEntityList
	 * @return List<Customer>
	 */
	public List<Customer> getCustomerModels(List<CustomerEntity> customerEntityList) {
		List<Customer> customerList=new ArrayList<>();
		try {
		for(CustomerEntity customerEntity:customerEntityList)
		{
			Customer cust=new Customer();
			cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
			//cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
			cust.setCustomerName(new SimpleStringProperty(customerEntity.getCustomerName()));
			cust.setAddress(new SimpleStringProperty(customerEntity.getAddress()));
			cust.setCity(new SimpleStringProperty(customerEntity.getCity()));
			cust.setCountry(new SimpleStringProperty(customerEntity.getCountry()));
			cust.setMobile_no(new SimpleStringProperty(customerEntity.getMobile_no()));
			cust.setEmailID(new SimpleStringProperty(customerEntity.getEmailID()));
			cust.setAddAdditionalInfo(new SimpleStringProperty(customerEntity.getAddAdditionalInfo()));
			cust.setState(new SimpleStringProperty(customerEntity.getState()));
			cust.setCustomerGroup(new SimpleStringProperty(customerEntity.getCustomerGroup()));
			cust.setZipCode(new SimpleStringProperty(customerEntity.getZipCode()));
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			if(null!=customerEntity.getRegDate()) {
		    String strDate = dateFormat.format(customerEntity.getRegDate());
		    cust.setRegistrationDate(new SimpleStringProperty(strDate));
			}else {
				cust.setRegistrationDate(new SimpleStringProperty(""));
			}
			
			customerList.add(cust);
		}
		}catch(Exception e)
		{
			System.out.println("bnmk"+e.getMessage());
			e.printStackTrace();
		}
		return customerList;
	}


	public CustomerEntity getCustomerEntity(Customer cust) {
		// TODO Auto-generated method stub
		CustomerEntity customerEntity =new CustomerEntity();
		customerEntity.setCustomerID(0);
		customerEntity.setCustomerName(cust.getCustomerName());
		customerEntity.setMobile_no(cust.getMobile_no());
		customerEntity.setAddress(cust.getAddress());
		customerEntity.setCity(cust.getCity());
		customerEntity.setCountry(cust.getCountry());
		customerEntity.setEmailID(cust.getEmailID());
		customerEntity.setAddAdditionalInfo(cust.getAddAdditionalInfo());
		customerEntity.setState(cust.getState());
		customerEntity.setCustomerGroup(cust.getCustomerGroup());
		customerEntity.setZipCode(cust.getZipCode());
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date regDate=null;
		try {
			regDate = df.parse(cust.getRegistrationDate());
		} catch (ParseException e) {
			System.out.println("Exception while date parsing"+e.getMessage());
		}
		customerEntity.setRegDate(regDate);
		return customerEntity;
	}


	public List<ProductEntity> getProdEntitiesFromObservableList(ObservableList<Product> obproductList) {
		List<ProductEntity> productEntityList = new ArrayList<>();
		for(Product prod:obproductList)
		{
			ProductEntity productEntity =new ProductEntity();
			productEntity.setDescription(prod.getDescription());;
			productEntity.setDiscount(prod.getDiscount());;
			productEntity.setId(prod.getProductId());;
			productEntity.setName(prod.getName());;
			productEntity.setRetail_price(prod.getRetailPrice());;
			productEntity.setWholesale_price(prod.getWholesalePrice());;
			productEntity.setStock(prod.getStock());;
			productEntityList.add(productEntity);
		}
		return productEntityList;
	}


	public ProductEntity getProductEntity(Product prod) {
		ProductEntity productEntity =new ProductEntity();
		productEntity.setId(0);
		productEntity.setDescription(prod.getDescription());
		productEntity.setName(prod.getName());;
		productEntity.setRetail_price(prod.getRetailPrice());;
		productEntity.setWholesale_price(prod.getWholesalePrice());;
		productEntity.setDiscount(prod.getDiscount());;
		productEntity.setStock(prod.getStock());;
		
		return productEntity;
	}


	public Customer getCustomerOneModel(CustomerEntity customerEntity) {
		// TODO Auto-generated method stub
		Customer cust=new Customer();
		try {
		
		cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
		//cust.setCustomerID(new SimpleIntegerProperty(customerEntity.getCustomerID()));
		cust.setCustomerName(new SimpleStringProperty(customerEntity.getCustomerName()));
		cust.setAddress(new SimpleStringProperty(customerEntity.getAddress()));
		cust.setCity(new SimpleStringProperty(customerEntity.getCity()));
		cust.setCountry(new SimpleStringProperty(customerEntity.getCountry()));
		cust.setMobile_no(new SimpleStringProperty(customerEntity.getMobile_no()));
		cust.setEmailID(new SimpleStringProperty(customerEntity.getEmailID()));
		cust.setAddAdditionalInfo(new SimpleStringProperty(customerEntity.getAddAdditionalInfo()));
		cust.setState(new SimpleStringProperty(customerEntity.getState()));
		cust.setCustomerGroup(new SimpleStringProperty(customerEntity.getCustomerGroup()));
		cust.setZipCode(new SimpleStringProperty(customerEntity.getZipCode()));
		
		}catch(Exception e)
		{
			System.out.println("Mapper"+ e.getMessage());
		}
		return cust;
	}

}