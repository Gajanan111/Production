package com.billdiary.model;

import org.springframework.stereotype.Component;

import com.billdiary.utility.IconGallery;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;


@Component
public class Product implements Cloneable{
	
	private SimpleIntegerProperty serialNumber;
	
	private SimpleDoubleProperty totalPrice;
	
	private SimpleIntegerProperty productId;
	
	private SimpleLongProperty productCode;
	
	private SimpleStringProperty name;
	
	private SimpleStringProperty productCategory;
	
	private SimpleStringProperty productHSNCode;
		
	private SimpleDoubleProperty wholesalePrice;
	
	private SimpleDoubleProperty retailPrice;
	
	private SimpleStringProperty description;
	
	private SimpleDoubleProperty stock;
	
	private SimpleDoubleProperty discount;
	
	private SimpleDoubleProperty quantity;
	
	private SimpleStringProperty wholeSaleGST;
	
	private SimpleDoubleProperty wholeSaleGSTpercentage;
	
	private SimpleStringProperty retailGST;
	
	private SimpleDoubleProperty retailGSTpercentage;
	
	private SimpleDoubleProperty mrpPrice;
	
	private SimpleDoubleProperty ratePrice;
	
	private SimpleDoubleProperty wholeSaleGSTPrice;
	
	
	
	private Unit unit;
	
	private HBox action;
	
	private Hyperlink delete;
	
	private Hyperlink save;
	IconGallery iconGallery=new IconGallery();
	
	public Product()
	{
	
	}
	public Object clone()
	{
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new Product();
	}
	public Product(int prodId, String nameOfProduct,
			double wholesale_Price, double retail_Price, String descriptionOfProduct,
			int stockOfProduct, double Discount, Hyperlink delete) {
		
		this.productId = new SimpleIntegerProperty(prodId);
		this.name = new SimpleStringProperty(nameOfProduct);
		
		this.wholesalePrice =new SimpleDoubleProperty( wholesale_Price);
		this.retailPrice = new SimpleDoubleProperty(retail_Price);
		this.description = new SimpleStringProperty(descriptionOfProduct);
		this.stock = new SimpleDoubleProperty(stockOfProduct);
		this.discount =new SimpleDoubleProperty (Discount);
		this.delete = new Hyperlink();
		this.delete.setGraphic(iconGallery.getDeleteIcon());
		this.delete.setStyle("-fx-text-fill: #606060;");
		this.save=new Hyperlink();
		this.save.setGraphic(iconGallery.getSaveIcon());
		this.save.setStyle("-fx-text-fill: #606060;");
		this.action=new HBox(delete,save);
	}
	public int getProductId() {
		return productId.get();
	}
	public void setProductId(SimpleIntegerProperty prodId) {
		this.productId=prodId;
	}
	public String getName() {
		return name.get();
	}
	public void setName(SimpleStringProperty nameOfProduct) {
		this.name=nameOfProduct;
	}
	public double getWholesalePrice() {
		return wholesalePrice.get();
	}
	public void setWholesalePrice(SimpleDoubleProperty wholesale_Price) {
		this.wholesalePrice=wholesale_Price;
	}
	public double getRetailPrice() {
		return retailPrice.get();
	}
	public void setRetailPrice(SimpleDoubleProperty retail_Price) {
		this.retailPrice=retail_Price;
	}
	public String getDescription() {
		return description.get();
	}
	public void setDescription(SimpleStringProperty descriptionOfProduct) {
		this.description=descriptionOfProduct;
	}

	public Double getStock() {
		return stock.get();
	}
	public void setStock(SimpleDoubleProperty stock) {
		this.stock = stock;
	}
	public double getDiscount() {
		return discount.get();
	}
	public void setDiscount(SimpleDoubleProperty Discount) {
		this.discount=Discount;
	}
	
	public Hyperlink getDelete() {
		if(delete==null)
		{
		 delete=new Hyperlink();
		 delete.setGraphic(iconGallery.getDeleteIcon());
		 delete.setTooltip(iconGallery.getDeleteToolTip());
		 this.delete.setStyle("-fx-text-fill: #606060;");
		}
		return delete;
	}
	public void setDelete(Hyperlink delete) {
		this.delete = delete;
	}
	public Hyperlink getSave() {
		if(save==null)
		{
			save=new Hyperlink();
			save.setGraphic(iconGallery.getSaveIcon());
			save.setTooltip(iconGallery.getSaveToolTip());
			
			this.save.setStyle("-fx-text-fill: #606060;");
		}
		
		return save;
	}
	public void setSave(Hyperlink save) {
		this.save = save;
	}
	
	public HBox getAction() {
		if(action==null)
		{
			delete=getDelete();
			save=getSave();
			action=new HBox(delete,save);
			
		}
		return action;
	}
	public void setAction(HBox action) {
		this.action = action;
	}


	public Double getQuantity() {
		return quantity.get();
	}
	public void setQuantity(SimpleDoubleProperty quantity) {
		this.quantity = quantity;
	}
	public int getSerialNumber() {
		return serialNumber.get();
	}
	public void setSerialNumber(SimpleIntegerProperty serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Double getTotalPrice() {
		return totalPrice.get();
	}
	public void setTotalPrice(SimpleDoubleProperty totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getProductCategory() {
		return productCategory.get();
	}
	public void setProductCategory(SimpleStringProperty productCategory) {
		this.productCategory = productCategory;
	}
	
	
	
	public String getWholeSaleGST() {
		return wholeSaleGST.get();
	}
	public void setWholeSaleGST(SimpleStringProperty wholeSaleGST) {
		this.wholeSaleGST = wholeSaleGST;
	}
	public Double getWholeSaleGSTpercentage() {
		return wholeSaleGSTpercentage.get();
	}
	public void setWholeSaleGSTpercentage(SimpleDoubleProperty wholeSaleGSTpercentage) {
		this.wholeSaleGSTpercentage = wholeSaleGSTpercentage;
	}
	public String getRetailGST() {
		return retailGST.get();
	}
	public void setRetailGST(SimpleStringProperty retailGST) {
		this.retailGST = retailGST;
	}
	public Double getRetailGSTpercentage() {
		return retailGSTpercentage.get();
	}
	public void setRetailGSTpercentage(SimpleDoubleProperty retailGSTpercentage) {
		this.retailGSTpercentage = retailGSTpercentage;
	}
	public String getProductHSNCode() {
		return productHSNCode.get();
	}
	public void setProductHSNCode(SimpleStringProperty productHSNCode) {
		this.productHSNCode = productHSNCode;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public Double getMrpPrice() {
		return mrpPrice.get();
	}
	public void setMrpPrice(SimpleDoubleProperty mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	public Double getRatePrice() {
		return ratePrice.get();
	}
	public void setRatePrice(SimpleDoubleProperty ratePrice) {
		this.ratePrice = ratePrice;
	}
	public Long getProductCode() {
		return productCode.get();
	}
	public void setProductCode(SimpleLongProperty productCode) {
		this.productCode = productCode;
	}	
	public Double getWholeSaleGSTPrice() {
		return wholeSaleGSTPrice.get();
	}
	public void setWholeSaleGSTPrice(SimpleDoubleProperty wholeSaleGSTPrice) {
		this.wholeSaleGSTPrice = wholeSaleGSTPrice;
	}
	
}
