package com.billdiary.ui;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Customer;
import com.billdiary.service.CustomerService;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


@Controller("AddCustomerController")
public class AddCustomerController implements Initializable{

	
	public String parentName;
	
	private Date regDate;
	
	@Autowired
	private CustomerService customerService;
	@FXML
	TextField addCustomerName;
	@FXML
	TextArea addAddress;
	@FXML
	TextField addMobileNo;
	@FXML
	ChoiceBox<?> addCity;
	@FXML
	TextField addEmailID;
	@FXML
	ChoiceBox<?> addCountry;
	@FXML
	ChoiceBox<?> addState;
	@FXML 
	TextArea addAdditionalInfo;
	@FXML
	ChoiceBox<?> addCustomerGroup;
	@FXML
	TextField addZipCode;
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass());
		System.out.println(this.getClass().getSuperclass());
	}

	
	@FXML
	public void addCustomer(ActionEvent event){
		String customerName=addCustomerName.getText();
		String address=addAddress.getText();
		String mobileNO=addMobileNo.getText();
		String city=(String)addCity.getValue();
		String emailID=addEmailID.getText();
		String country=(String)addCountry.getValue();
		String customerGroup=(String)addCustomerGroup.getValue();
		String zipCode=addZipCode.getText();
		String additionalInfo=addAdditionalInfo.getText();
		String state=(String)addState.getValue();
		regDate=new Date();
		
		if(customerName!=null && address!=null && mobileNO!=null && city!=null && emailID!=null && country!=null
				&& null!=customerGroup && null!=zipCode && null!=additionalInfo && null!=state)
		{
			System.out.println(customerName+" "+address+" "+mobileNO+" "+city+" "+emailID+" "+country);
			Customer cust=new Customer();
			cust.setCustomerName(new SimpleStringProperty(customerName));
			cust.setAddress(new SimpleStringProperty(address));
			cust.setMobile_no(new SimpleStringProperty(mobileNO));
		    cust.setCity(new SimpleStringProperty(city));
		    cust.setEmailID(new SimpleStringProperty(emailID));
		    cust.setCountry(new SimpleStringProperty(country));
		    cust.setState(new SimpleStringProperty(state));
		    cust.setZipCode(new SimpleStringProperty(zipCode));
		    cust.setCustomerGroup(new SimpleStringProperty(customerGroup));
		    cust.setAddAdditionalInfo(new SimpleStringProperty(additionalInfo));
		    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		    String strDate = dateFormat.format(regDate);
		    cust.setRegistrationDate(new SimpleStringProperty(strDate));
		    
			customerService.addCustomer(cust);
			
			((Node)(event.getSource())).getScene().getWindow().hide();
			
			System.out.println("ParentController of addcustomer : "+this.parentName);
			if(null!=this.parentName) {
			if(this.parentName.equals("CustomerController")) {
				ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
				ManageCustomerController manageCustomer=(ManageCustomerController) applicationContext.getBean("ManageCustomerController");
				manageCustomer.getRefreshedTable();
			}else if(this.parentName.equals("InvoiceController")) {
				ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
				ManageInvoiceController manageInvoiceController=(ManageInvoiceController) applicationContext.getBean("ManageInvoiceController");
				manageInvoiceController.refreshCustomerList();;
			}
			
			
			}
		
		}	
	}
	
	

	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}
