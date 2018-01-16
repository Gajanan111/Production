package com.billdiary.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;



@Entity
@Table(name = "product")
public class ProductEntity implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "product_id")
	int id;
	
	@Column(name = "product_name")
	String name; 
	
	@Column(name="product_category")
	String productCategory;
	
	@Column(name="product_hsncode")
	String productHSNCode;

	@Column(name = "description")
	String description;
	
	@Column(name = "wholesale_price")
	double wholesale_price;
	
	@Column(name = "retail_price")
	double retail_price;
	
	@Column(name = "wholeSale_GST")
	String wholeSaleGST;
	
	@Column(name = "wholeSale_GST_percentage")
	double wholeSaleGSTpercentage;
	
	@Column(name = "retail_GST")
	String retailGST;
	
	@Column(name = "retail_GST_percentage")
	double retailGSTpercentage;
	
	@Column(name = "discount")
	double discount; 
	
	@Column(name = "stock")
	int stock;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getWholesale_price() {
		return wholesale_price;
	}
	public void setWholesale_price(double wholesale_price) {
		this.wholesale_price = wholesale_price;
	}
	public double getRetail_price() {
		return retail_price;
	}
	public void setRetail_price(double retail_price) {
		this.retail_price = retail_price;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	} 
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public String getWholeSaleGST() {
		return wholeSaleGST;
	}
	public void setWholeSaleGST(String wholeSaleGST) {
		this.wholeSaleGST = wholeSaleGST;
	}
	public double getWholeSaleGSTpercentage() {
		return wholeSaleGSTpercentage;
	}
	public void setWholeSaleGSTpercentage(double wholeSaleGSTpercentage) {
		this.wholeSaleGSTpercentage = wholeSaleGSTpercentage;
	}
	public String getRetailGST() {
		return retailGST;
	}
	public void setRetailGST(String retailGST) {
		this.retailGST = retailGST;
	}
	public double getRetailGSTpercentage() {
		return retailGSTpercentage;
	}
	public void setRetailGSTpercentage(double retailGSTpercentage) {
		this.retailGSTpercentage = retailGSTpercentage;
	}
	
	public String getProductHSNCode() {
		return productHSNCode;
	}
	public void setProductHSNCode(String productHSNCode) {
		this.productHSNCode = productHSNCode;
	}

}