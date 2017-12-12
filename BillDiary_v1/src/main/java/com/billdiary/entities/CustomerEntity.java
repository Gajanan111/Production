package com.billdiary.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.SimpleStringProperty;

@Entity
@Table(name = "customer")
public class CustomerEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "customer_id")
	private int customerID;
	
	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name="email_id")
	private String emailID;

	@Column(name = "mobile_no")
	private String mobile_no;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "country")
	private String country;
	
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "customer_group")
	private String customerGroup;
	
	@Column(name = "zipCode")
	private String zipCode;
	
	
	@Column(name = "addAdditional_info")
	private String addAdditionalInfo;
	
	@Column(name = "regDate")
	private Date regDate;
	
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMobile_no() {
		return mobile_no;
	}
	public void setMobile_no(String mobile_no) {
		this.mobile_no = mobile_no;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCustomerGroup() {
		return customerGroup;
	}
	public void setCustomerGroup(String customerGroup) {
		this.customerGroup = customerGroup;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddAdditionalInfo() {
		return addAdditionalInfo;
	}
	public void setAddAdditionalInfo(String addAdditionalInfo) {
		this.addAdditionalInfo = addAdditionalInfo;
	}
	

}
