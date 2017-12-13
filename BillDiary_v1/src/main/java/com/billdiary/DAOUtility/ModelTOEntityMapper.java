package com.billdiary.DAOUtility;

import java.time.ZoneId;
import java.util.Date;

import com.billdiary.entities.SupplierEntity;
import com.billdiary.model.Supplier;

public class ModelTOEntityMapper {

	public SupplierEntity getSupplierEntity(Supplier sup) {
		// TODO Auto-generated method stub
		SupplierEntity supEntity=new SupplierEntity();
		
		supEntity.setSupplierName(sup.getSupplierName());
		supEntity.setSupplierCompany(sup.getSupplierCompany());
		supEntity.setSupplierAddress(sup.getSupplierAddress());
		supEntity.setSupplierAccountNo(sup.getSupplierAccountNo());
		supEntity.setSupplierAsOfDate(Date.from(sup.getSupplierAsOfDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
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
		
		return supEntity;
	}
	
	

}
