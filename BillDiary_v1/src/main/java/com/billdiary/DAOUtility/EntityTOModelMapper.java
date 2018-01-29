package com.billdiary.DAOUtility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.ProductCategoryEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.SupplierEntity;
import com.billdiary.entities.UnitEntity;
import com.billdiary.model.Address;
import com.billdiary.model.Customer;
import com.billdiary.model.Product;
import com.billdiary.model.Supplier;
import com.billdiary.model.Unit;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

@Component
public class EntityTOModelMapper {

	public List<Supplier> getSupplierModels(List<SupplierEntity> supplierEntityList) {
		List<Supplier> supplierList=new ArrayList<>();
		for(SupplierEntity supEntity:supplierEntityList) {
			Supplier sup=new Supplier();
			sup.setSupplierID(new SimpleIntegerProperty(supEntity.getSupplierID()));
			sup.setSupplierName(new SimpleStringProperty(supEntity.getSupplierName()));
			sup.setSupplierCompany(new SimpleStringProperty(supEntity.getSupplierCompany()));
			sup.setSupplierAccountNo(new SimpleStringProperty(supEntity.getSupplierAccountNo()));
		//	sup.setSupplierAsOfDate(Date.from(supEntity.getSupplierAsOfDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			if(null!=supEntity.getSupplierAsOfDate())
			sup.setSupplierAsOfDate(supEntity.getSupplierAsOfDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
			sup.setSupplierBillingRate(new SimpleDoubleProperty(supEntity.getSupplierBillingRate()));
			sup.setSupplierEmailID(new SimpleStringProperty(supEntity.getSupplierEmailID()));
			sup.setSupplierGovID(new SimpleStringProperty(supEntity.getSupplierGovID()));
			sup.setSupplierFaxNo(new SimpleStringProperty(supEntity.getSupplierFaxNo()));
			sup.setSupplierWebsite(new SimpleStringProperty(supEntity.getSupplierWebsite()));
			sup.setSupplierUnpaidBalance(new SimpleDoubleProperty(supEntity.getSupplierUnpaidBalance()));
			sup.setSupplierTaxRegNo(new SimpleStringProperty(supEntity.getSupplierTaxRegNo()));
			sup.setSupplierOther(new SimpleStringProperty(supEntity.getSupplierOther()));
			sup.setSupplierMobileNo(new SimpleStringProperty(supEntity.getSupplierMobileNo()));
			sup.setSupplierPhoneNo(new SimpleStringProperty(supEntity.getSupplierPhoneNo()));
			Address address=new Address();
			address.setId(supEntity.getAddressEntity().getId());
			address.setZipcode(supEntity.getAddressEntity().getZipcode());
			address.setCity(supEntity.getAddressEntity().getCity());
			address.setCountry(supEntity.getAddressEntity().getCountry());
			address.setState(supEntity.getAddressEntity().getState());
			address.setStreet1(supEntity.getAddressEntity().getStreet1());
			sup.setAddress(address);
			supplierList.add(sup);
		}
		return supplierList;
	}

	public Supplier getSupplierModel(SupplierEntity supEntity) {
		// TODO Auto-generated method stub
		Supplier sup=new Supplier();
		sup.setSupplierID(new SimpleIntegerProperty(supEntity.getSupplierID()));
		sup.setSupplierName(new SimpleStringProperty(supEntity.getSupplierName()));
		sup.setSupplierCompany(new SimpleStringProperty(supEntity.getSupplierCompany()));
		sup.setSupplierAccountNo(new SimpleStringProperty(supEntity.getSupplierAccountNo()));
	//	sup.setSupplierAsOfDate(Date.from(supEntity.getSupplierAsOfDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		if(null!=supEntity.getSupplierAsOfDate())
		sup.setSupplierAsOfDate(supEntity.getSupplierAsOfDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		sup.setSupplierBillingRate(new SimpleDoubleProperty(supEntity.getSupplierBillingRate()));
		sup.setSupplierEmailID(new SimpleStringProperty(supEntity.getSupplierEmailID()));
		sup.setSupplierGovID(new SimpleStringProperty(supEntity.getSupplierGovID()));
		sup.setSupplierFaxNo(new SimpleStringProperty(supEntity.getSupplierFaxNo()));
		sup.setSupplierWebsite(new SimpleStringProperty(supEntity.getSupplierWebsite()));
		sup.setSupplierUnpaidBalance(new SimpleDoubleProperty(supEntity.getSupplierUnpaidBalance()));
		sup.setSupplierTaxRegNo(new SimpleStringProperty(supEntity.getSupplierTaxRegNo()));
		sup.setSupplierOther(new SimpleStringProperty(supEntity.getSupplierOther()));
		sup.setSupplierMobileNo(new SimpleStringProperty(supEntity.getSupplierMobileNo()));
		sup.setSupplierPhoneNo(new SimpleStringProperty(supEntity.getSupplierPhoneNo()));
		return sup;
	}
	
	public Product getProductModel(ProductEntity productEntity) {
	    Product product=new Product();
	    product.setProductId(new SimpleIntegerProperty(productEntity.getId()));
	    product.setDescription(new SimpleStringProperty(productEntity.getDescription()));
	    product.setDiscount(new SimpleDoubleProperty(productEntity.getDiscount()));
	    product.setName(new SimpleStringProperty(productEntity.getName()));
	    product.setRetailPrice(new SimpleDoubleProperty(productEntity.getRetail_price()));
	    product.setWholesalePrice(new SimpleDoubleProperty(productEntity.getWholesale_price()));
	    product.setStock(new SimpleIntegerProperty(productEntity.getStock()));
	    product.setProductCategory(new SimpleStringProperty(productEntity.getProductCategory()));
	    product.setWholeSaleGST(new SimpleStringProperty(productEntity.getWholeSaleGST()));
	    product.setWholeSaleGSTpercentage(new SimpleDoubleProperty(productEntity.getWholeSaleGSTpercentage()));
	    product.setRetailGST(new SimpleStringProperty(productEntity.getRetailGST()));
	    product.setRetailGSTpercentage(new SimpleDoubleProperty(productEntity.getRetailGSTpercentage()));
		return product;
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
			prod.setProductHSNCode(new SimpleStringProperty(productEntity.getProductHSNCode()));
			prod.setRetailPrice(new SimpleDoubleProperty(productEntity.getRetail_price()));
			prod.setWholesalePrice(new SimpleDoubleProperty(productEntity.getWholesale_price()));
			prod.setStock(new SimpleIntegerProperty(productEntity.getStock()));
			prod.setProductCategory(new SimpleStringProperty(productEntity.getProductCategory()));
			prod.setWholeSaleGST(new SimpleStringProperty(productEntity.getWholeSaleGST()));
			prod.setWholeSaleGSTpercentage(new SimpleDoubleProperty(productEntity.getWholeSaleGSTpercentage()));
			prod.setRetailGST(new SimpleStringProperty(productEntity.getRetailGST()));
			prod.setRetailGSTpercentage(new SimpleDoubleProperty(productEntity.getRetailGSTpercentage()));
			Unit unit=getUnitEntity(productEntity);
			prod.setUnit(unit);
			productList.add(prod);
	
		}
		
		return productList;
	}
	
	
	
	private Unit getUnitEntity(ProductEntity prod) {
		Unit unit=null;
		UnitEntity unitEntity=prod.getUnitEntity();
		if(null!=unitEntity) {
			unit=new Unit(unitEntity);
		}
		return unit;
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
			cust.setAnniversary_date(customerEntity.getAnniversary_Date());
			cust.setBirth_date(customerEntity.getBirth_Date());
			cust.setBalance(new SimpleDoubleProperty(customerEntity.getBalance()));
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
		cust.setAnniversary_date(customerEntity.getAnniversary_Date());
		cust.setBirth_date(customerEntity.getBirth_Date());
		cust.setBalance(new SimpleDoubleProperty(customerEntity.getBalance()));
		}catch(Exception e)
		{
			System.out.println("Mapper"+ e.getMessage());
		}
		return cust;
	}

	public List<String> getProductCategoryList(List<ProductCategoryEntity> categoryListEntity) {
		List<String> categoryList=new ArrayList<>();
		categoryListEntity.forEach(categoryEntity->{
			categoryList.add(categoryEntity.getCategoryName());	
		});
		return categoryList;
	}

	public List<Unit> getUnitList(List<UnitEntity> unitEntityList) {
		List<Unit> units=new ArrayList<>();
		unitEntityList.forEach(unitEntity->{
			units.add(new Unit(unitEntity));
		});
		return units;
	}

	
	

}
