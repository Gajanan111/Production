package com.billdiary.model;

import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.HBox;

public class Supplier {

	private SimpleIntegerProperty supplierID;
	private SimpleStringProperty supplierName;
	private SimpleStringProperty supplierCompany;
	private SimpleStringProperty supplierAddress;
	private SimpleStringProperty supplierGovID;
	private SimpleStringProperty supplierEmailID;
	private SimpleStringProperty  supplierPhoneNo;
	private SimpleStringProperty  supplierMobileNo;
	private SimpleStringProperty supplierFaxNo;
	private SimpleStringProperty supplierWebsite;
	private SimpleDoubleProperty supplierUnpaidBalance;
	private DatePicker supplierAsOfDate;
	private SimpleStringProperty supplierAccountNo;
	private SimpleStringProperty supplierTaxRegNo;
	private SimpleDoubleProperty  supplierBillingRate;
	private SimpleStringProperty supplierOther;
	private  HBox actionbox;
	
	
	
	public HBox getActionbox() {
		return actionbox;
	}
	public void setActionbox(HBox actionbox) {
		this.actionbox = actionbox;
	}
	public Integer getSupplierID() {
		return supplierID.get();
	}
	public void setSupplierID(SimpleIntegerProperty supplierID) {
		this.supplierID = supplierID;
	}
	public String getSupplierName() {
		return supplierName.get();
	}
	public void setSupplierName(SimpleStringProperty supplierName) {
		this.supplierName = supplierName;
	}
	public String getSupplierCompany() {
		return supplierCompany.get();
	}
	public void setSupplierCompany(SimpleStringProperty supplierCompany) {
		this.supplierCompany = supplierCompany;
	}
	public String getSupplierAddress() {
		return supplierAddress.get();
	}
	public void setSupplierAddress(SimpleStringProperty supplierAddress) {
		this.supplierAddress = supplierAddress;
	}
	public String getSupplierGovID() {
		return supplierGovID.get();
	}
	public void setSupplierGovID(SimpleStringProperty supplierGovID) {
		this.supplierGovID = supplierGovID;
	}
	public String getSupplierEmailID() {
		return supplierEmailID.get();
	}
	public void setSupplierEmailID(SimpleStringProperty supplierEmailID) {
		this.supplierEmailID = supplierEmailID;
	}
	public String getSupplierPhoneNo() {
		return supplierPhoneNo.get();
	}
	public void setSupplierPhoneNo(SimpleStringProperty supplierPhoneNo) {
		this.supplierPhoneNo = supplierPhoneNo;
	}
	public String getSupplierMobileNo() {
		return supplierMobileNo.get();
	}
	public void setSupplierMobileNo(SimpleStringProperty supplierMobileNo) {
		this.supplierMobileNo = supplierMobileNo;
	}
	public String getSupplierFaxNo() {
		return supplierFaxNo.get();
	}
	public void setSupplierFaxNo(SimpleStringProperty supplierFaxNo) {
		this.supplierFaxNo = supplierFaxNo;
	}
	public String getSupplierWebsite() {
		return supplierWebsite.get();
	}
	public void setSupplierWebsite(SimpleStringProperty supplierWebsite) {
		this.supplierWebsite = supplierWebsite;
	}
	public double getSupplierUnpaidBalance() {
		return supplierUnpaidBalance.get();
	}
	public void setSupplierUnpaidBalance(SimpleDoubleProperty supplierUnpaidBalance) {
		this.supplierUnpaidBalance = supplierUnpaidBalance;
	}
	public LocalDate getSupplierAsOfDate() {
		return supplierAsOfDate.getValue();
	}
	public void setSupplierAsOfDate(DatePicker supplierAsOfDate) {
		this.supplierAsOfDate = supplierAsOfDate;
	}
	public String getSupplierAccountNo() {
		return supplierAccountNo.get();
	}
	public void setSupplierAccountNo(SimpleStringProperty supplierAccountNo) {
		this.supplierAccountNo = supplierAccountNo;
	}
	public String getSupplierTaxRegNo() {
		return supplierTaxRegNo.get();
	}
	public void setSupplierTaxRegNo(SimpleStringProperty supplierTaxRegNo) {
		this.supplierTaxRegNo = supplierTaxRegNo;
	}
	public double getSupplierBillingRate() {
		return supplierBillingRate.get();
	}
	public void setSupplierBillingRate(SimpleDoubleProperty supplierBillingRate) {
		this.supplierBillingRate = supplierBillingRate;
	}
	public String getSupplierOther() {
		return supplierOther.get();
	}
	public void setSupplierOther(SimpleStringProperty supplierOther) {
		this.supplierOther = supplierOther;
	}

	
	
}
