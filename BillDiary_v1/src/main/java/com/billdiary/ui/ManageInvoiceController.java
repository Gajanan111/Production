package com.billdiary.ui;

import java.net.URL;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;

import com.billdiary.model.Customer;
import com.billdiary.model.Product;
import com.billdiary.service.CustomerService;
import com.billdiary.service.ProductService;
import com.billdiary.utility.Calculate;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.DatePicker;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

@Controller("ManageInvoiceController")
public class ManageInvoiceController implements Initializable {

	@Autowired
	public LayoutController layoutController;
	
	@Autowired
	public Calculate calculate;

	@FXML
	TextField totalAmount;
	@FXML
	TextField bigFinalAmount;
	@FXML
	TextField amountDue;
	@FXML
	TextField discount;
	@FXML
	TextField finalAmount;
	@FXML
	TextArea invAddress;
	@FXML
	TextField invMobileNo;
	@FXML
	Text invNO;
	@FXML
	Text invDate;
	@FXML
	DatePicker invDueDate;
	@FXML
	TextField invCustName;
	@FXML
	TextField invProductName;
	@FXML
	TextField invProductQuantity;
	@FXML
	TextField invProductPrice;

	/** All table related **/
	@FXML
	TableView<Product> productTable;
	private ObservableList<Product> data = FXCollections.observableArrayList();
	@FXML
	TableColumn<Product, Integer> productQuantity;
	@FXML
	TableColumn<Product, Double> productPrice;
	@FXML
	TableColumn<Product, Double> productDiscount;
	@FXML
	TableColumn<Product, Integer> productID;
	@FXML
	TableColumn<Product, Integer> serialNumber;
	@FXML
	TableColumn<Product, Double> totalPrice;

	Customer selectedCustomer = null;
	Product selectedProduct = null;
	ObservableList<String> options;

	@Autowired
	private CustomerService customerService;
	List<String> customerNameList = new ArrayList<>();
	List<Customer> custList = new ArrayList<>();

	@Autowired
	private ProductService productService;
	List<String> productNameList = new ArrayList<>();
	List<Product> prodList = new ArrayList<>();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		refreshInvoiceTable();
		refreshCustomerList();
		refreshProductList();
		LocalDate localDate = LocalDate.now();
		invDate.setText(localDate.toString() + " (YYYY-DD-MM)");
		int invoiceNO = calculateInvoiceNO();
		invNO.setText(Integer.toString(invoiceNO));

		productQuantity
				.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		productPrice.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		productDiscount.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		productID.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		serialNumber.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		totalPrice.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));

		invCustName.focusedProperty().addListener((ov, oldV, newV) -> {
			if (!newV) {
				String customer = invCustName.getText();
				if (null != customer) {
					Customer cust = custList.stream()
							.filter(x -> (x.getCustomerName() + " " + x.getMobile_no()).equals(customer)).findAny()
							.orElse(null);
					if (null != cust)
						selectedCustomer = cust;

					if (null != selectedCustomer) {
						invAddress.setText(selectedCustomer.getAddress());
						invMobileNo.setText(selectedCustomer.getMobile_no());
					}
				}
			}
		});

		invProductName.focusedProperty().addListener((ov, oldV, newV) -> {
			if (!newV) {
				invProductQuantity.setText(Integer.toString(1));
				String product = invProductName.getText();
				if (null != product) {
					Product prd = prodList.stream().filter(x -> (x.getProductId() + ": " + x.getName()).equals(product))
							.findAny().orElse(null);
					if (null != prd) {
						invProductPrice.setText(Double.toString(prd.getRetailPrice()));
						selectedProduct = prd;
					}

				}
			}
		});

		discount.focusedProperty().addListener((ov, oldV, newV) -> {
			if (!newV){
					double total=Double.parseDouble(totalAmount.getText());
					double disc=Double.parseDouble(discount.getText());
					total=total-(total*(disc/100));
					finalAmount.setText(String.valueOf(total));
				}
		});
		
		totalAmount.focusedProperty().addListener((ov, oldV, newV) -> {
			if (!newV){
				System.out.println("hi");
					double total=Double.parseDouble(totalAmount.getText());
					double disc;
					if(discount.getText().isEmpty()) {
						discount.setText("0");
						disc=0.0;
					}else {
					disc=Double.parseDouble(discount.getText());
					}
					total=total-(total*(disc/100));
					finalAmount.setText(String.valueOf(total));
				}
		});
		
		
		finalAmount.textProperty().bindBidirectional(bigFinalAmount.textProperty());
	}

	private void refreshInvoiceTable() {
		// TODO Auto-generated method stub
		data.clear();
		
	}

	private int calculateInvoiceNO() {
		// TODO Auto-generated method stub0
		int invoiceNO = 1;

		return invoiceNO;
	}

	public void refreshCustomerList() {
		if (null != custList) {
			custList.clear();
		}
		if (null != customerNameList) {
			customerNameList.clear();
		}
		/**
		 * Fetching Fresh Customer list
		 */
		custList = customerService.fetchCustomers();
		/**
		 * Creating a customer list with Name + Mobile no
		 */
		for (Customer cust : custList) {
			customerNameList.add(cust.getCustomerName() + " " + cust.getMobile_no());
		}
		TextFields.bindAutoCompletion(invCustName, customerNameList).setVisibleRowCount(5);
	}

	private void refreshProductList() {
		// TODO Auto-generated method stub
		if (null != prodList) {
			prodList.clear();
		}
		if (null != productNameList) {
			productNameList.clear();
		}

		prodList = productService.fetchProducts();

		for (Product prod : prodList) {
			productNameList.add(prod.getProductId() + ": " + prod.getName());
		}

		TextFields.bindAutoCompletion(invProductName, productNameList).setVisibleRowCount(5);
	}

	public void showAddNewCustomerPage() {
		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		// ResourceBundle bundle = ResourceBundle.getBundle("resources.UIResources");
		StackPane addCustomer = (StackPane) loader.load(URLS.ADD_CUSTOMER);
		ApplicationContext applicationContext = SpringFxmlLoader.getApplicationcontext();
		AddCustomerController addCustomerController = (AddCustomerController) applicationContext
				.getBean("AddCustomerController");
		addCustomerController.setParentName("InvoiceController");
		BorderPane root = new BorderPane();
		root.setCenter(addCustomer);
		layoutController.loadWindow(root, "Add Customer Details", Constants.POPUP_WINDOW_WIDTH,
				Constants.POPUP_WINDOW_HEIGHT);

	}

	@FXML
	public void handleKeyAction(KeyEvent event) {

		System.out.println("+++++++");

		if ((KeyCode) event.getCode() == KeyCode.ADD) {
			try {
				addProduct();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	private void addProduct() {
		if (null != selectedProduct) {
			Product pr=selectedProduct;
			if(invProductQuantity.getText()!="") {
			pr.setSerialNumber(new SimpleIntegerProperty(productTable.getItems().size() + 1));
			pr.setQuantity(new SimpleIntegerProperty(Integer.parseInt(invProductQuantity.getText())));
			
			pr.setTotalPrice(new SimpleDoubleProperty(calculate.getTotalPrice(pr)));
			data.add(pr);
			int index=data.indexOf(pr);
			pr.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(index));
			}
			productTable.setItems(data);
			calculateTotalAmount();
			invProductName.clear();
			invProductPrice.clear();
			invProductQuantity.clear();
			
			
		}
	}
	
	
	
	private void calculateTotalAmount() {
		// TODO Auto-generated method stub
		//totalAmount
		
		//double d=((TableColumn<Product, Integer>) productTable).getTableView().getItems().get(event.getTablePosition().getRow());
		double total=0;
		for (Product row : productTable.getItems()) {
		      /*for (TableColumn column : columns) {
		        values.add(
		          (String) column.
		          getCellObservableValue(row).
		          getValue());
		      }*/
			total=total+row.getTotalPrice();
			
		    }
		totalAmount.setText(String.valueOf(total));
	}

	private void deleteButtonClickedThroughHyperlink(int index) {
		// TODO Auto-generated method stub
		
		data.remove(index);
	}

	@FXML
	public void addNewCustomer() {

		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		StackPane addCustomer = (StackPane) loader.load(URLS.ADD_CUSTOMER);

		BorderPane root = new BorderPane();
		root.setCenter(addCustomer);
		layoutController.loadWindow(root, "Add New Customer", Constants.SEARCH_CUSTOMER_WIDTH,
				Constants.SEARCH_CUSTOMER_HEIGHT);

	}
	@FXML
	public void addProdcutToTable() {
		addProduct();
	}

}
