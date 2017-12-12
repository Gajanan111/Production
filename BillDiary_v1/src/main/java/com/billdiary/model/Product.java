package com.billdiary.model;





import org.springframework.stereotype.Component;

import com.billdiary.utility.IconGallery;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;


@Component
public class Product {
	
	private SimpleIntegerProperty serialNumber;
	
	private SimpleDoubleProperty totalPrice;
	
	
	private SimpleIntegerProperty productId;
	
	private  SimpleStringProperty name;
	
	private SimpleDoubleProperty wholesalePrice;
	
	private SimpleDoubleProperty retailPrice;
	
	private SimpleStringProperty description;
	
	private SimpleIntegerProperty stock;
	
	private SimpleDoubleProperty discount;
	
	private SimpleIntegerProperty quantity;
	
	private HBox action;
	
	
	private Hyperlink delete;
	
	private Hyperlink save;
	IconGallery iconGallery=new IconGallery();
	
	public Product()
	{
	
	}
	public Product(int prodId, String nameOfProduct,
			double wholesale_Price, double retail_Price, String descriptionOfProduct,
			int stockOfProduct, double Discount, Hyperlink delete) {
		
		this.productId = new SimpleIntegerProperty(prodId);
		this.name = new SimpleStringProperty(nameOfProduct);
		this.wholesalePrice =new SimpleDoubleProperty( wholesale_Price);
		this.retailPrice = new SimpleDoubleProperty(retail_Price);
		this.description = new SimpleStringProperty(descriptionOfProduct);
		this.stock = new SimpleIntegerProperty(stockOfProduct);
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
	public int getStock() {
		return stock.get();
	}
	public void setStock(SimpleIntegerProperty stockOfProduct) {
		this.stock=stockOfProduct;
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

	public int getQuantity() {
		return quantity.get();
	}
	public void setQuantity(SimpleIntegerProperty quantity) {
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
}
