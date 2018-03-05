package com.billdiary.ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.hibernate.type.descriptor.java.LocalDateJavaDescriptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.billdiary.model.Address;
import com.billdiary.model.Customer;
import com.billdiary.model.Supplier;
import com.billdiary.service.SupplierService;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

@Controller("PurchaseOrderController")
public class PurchaseOrderController implements Initializable{
	
	
	@Autowired
	SupplierService supplierService;
	
	@FXML TextField supplierIdName;
	@FXML DatePicker orderDate;
	@FXML TextArea address;
	@FXML TextField mobileNo;
	@FXML TextField city;
	@FXML TextField gstNo;
	
	
	
	public Supplier selectedSupplier;
	public List<Supplier> supplierList=new ArrayList<>();
	public List<String> supplierNameList=new ArrayList<>();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		supplierList.clear();
		
		try {
			supplierList=supplierService.fetchSuppliers();
			for(Supplier supplier:supplierList) {
				supplierNameList.add(supplier.getSupplierID()+" : "+supplier.getSupplierName());
			}
			TextFields.bindAutoCompletion(supplierIdName, supplierNameList).setVisibleRowCount(5);
			orderDate.setValue(LocalDate.now());
			supplierIdName.focusedProperty().addListener((ov, oldV, newV) -> {
				if (!newV) {
					selectSupplier();
				}
			});
			
			
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	private void selectSupplier() {
		String supp=supplierIdName.getText();
		if (null != supp) {
			selectedSupplier = supplierList.stream()
					.filter(x -> (x.getSupplierID()+" : "+x.getSupplierName()).equals(supp)).findAny()
					.orElse(null);
			if (null != selectedSupplier) {
				Address addr=selectedSupplier.getAddress();
				address.setText(addr.getStreet1());
				mobileNo.setText(selectedSupplier.getSupplierMobileNo());
				city.setText(addr.getCity());
				gstNo.setText(selectedSupplier.getSupplierTaxRegNo());
			}
		}
		
	}

	@FXML public void showAddProduct() {
		
	}

}
