package com.billdiary.ui;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.model.Supplier;
import com.billdiary.service.SupplierService;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller("AddSupplierController")
public class AddSupplierController {
	
	
	@Autowired
	SupplierService supplierService;

	@FXML
	TextField supplierName;
	@FXML
	TextArea supplierAddress;
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
	ChoiceBox<?> supplierCity;
	
	@FXML
	TextField supplierEmailID;
	@FXML 
	ChoiceBox<?> supplierCountry;
	@FXML 
	ChoiceBox<?> supplierState;
	@FXML
	TextArea supplierOtherInfo;
	@FXML
	TextField supplierZipCode;
	@FXML
	TextField supplierCompany;
	@FXML
	DatePicker asOfDate;
	
	@FXML
	public void addSupplier() {
		
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
		double supplUnpaidBalance=0.0;
		if(!supplierUnpaidBalance.getText().isEmpty()) {
	           supplUnpaidBalance=Double.parseDouble(supplierUnpaidBalance.getText());
	    }else {
	    	supplUnpaidBalance=0.0;
	    }
		LocalDate supplAsOfDate=asOfDate.getValue();
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
		sup.setSupplierAsOfDate(asOfDate);
		
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
		
		supplierService.addNewSupplier(sup);
		
	}
}
