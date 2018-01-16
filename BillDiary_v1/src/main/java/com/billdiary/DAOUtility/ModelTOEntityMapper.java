package com.billdiary.DAOUtility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.billdiary.entities.AddressEntity;
import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.SupplierEntity;
import com.billdiary.entities.UserEntity;

import com.billdiary.model.Customer;
import com.billdiary.model.Invoice;
import com.billdiary.model.Product;
import com.billdiary.model.Supplier;

import com.billdiary.model.User;

import javafx.collections.ObservableList;

@Component
public class ModelTOEntityMapper {
	
	public UserEntity getUserEntity(User user)
	{
		UserEntity userEntity=new UserEntity();
		if(null!=user)
		{
			userEntity.setId(user.getId());
			userEntity.setUserName(user.getUserName());
			userEntity.setPassword(user.getPassword());
			userEntity.setRole(user.getRole());
		}
		
		return userEntity;
	}

	public SupplierEntity getSupplierEntity(Supplier sup) {
		// TODO Auto-generated method stub
		SupplierEntity supEntity=new SupplierEntity();
		
		supEntity.setSupplierName(sup.getSupplierName());
		supEntity.setSupplierCompany(sup.getSupplierCompany());
		supEntity.setSupplierAccountNo(sup.getSupplierAccountNo());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date asOfDate=null;
		
		try {
			if(""!=sup.getSupplierAsOfDate()) {
			asOfDate = df.parse(sup.getSupplierAsOfDate());
			}
		} catch (ParseException e) {
			System.out.println("Exception while date parsing"+e.getMessage());
		
		}
		supEntity.setSupplierAsOfDate(asOfDate);
		supEntity.setSupplierBillingRate(sup.getSupplierBillingRate());
		supEntity.setSupplierEmailID(sup.getSupplierEmailID());
		supEntity.setSupplierGovID(sup.getSupplierGovID());
		supEntity.setSupplierFaxNo(sup.getSupplierFaxNo());
		supEntity.setSupplierWebsite(sup.getSupplierWebsite());
		supEntity.setSupplierUnpaidBalance(sup.getSupplierUnpaidBalance());
		supEntity.setSupplierTaxRegNo(sup.getSupplierTaxRegNo());
		supEntity.setSupplierOther(sup.getSupplierOther());
		supEntity.setSupplierMobileNo(sup.getSupplierMobileNo());
		supEntity.setSupplierPhoneNo(sup.getSupplierPhoneNo());
		
		AddressEntity addressEnitity=new AddressEntity();
		addressEnitity.setId(sup.getAddress().getId());
		addressEnitity.setStreet1(sup.getAddress().getStreet1());
		addressEnitity.setCity(sup.getAddress().getCity());
		addressEnitity.setState(sup.getAddress().getState());
		addressEnitity.setCountry(sup.getAddress().getCountry());
		addressEnitity.setZipcode(sup.getAddress().getZipcode());
		supEntity.setAddressEntity(addressEnitity);
		return supEntity;
	}

	public InvoiceEntity getInvoiceEntity(Invoice inv) {
		InvoiceEntity invoiceEntity=new InvoiceEntity();
		invoiceEntity.setCustomerEntity(getCustomerEntity(inv.getCustomer()));
		/** Set Customer ID**/
		invoiceEntity.getCustomerEntity().setCustomerID(inv.getCustomer().getCustomerID());
		invoiceEntity.setAmountDue(inv.getAmountDue());
		invoiceEntity.setFinalAmount(inv.getFinalAmount());
		invoiceEntity.setInvoiceDate(inv.getInvoiceDate());
		invoiceEntity.setInvoiceDueDate(inv.getInvoiceDueDate());
		invoiceEntity.setLastAmountPaidDate(inv.getLastAmountPaidDate());
		invoiceEntity.setPaidAmount(inv.getPaidAmount());
		invoiceEntity.setProduct_sale_qty(inv.getProduct_sale_qty());
		return invoiceEntity;
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
			customerEntity.setAnniversary_Date(cust.getAnniversary_date());
			customerEntity.setBirth_Date(cust.getBirth_date());
			customerEntityList.add(customerEntity);
		}
		return customerEntityList;
	}
	
	/**
	 * customer model to Customer entity
	 */
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
		customerEntity.setAnniversary_Date(cust.getAnniversary_date());
		customerEntity.setBirth_Date(cust.getBirth_date());
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
			productEntity.setDescription(prod.getDescription());
			productEntity.setDiscount(prod.getDiscount());
			productEntity.setId(prod.getProductId());
			productEntity.setName(prod.getName());
			productEntity.setRetail_price(prod.getRetailPrice());
			productEntity.setWholesale_price(prod.getWholesalePrice());
			productEntity.setStock(prod.getStock());
			productEntity.setProductCategory(prod.getProductCategory());
			productEntityList.add(productEntity);
		}
		return productEntityList;
	}
	public ProductEntity getProductEntity(Product prod) {
		ProductEntity productEntity =new ProductEntity();
		productEntity.setId(0);
		productEntity.setDescription(prod.getDescription());
		productEntity.setName(prod.getName());
		productEntity.setRetail_price(prod.getRetailPrice());;
		productEntity.setWholesale_price(prod.getWholesalePrice());;
		productEntity.setDiscount(prod.getDiscount());
		productEntity.setStock(prod.getStock());
		productEntity.setProductCategory(prod.getProductCategory());
		productEntity.setRetailGST(prod.getRetailGST());
		productEntity.setWholeSaleGST(prod.getWholeSaleGST());
		productEntity.setRetailGSTpercentage(prod.getRetailGSTpercentage());
		productEntity.setWholeSaleGSTpercentage(prod.getWholeSaleGSTpercentage());
		return productEntity;
	}



}
