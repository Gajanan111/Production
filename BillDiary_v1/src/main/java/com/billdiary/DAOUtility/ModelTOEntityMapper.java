package com.billdiary.DAOUtility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;

import com.billdiary.entities.AddressEntity;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.entities.SupplierEntity;
import com.billdiary.model.Address;
import com.billdiary.model.Invoice;
import com.billdiary.model.Supplier;

public class ModelTOEntityMapper {

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
	//	invoiceEntity.setCustomerEntity(inv.getCustomer());
		Mapper map=new Mapper();
		invoiceEntity.setCustomerEntity(map.getCustomerEntity(inv.getCustomer()));
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
	
	

}
