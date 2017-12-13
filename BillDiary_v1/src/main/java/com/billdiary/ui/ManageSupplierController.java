package com.billdiary.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Supplier;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

@Controller("ManageSupplierController")
public class ManageSupplierController implements Initializable {

	
	
	@Autowired
	public LayoutController layoutController;
	
	@FXML
	TextField supplierName;
	@FXML
	TextField supplierID;
	@FXML
	TextField suppliermobileNo;
	
	@FXML
	private TableView<Supplier> supplierTable;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	
	@FXML
	public void showAddSupplier() {
		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		// ResourceBundle bundle = ResourceBundle.getBundle("resources.UIResources");
		StackPane addSupplier = (StackPane) loader.load(URLS.ADD_SUPPLIER);
		ApplicationContext applicationContext = SpringFxmlLoader.getApplicationcontext();
		AddSupplierController addSupplierController = (AddSupplierController) applicationContext
				.getBean("AddSupplierController");
		//addCustomerController.setParentName("ManageSupplierController");
		BorderPane root = new BorderPane();
		root.setCenter(addSupplier);
		layoutController.loadWindow(root, "Add Supplier Details", Constants.POPUP_WINDOW_WIDTH,
				Constants.POPUP_WINDOW_HEIGHT);
	}
	
	@FXML 
	public void searchSupplier() {
		
	}

}
