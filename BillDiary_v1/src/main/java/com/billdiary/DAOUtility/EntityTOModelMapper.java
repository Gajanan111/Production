package com.billdiary.DAOUtility;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.billdiary.entities.SupplierEntity;
import com.billdiary.model.Supplier;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EntityTOModelMapper {

	public List<Supplier> getSupplierModels(List<SupplierEntity> supplierEntityList) {
		List<Supplier> supplierList=new ArrayList<>();
		for(SupplierEntity supEntity:supplierEntityList) {
			Supplier sup=new Supplier();
			sup.setSupplierID(new SimpleIntegerProperty(supEntity.getSupplierID()));
			sup.setSupplierName(new SimpleStringProperty(supEntity.getSupplierName()));
			sup.setSupplierCompany(new SimpleStringProperty(supEntity.getSupplierCompany()));
			sup.setSupplierAddress(new SimpleStringProperty(supEntity.getSupplierAddress()));
			sup.setSupplierAccountNo(new SimpleStringProperty(supEntity.getSupplierAccountNo()));
		//	sup.setSupplierAsOfDate(Date.from(supEntity.getSupplierAsOfDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
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
			supplierList.add(sup);
		}
		return supplierList;
	}
	
	

}
