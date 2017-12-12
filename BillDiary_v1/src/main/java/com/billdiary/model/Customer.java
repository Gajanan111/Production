package com.billdiary.model;



import java.util.ArrayList;
import java.util.List;

import org.controlsfx.glyphfont.FontAwesome;
import org.springframework.beans.factory.annotation.Autowired;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.utility.GeneralUitilies;
import com.billdiary.utility.IconGallery;
import com.billdiary.utility.URLS;

import aj.org.objectweb.asm.Label;
import de.jensd.fx.glyphs.GlyphsBuilder;
import de.jensd.fx.glyphs.GlyphsStack;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;


public class Customer {
	
	
	
	@Autowired
	GeneralUitilies generalUtilities;
	
//	private Image image;

	//Label iconLabel = GlyphsDude.createIcon( FontAwesomeIcons.BARS,&quot;40px&quot; );
	/*
	Region r=GlyphsStack.create().add(
			GlyphsBuilder.create(FontAwesome.class).icon(FontAwesomeIcon.SAVE).sizse("10em").build()
			);
	*/
	
	/*
	public Image getImage() {
		if(null==image)
		{
			try {
				GeneralUitilies generalUtilities=new GeneralUitilies();
			image=new Image(generalUtilities.getFile("/images/deleteIcon.jpg").toURI().toString(),15,20,false,false);
			}
			catch(Exception e)
			{
				System.out.println("image "+ e.getMessage());
			}
		}
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}*/
	
	/**
	 * These fields are only for tableview purpose
	 * 
	 */
	private SimpleIntegerProperty customerID;
	private SimpleStringProperty customerName;
	private SimpleStringProperty customerGroup;
	private SimpleStringProperty registrationDate;
	private SimpleStringProperty address;
	private SimpleStringProperty emailID;
	private SimpleStringProperty mobile_no;
	private SimpleStringProperty city;
	private SimpleStringProperty state;
	private SimpleStringProperty country;
	private SimpleStringProperty zipCode;
	private SimpleStringProperty addAdditionalInfo;
	
	
	
	
	
	IconGallery iconGallery=new IconGallery();
	
	//@Autowired
	//IconGallery iconGallery;
	
	private HBox actionbox;
	
	
	public HBox getActionbox() {
		if(actionbox==null) {
			deleteHyperlink=getDeleteHyperlink();
			saveHyperlink=getSaveHyperlink();
			actionbox=new HBox(deleteHyperlink,saveHyperlink);
		}
		
		return actionbox;
	}

	public void setActionbox(HBox actionbox) {
		this.actionbox = actionbox;
	}


	private Hyperlink deleteHyperlink;
	private Hyperlink saveHyperlink;
	private List<Hyperlink> hyperlinks =new ArrayList<>();

	public List<Hyperlink> getHyperlinks() {
		
		if(hyperlinks.isEmpty())
		{
			
			hyperlinks.add(getDeleteHyperlink());
			hyperlinks.add(getSaveHyperlink());
		}
		return hyperlinks;
	}

	public void setHyperlinks(List<Hyperlink> hyperlinks) {
		this.hyperlinks = hyperlinks;
	}

	public Hyperlink getSaveHyperlink() {
		if(saveHyperlink==null)
		{
			saveHyperlink=new Hyperlink();
			try {
			saveHyperlink.setGraphic(iconGallery.getSaveIcon());
			saveHyperlink.setTooltip(iconGallery.getSaveToolTip());
			this.saveHyperlink.setStyle("-fx-text-fill: #606060;");
			
			}
			catch(Exception e)
			{
				System.out.println("in saveICon "+e.getMessage());
			}
		}
		return saveHyperlink;
	}

	public void setSaveHyperlink(Hyperlink saveHyperlink) {
		this.saveHyperlink = saveHyperlink;
		
	}

	public Hyperlink getDeleteHyperlink() {
		if(deleteHyperlink==null)
		{
			deleteHyperlink=new Hyperlink();
			//fontAwesomeTrashIconView.setSize("1.5em");
			
			
			deleteHyperlink.setGraphic(iconGallery.getDeleteIcon());
			deleteHyperlink.setTooltip(iconGallery.getDeleteToolTip());
		}
		return deleteHyperlink;
	}

	public void setDeleteHyperlink(Hyperlink deleteHyperlink) {
		this.deleteHyperlink = deleteHyperlink;
	}

	public String getAddress() {
		return address.get();
	}

	public String getMobile_no() {
		return mobile_no.get();
	}

	public String getCity() {
		return city.get();
	}

	public String getCountry() {
		return country.get();
	}
	
	public String getCustomerName() {
		return customerName.get();
	}

	public Integer getCustomerID() {
		return customerID.get();
	}

	public String getEmailID() {
		return emailID.get();
	}

	public void setEmailID(SimpleStringProperty emailID) {
		this.emailID = emailID;
	}

	
	public void setCustomerName(SimpleStringProperty customerName) {
		this.customerName = customerName;
	}

	public void setCustomerID(SimpleIntegerProperty customerID) {
		this.customerID = customerID;
	}

	public void setAddress(SimpleStringProperty address) {
		this.address = address;
	}

	public void setMobile_no(SimpleStringProperty mobile_no) {
		this.mobile_no = mobile_no;
	}

	public void setCity(SimpleStringProperty city) {
		this.city = city;
	}

	public void setCountry(SimpleStringProperty country) {
		this.country = country;
	}

	public String getCustomerGroup() {
		if(null==customerGroup)
		{
			return "";
		}
		else
		return customerGroup.get();
	}

	public void setCustomerGroup(SimpleStringProperty customerGroup) {
		this.customerGroup = customerGroup;
	}

	public String getState() {
		if(null==state)
		{
			return "";
		}
		else
		return state.get();
	}

	public void setState(SimpleStringProperty state) {
		this.state = state;
	}

	public String getZipCode() {
		if(null==zipCode)
		{
			return "";
		}
		else
		return zipCode.get();
		
	}

	public void setZipCode(SimpleStringProperty zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddAdditionalInfo() {
		if(null==addAdditionalInfo)
		{
			return "";
		}
		else
		return addAdditionalInfo.get();
		
	}

	public void setAddAdditionalInfo(SimpleStringProperty addAdditionalInfo) {
		this.addAdditionalInfo = addAdditionalInfo;
	}
	public String getRegistrationDate() {
		return registrationDate.get();
	}

	public void setRegistrationDate(SimpleStringProperty registrationDate) {
		this.registrationDate = registrationDate;
	}


	
	public Customer(CustomerEntity customerEnitity)
	{
		this.customerID=new SimpleIntegerProperty(customerEnitity.getCustomerID());
		this.customerName=new SimpleStringProperty(customerEnitity.getCustomerName());
		this.address=new SimpleStringProperty(customerEnitity.getAddress());
		this.emailID=new SimpleStringProperty(customerEnitity.getEmailID());
		this.city=new SimpleStringProperty(customerEnitity.getCity());
		this.country=new SimpleStringProperty(customerEnitity.getCountry());
		this.mobile_no=new SimpleStringProperty(customerEnitity.getMobile_no());
	//	this.image=new Image(generalUtilities.getFileAsInputStream (URLS.SAVE_IMAGE));
		this.deleteHyperlink=new Hyperlink("Delete");
		this.deleteHyperlink.setStyle("-fx-text-fill: #606060;");
		this.saveHyperlink=new Hyperlink("Save");
		this.saveHyperlink.setStyle("-fx-text-fill: #606060;");
		this.hyperlinks=new ArrayList<>();
		this.hyperlinks.add(getDeleteHyperlink());
		this.hyperlinks.add(getSaveHyperlink());
		
		this.actionbox=getActionbox();
		
		
	}
	
	public Customer(final int customerID,final String customerName)
	{
		this.customerID=new SimpleIntegerProperty(customerID);
		this.customerName=new SimpleStringProperty(customerName);
	}
	public Customer()
	{
		
	}
	
}
