package com.billdiary.ui;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Address;
import com.billdiary.model.Supplier;
import com.billdiary.service.SupplierService;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller("AddSupplierController")
public class AddSupplierController implements Initializable{
	
	
	@Autowired
	SupplierService supplierService;
	
	public Supplier supModel;
	@FXML
	TextField supplierName;
	
	@FXML
	TextField supplierPhoneNO;
	@FXML
	TextField supplierGovID;
	@FXML
	TextField supplierFaxNO;
	@FXML
	TextField supplierMobileNO;
	@FXML
	TextField supplierwebsite;
	@FXML
	TextField  supplierUnpaidBalance;
	@FXML
	TextField supplierAccountNO;
	@FXML
	TextField supplierTaxRegNO;
	@FXML
	TextField supplierBillingRate;
	@FXML
	TextField supplierEmailID;
	@FXML
	TextArea supplierOtherInfo;
	
	@FXML
	TextField supplierCompany;
	@FXML
	DatePicker asOfDate;
	
	/**
	 * Address Fields
	 */
	@FXML
	TextArea supplierAddress;
	@FXML 
	ChoiceBox<String> supplierCity;
	@FXML 
	ChoiceBox<String> supplierCountry;
	@FXML 
	ChoiceBox<String> supplierState;
	@FXML
	TextField supplierZipCode;
	
	
	@FXML
	public void addSupplier(ActionEvent event) {
		
		//String  supplID=
		String supplName=supplierName.getText();
		String supplCompany=supplierCompany.getText();
		String supplAddress=supplierAddress.getText();
		String supplGovID=supplierGovID.getText();
		String supplEmailID=supplierEmailID.getText();
		String  supplPhoneNo=supplierPhoneNO.getText();
		String  supplMobileNo=supplierMobileNO.getText();
		String supplFaxNo=supplierFaxNO.getText();
		String supplWebsite=supplierwebsite.getText();
		String street=supplierAddress.getText();
		String city=(String) supplierCity.getValue();
		String country=(String) supplierCountry.getValue();
		String state=(String)supplierState.getValue();
		String zipcode=supplierZipCode.getText();
		
		double supplUnpaidBalance=0.0;
		if(!supplierUnpaidBalance.getText().isEmpty()) {
	           supplUnpaidBalance=Double.parseDouble(supplierUnpaidBalance.getText());
	    }else {
	    	supplUnpaidBalance=0.0;
	    }
		LocalDate supplAsOfDate;
		if(asOfDate.getValue()==null) {
			supplAsOfDate=null;
		}else {
			supplAsOfDate=asOfDate.getValue();
		}
		
		
		String supplAccountNo=supplierAccountNO.getText();
		String supplTaxRegNo=supplierTaxRegNO.getText();
		String  supplBillingRate;
		if(supplierBillingRate.getText().isEmpty()) {
			supplBillingRate="0";
		}else{
			supplBillingRate=supplierBillingRate.getText();
		}
		String supplOther=supplierOtherInfo.getText();
		
		/*System.out.println(
						supplName
						+" "+supplCompany
						+" "+supplAddress
						+" "+supplGovID
						+" "+supplEmailID
						+" "+ supplPhoneNo
						+" "+ supplMobileNo
						+" "+supplFaxNo
						+" "+supplWebsite
						+" "+supplUnpaidBalance
						+" "+supplAsOfDate
						+" "+supplAccountNo
						+" "+supplTaxRegNo
						+" "+ supplBillingRate
						+" "+supplOther
						);*/
		Supplier sup=new Supplier();
		sup.setSupplierName(new SimpleStringProperty(supplName));
		sup.setSupplierCompany(new SimpleStringProperty(supplCompany));
		sup.setSupplierAddress(new SimpleStringProperty(supplAddress));
		sup.setSupplierAccountNo(new SimpleStringProperty(supplAccountNo));
		sup.setSupplierAsOfDate(supplAsOfDate);
		
		sup.setSupplierBillingRate(new SimpleDoubleProperty(Double.parseDouble(supplBillingRate)));
		sup.setSupplierEmailID(new SimpleStringProperty(supplEmailID));
		sup.setSupplierGovID(new SimpleStringProperty(supplGovID));
		sup.setSupplierFaxNo(new SimpleStringProperty(supplFaxNo));
		sup.setSupplierWebsite(new SimpleStringProperty(supplWebsite));
		sup.setSupplierUnpaidBalance(new SimpleDoubleProperty(supplUnpaidBalance));
		sup.setSupplierTaxRegNo(new SimpleStringProperty(supplTaxRegNo));
		sup.setSupplierOther(new SimpleStringProperty(supplOther));
		sup.setSupplierMobileNo(new SimpleStringProperty(supplMobileNo));
		sup.setSupplierPhoneNo(new SimpleStringProperty(supplPhoneNo));
		
		Address address=new Address();
		address.setStreet1(supplAddress);
		address.setCity(city);
		address.setState(state);
		address.setCountry(country);
		address.setZipcode(zipcode);
		sup.setAddress(address);
		
		
		if(getSupModel()==null) {
			supplierService.addNewSupplier(sup);
			
		}else {
			sup.setSupplierID(new SimpleIntegerProperty(getSupModel().getSupplierID()));
			supplierService.updateSupplier(sup);	
			
		}
		
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		ManageSupplierController manageSupplirController=(ManageSupplierController)applicationContext.getBean("ManageSupplierController");
		manageSupplirController.getRefreshedTable();
		setSupModel(null);
		((Node)(event.getSource())).getScene().getWindow().hide();	
	}
	
	public Supplier getSupModel() {
		return supModel;
	}

	public void setSupModel(Supplier supModel) {
		this.supModel = supModel;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(null!=supModel) {
			supplierName.setText(supModel.getSupplierName());
			supplierAddress.setText(supModel.getSupplierAddress());
			supplierPhoneNO.setText(supModel.getSupplierPhoneNo()); 
			supplierGovID.setText(supModel.getSupplierGovID()); 
			supplierFaxNO.setText(supModel.getSupplierFaxNo());
			supplierMobileNO.setText(supModel.getSupplierMobileNo());
			supplierwebsite.setText(supModel.getSupplierWebsite());
			supplierUnpaidBalance.setText(String.valueOf(supModel.getSupplierUnpaidBalance()));
		    supplierAccountNO.setText(supModel.getSupplierAccountNo());
			supplierTaxRegNO.setText(supModel.getSupplierTaxRegNo());
			supplierBillingRate.setText(String.valueOf(supModel.getSupplierBillingRate()));
			supplierEmailID.setText(supModel.getSupplierEmailID());
			supplierOtherInfo.setText(supModel.getSupplierOther());
			//supplierZipCode.setText(supModel.getZipcode);
			supplierCompany.setText(supModel.getSupplierCompany());
			
			if(supModel.getAddress()!=null) {
				supplierZipCode.setText(supModel.getAddress().getZipcode());
				//supplierCity.setValue(supplierCity.getConverter().toString(supModel.getAddress().getCity()));
					supplierCity.setValue(supModel.getAddress().getCity());
					supplierCountry.setValue(supModel.getAddress().getCountry());
					supplierState.setValue(supModel.getAddress().getState());
					supplierAddress.setText(supModel.getAddress().getStreet1());
				System.out.println("city selected"+supModel.getAddress().getCity());
				//supplierCity.getSelectionModel().select(supModel.getAddress().getCity());
				
				
				
			}
			
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date asOfD=null;
			try {
				if(""!=supModel.getSupplierAsOfDate()) {
				asOfD = df.parse(supModel.getSupplierAsOfDate());
				asOfDate.setValue(asOfD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				}
			} catch (ParseException e) {
				System.out.println("Exception while date parsing"+e.getMessage());
			
			}
			
		}
		
	}
	
	public void cleanFields() {
		supplierName.setText("");
		supplierAddress.setText("");
		supplierPhoneNO.setText(""); 
		supplierGovID.setText(""); 
		supplierFaxNO.setText("");
		supplierMobileNO.setText("");
		supplierwebsite.setText("");
		supplierUnpaidBalance.setText("");
	    supplierAccountNO.setText("");
		supplierTaxRegNO.setText("");
		supplierBillingRate.setText("");
		supplierEmailID.setText("");
		supplierOtherInfo.setText("");
		//supplierZipCode.setText(supModel.getZipcode);
		supplierCompany.setText("");
		asOfDate.setValue(null);
	}
}
