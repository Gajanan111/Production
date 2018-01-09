package com.billdiary.ui;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;


import org.controlsfx.validation.ValidationSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.javafxUtility.ControlFXValidation;
import com.billdiary.javafxUtility.Popup;
import com.billdiary.model.Customer;
import com.billdiary.service.CustomerService;
import com.billdiary.utility.Constants;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


@Controller("AddCustomerController")
public class AddCustomerController implements Initializable{

	
	ValidationSupport support=new ValidationSupport();
	
	@Autowired 
	ControlFXValidation controlFXValidation;
	
	public Customer custModel;
	public Customer getCustModel() {
		return custModel;
	}
	public void setCustModel(Customer custModel) {
		this.custModel = custModel;
	}
	
	
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
	ChoiceBox<String> addCity;
	@FXML
	TextField addEmailID;
	@FXML
	ChoiceBox<String> addCountry;
	@FXML
	ChoiceBox<String> addState;
	@FXML 
	TextArea addAdditionalInfo;
	@FXML
	ChoiceBox<String> addCustomerGroup;
	@FXML
	TextField addZipCode;
	Integer cust_id;
	@FXML
	DatePicker Anniversary_Date;
	@FXML
	DatePicker Birth_Date;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println(this.getClass());
		System.out.println(this.getClass().getSuperclass());
		support.registerValidator( addCustomerName, true, controlFXValidation.getStringValidator() );
		if(custModel!=null)
		{	
		addCustomerName.setText(custModel.getCustomerName());
		addAdditionalInfo.setText(custModel.getAddAdditionalInfo());
		addAddress.setText(custModel.getAddress());
		addCity.setValue(custModel.getCity());
		addCountry.setValue(custModel.getCountry());
		addCustomerGroup.setValue(custModel.getCustomerGroup());
		addEmailID.setText(custModel.getEmailID());
		addMobileNo.setText(custModel.getMobile_no());
		addState.setValue(custModel.getState());;
		addZipCode.setText(custModel.getZipCode());
		Anniversary_Date.setValue(custModel.getAnniversary_date());
		Birth_Date.setValue(custModel.getBirth_date());
	    cust_id=custModel.getCustomerID();
		}
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
		LocalDate anni_date=Anniversary_Date.getValue();
		LocalDate birth_date=Birth_Date.getValue();
		String additionalInfo=addAdditionalInfo.getText();
		String state=(String)addState.getValue();
		regDate=new Date();
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
		cust.setAnniversary_date(anni_date);
	    cust.setBirth_date(birth_date);
	    cust.setCustomerGroup(new SimpleStringProperty(customerGroup));
	    cust.setAddAdditionalInfo(new SimpleStringProperty(additionalInfo));
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    String strDate = dateFormat.format(regDate);
	    cust.setRegistrationDate(new SimpleStringProperty(strDate));
	    /**
	     * All customer Customer validation 
	     */
	    if(validateCustomer(cust))
	    {
		    if(custModel==null)
				customerService.addCustomer(cust);
			else{ 
				cust.setCustomerID(new SimpleIntegerProperty(custModel.getCustomerID()));
			    customerService.updateCustomer(cust);
			    }
			 setCustModel(null);
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
	
	private boolean validateCustomer(Customer cust) {
		boolean valid=true;
		if(null==cust.getCustomerName() || cust.getCustomerName().isEmpty()) {
			valid=false;
			Popup.showErrorAlert(Constants.ERROR_TITLE,Constants.ERROR_CUSTOMER_VALIDATION,AlertType.ERROR);
		}
		
		return valid;
		
	}
	public void clearFields() {
		addAdditionalInfo.setText(null);
		addAddress.setText(null);
		addCity.setValue(null);
		addCountry.setValue(null);
		addCustomerGroup.setValue(null);
		addCustomerName.setText(null);
		addEmailID.setText(null);
		addMobileNo.setText(null);
		addState.setValue(null);
		addZipCode.setText(null);
		Anniversary_Date.setValue(null);
		Birth_Date.setValue(null);
	}

	public String getParentName() {
		return parentName;
	}


	public void setParentName(String parentName) {
		this.parentName = parentName;
	}


}
