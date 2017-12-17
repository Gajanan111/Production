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
		
		if(getSupModel()==null) {
			supplierService.addNewSupplier(sup);
		}else {
			sup.setSupplierID(new SimpleIntegerProperty(getSupModel().getSupplierID()));
			supplierService.updateSupplier(sup);	
			setSupModel(null);
		}
		
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		ManageSupplierController manageSupplirController=(ManageSupplierController)applicationContext.getBean("ManageSupplierController");
		manageSupplirController.getRefreshedTable();
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
}
