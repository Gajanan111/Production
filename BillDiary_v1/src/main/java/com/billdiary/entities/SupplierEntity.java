package com.billdiary.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supplier")
public class SupplierEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "supplier_id")
	private int supplierID;
	
	@Column(name = "supplier_name")
	private String supplierName;
	
	@Column(name = "supplier_company")
	private String supplierCompany;
	
	@Column(name = "supplier_address")
	private String supplierAddress;
	
	@Column(name = "supplier_gov_id")
	private String supplierGovID;
	
	@Column(name = "supplier_emailid")
	private String supplierEmailID;
	
	@Column(name = "supplier_phoneno")
	private String supplierPhoneNo;
	
	@Column(name = "supplier_mobileno")
	private String supplierMobileNo;
	
	@Column(name = "supplier_faxno")
	private String supplierFaxNo;
	
	@Column(name = "supplier_website")
	private String supplierWebsite;
	
	@Column(name = "supplier_unpaid_balance")
	private double supplierUnpaidBalance;
	
	@Column(name = "supplier_asofdate")
	private Date supplierAsOfDate;
	
	@Column(name = "supplier_account_no")
	private String supplierAccountNo; 
	
	@Column(name = "supplier_tax_reg_no")
	private String supplierTaxRegNo;
	
	@Column(name = "supplier_billing_rate")
	private double supplierBillingRate; 
	
	@Column(name = "supplier_other")
	private String supplierOther;

	public int getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierCompany() {
		return supplierCompany;
	}

	public void setSupplierCompany(String supplierCompany) {
		this.supplierCompany = supplierCompany;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getSupplierGovID() {
		return supplierGovID;
	}

	public void setSupplierGovID(String supplierGovID) {
		this.supplierGovID = supplierGovID;
	}

	public String getSupplierEmailID() {
		return supplierEmailID;
	}

	public void setSupplierEmailID(String supplierEmailID) {
		this.supplierEmailID = supplierEmailID;
	}

	public String getSupplierPhoneNo() {
		return supplierPhoneNo;
	}

	public void setSupplierPhoneNo(String supplierPhoneNo) {
		this.supplierPhoneNo = supplierPhoneNo;
	}

	public String getSupplierMobileNo() {
		return supplierMobileNo;
	}

	public void setSupplierMobileNo(String supplierMobileNo) {
		this.supplierMobileNo = supplierMobileNo;
	}

	public String getSupplierFaxNo() {
		return supplierFaxNo;
	}

	public void setSupplierFaxNo(String supplierFaxNo) {
		this.supplierFaxNo = supplierFaxNo;
	}

	public String getSupplierWebsite() {
		return supplierWebsite;
	}

	public void setSupplierWebsite(String supplierWebsite) {
		this.supplierWebsite = supplierWebsite;
	}

	public double getSupplierUnpaidBalance() {
		return supplierUnpaidBalance;
	}

	public void setSupplierUnpaidBalance(double supplierUnpaidBalance) {
		this.supplierUnpaidBalance = supplierUnpaidBalance;
	}

	public Date getSupplierAsOfDate() {
		return supplierAsOfDate;
	}

	public void setSupplierAsOfDate(Date supplierAsOfDate) {
		this.supplierAsOfDate = supplierAsOfDate;
	}

	public String getSupplierAccountNo() {
		return supplierAccountNo;
	}

	public void setSupplierAccountNo(String supplierAccountNo) {
		this.supplierAccountNo = supplierAccountNo;
	}

	public String getSupplierTaxRegNo() {
		return supplierTaxRegNo;
	}

	public void setSupplierTaxRegNo(String supplierTaxRegNo) {
		this.supplierTaxRegNo = supplierTaxRegNo;
	}

	public double getSupplierBillingRate() {
		return supplierBillingRate;
	}

	public void setSupplierBillingRate(double supplierBillingRate) {
		this.supplierBillingRate = supplierBillingRate;
	}

	public String getSupplierOther() {
		return supplierOther;
	}

	public void setSupplierOther(String supplierOther) {
		this.supplierOther = supplierOther;
	} 

	

}