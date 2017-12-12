package com.billdiary.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



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
	
	@Column(name = "description")
	String description;
	
	@Column(name = "wholesale_price")
	double wholesale_price;
	
	@Column(name = "retail_price")
	double retail_price;
	
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

}