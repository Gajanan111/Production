package com.billdiary.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Product;
import com.billdiary.model.Supplier;
import com.billdiary.service.SupplierService;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;

@Controller("ManageSupplierController")
public class ManageSupplierController implements Initializable {

	
	
	@Autowired
	public LayoutController layoutController;
	@Autowired
	public SupplierService supplierService;
	
	@FXML
	TextField supplierName;
	@FXML
	TextField supplierID;
	@FXML
	TextField suppliermobileNo;
	
	@FXML
	private TableView<Supplier> supplierTable;
	@FXML
	TableColumn<Supplier, Double> supplierUnpaidBalance;
	@FXML
	TableColumn<Supplier, Double> supplierBillingRate;
	
	List<Supplier> supplierList = new ArrayList<>();
	
	
	private ObservableList<Supplier> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		supplierTable.setItems(data);
		System.out.println("f");
		populate(retrieveData());
		System.out.println("ff");
		supplierUnpaidBalance.setCellFactory(TextFieldTableCell.<Supplier, Double>forTableColumn(new DoubleStringConverter()));
		supplierBillingRate.setCellFactory(TextFieldTableCell.<Supplier, Double>forTableColumn(new DoubleStringConverter()));
	}

	private void populate(final List<Supplier> suppliers) {
		if (data.isEmpty()) {
			for (Supplier sup : suppliers) {
				data.add(sup);
				int supID = sup.getSupplierID();
				int index = data.indexOf(sup);
				//cust.getDeleteHyperlink().setOnAction(e -> deleteButtonClickedThroughHyperlink(custID));
				//cust.getSaveHyperlink().setOnAction(e -> saveButtonClickedThroughHyperlink(custID, index));
				sup.getDeleteHyperlink().setOnAction(e -> deleteButtonClickedThroughHyperlink(supID));
				sup.getSaveHyperlink().setOnAction(e -> saveButtonClickedThroughHyperlink(supID, index));
			}
		}
	}
	private void saveButtonClickedThroughHyperlink(int supID, int index) {
		Supplier sup=data.get(index);
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		AddSupplierController addSupplierController=(AddSupplierController)applicationContext.getBean("AddSupplierController");
		addSupplierController.setSupModel(sup);
		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		StackPane addSupplier = (StackPane) loader.load(URLS.ADD_SUPPLIER);
		BorderPane root = new BorderPane();
		root.setCenter(addSupplier);
		layoutController.loadWindow(root, "Update Supplier Details", Constants.POPUP_WINDOW_WIDTH,
				Constants.POPUP_WINDOW_HEIGHT);
	
	}

	private void deleteButtonClickedThroughHyperlink(int supID) {
		supplierService.removeSupplier(supID);
		getRefreshedTable();
	}

	private List<Supplier> retrieveData() {
		try {
			if (supplierList.isEmpty()) {
			//	supplierList = customerService.fetchCustomers();
				supplierList = supplierService.fetchSuppliers();
			}
			return supplierList;

		} catch (Exception e) {
			System.out.println("ManageSupplierController" + e.getMessage());
		}
		return new ArrayList<Supplier>();
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
	public void getRefreshedTable() {
		supplierList.clear();
		data.clear();
		supplierTable.setItems(data);
		populate(retrieveData());
	}

}
