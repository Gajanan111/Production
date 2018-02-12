package com.billdiary.ui;

import java.net.URL;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.javafxUtility.Popup;
import com.billdiary.javafxUtility.TabTraversalEventHandler;
import com.billdiary.model.Customer;
import com.billdiary.model.Invoice;
import com.billdiary.model.InvoiceTemplateA4;
import com.billdiary.model.Product;
import com.billdiary.service.CustomerService;
import com.billdiary.service.InvoiceService;
import com.billdiary.service.PriceService;
import com.billdiary.service.ProductService;
import com.billdiary.utility.Calculate;
import com.billdiary.utility.Constants;
import com.billdiary.utility.GeneratePDF;
import com.billdiary.utility.URLS;


import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

@Controller("ManageInvoiceController")
public class ManageInvoiceController implements Initializable {

	final static Logger LOGGER = Logger.getLogger(ManageInvoiceController.class);
	
	@Autowired
	GeneratePDF generatePDF;
	
	@Autowired
	public InvoiceService invoiceService;
	
	@Autowired
	public LayoutController layoutController;
	
	@Autowired
	private PriceService priceService;
	
	@Autowired
	public Calculate calculate;
	@FXML
	SplitMenuButton saveButton;

	@FXML TextField paidAmount;
	@FXML TextField totalAmount;
	@FXML TextField bigFinalAmount;
	@FXML TextField amountDue;
	@FXML TextField discount;
	@FXML TextField finalAmount;
	@FXML TextArea invAddress;
	@FXML TextField invMobileNo;
	@FXML Text invNO;
	@FXML Text invDate;
	@FXML DatePicker invIssueDate;
	@FXML DatePicker invDueDate;
	@FXML TextField invCustName;
	@FXML TextField invProductName;
	@FXML TextField invProductQuantity;
	@FXML TextField invProductPrice;
	@FXML TextField taxableAmt;
	@FXML TextField totalCGST;
	@FXML TextField totalSGST;
	@FXML TextField totalAmt;

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
	@FXML
	TableColumn<Product, Double> gstRate;
	@FXML
	TableColumn<Product, Double> mrpPrice;;
	

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
		//invDate.setText(localDate.toString() + " (YYYY-DD-MM)");
		invIssueDate.setValue(localDate);
		invDueDate.setValue(localDate);
		int invoiceNO = calculateInvoiceNO();
		invNO.setText(invoiceService.generateInvoiceNO());
		
		

		productQuantity
				.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		productPrice.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		productDiscount.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		productID.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		serialNumber.setCellFactory(TextFieldTableCell.<Product, Integer>forTableColumn(new IntegerStringConverter()));
		totalPrice.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		gstRate.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		mrpPrice.setCellFactory(TextFieldTableCell.<Product, Double>forTableColumn(new DoubleStringConverter()));
		
		invCustName.focusedProperty().addListener((ov, oldV, newV) -> {
			if (!newV) {
				selectCustomerAddMob();
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

		invAddress.addEventFilter(KeyEvent.KEY_PRESSED, new TabTraversalEventHandler());
		
		discount.textProperty().addListener((ov, oldV, newV) -> {
			calculatefinalAmount();	
		});
		
		totalAmount.textProperty().addListener((ov, oldV, newV) -> {
			calculatefinalAmount();
			
		});
		paidAmount.textProperty().addListener((ov,oldV,newV)->{
			calculateAmountDue();
		});
		taxableAmt.textProperty().addListener((ov,oldV,newV)->{
			calculateTaxableAmount();
		});
	
		finalAmount.textProperty().bindBidirectional(bigFinalAmount.textProperty());
		finalAmount.textProperty().addListener((ov,oldV,newV)->{
			paidAmount.setText("0");
			amountDue.setText(finalAmount.getText());
		});
	}

	private void calculateTaxableAmount() {
		
		
	}

	private void refreshInvoiceTable() {
		data.clear();	
	}

	private int calculateInvoiceNO() {
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
			customerNameList.add(cust.getCustomerID()+" "+cust.getCustomerName() + " " + cust.getMobile_no());
		}
		TextFields.bindAutoCompletion(invCustName, customerNameList).setVisibleRowCount(5);
	}

	
	public void calculatefinalAmount() {
		double total;
		double disc;
		total=Calculate.getNonEmptyDoubleValue(totalAmount.getText());
		disc=Calculate.getNonEmptyDoubleValue(discount.getText());
		total=total-(total*(disc/100));
		finalAmount.setText(String.valueOf(Calculate.getFormatedDoubleValue(total)));
	}
	
	public void calculateAmountDue()
	{
		double paidAmt;
		double total;
		total=Calculate.getNonEmptyDoubleValue(finalAmount.getText());
		paidAmt=Calculate.getNonEmptyDoubleValue(paidAmount.getText());
		amountDue.setText(String.valueOf(Calculate.getFormatedDoubleValue(total-paidAmt)));
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
	
	@FXML
	public void handleAddNewCustomer(KeyEvent event) {
		if ((KeyCode) event.getCode() == KeyCode.ENTER) {
			try {
				showAddNewCustomerPage();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
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
		
		final KeyCombination keyComb = new KeyCodeCombination(KeyCode.P, KeyCombination.CONTROL_DOWN);

		if ((KeyCode) event.getCode() == KeyCode.ENTER) {
			try {
				addProduct();
				invProductName.requestFocus();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (keyComb.match(event)) {
			try {
				generateInvoiceSaveAndPrint(new ActionEvent());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@FXML
	public void handleKeyActionEnter(KeyEvent event) {
		if ((KeyCode) event.getCode() == KeyCode.ENTER) {
			try {
				selectCustomerAddMob();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void selectCustomerAddMob() {
		String customer = invCustName.getText();
		if (null != customer) {
			Customer cust = custList.stream()
					.filter(x -> (x.getCustomerID()+" "+x.getCustomerName() + " " + x.getMobile_no()).equals(customer)).findAny()
					.orElse(null);
			if (null != cust)
				selectedCustomer = cust;

			if (null != selectedCustomer) {
				invAddress.setText(selectedCustomer.getAddress());
				invMobileNo.setText(selectedCustomer.getMobile_no());
			}
		}
	}
	private void addProduct() {
		if (null != selectedProduct) {
			Product pr=new Product();
			pr=(Product)selectedProduct.clone();
			/*pr.setProductId(new SimpleIntegerProperty(selectedProduct.getProductId()));
			pr.setName(new SimpleStringProperty(selectedProduct.getName()));
			pr.setDescription(new SimpleStringProperty(selectedProduct.getDescription()));
			pr.setRetailPrice(new SimpleDoubleProperty(selectedProduct.getRetailPrice()));
	        pr.setDiscount(new SimpleDoubleProperty(selectedProduct.getDiscount()));*/
	        
	        
			if(invProductQuantity.getText()!="") {
			pr.setSerialNumber(new SimpleIntegerProperty(productTable.getItems().size() + 1));
			pr.setQuantity(new SimpleIntegerProperty(Integer.parseInt(invProductQuantity.getText())));
			
			double totalPrice=priceService.getProductSellingPrice(pr.getRetailPrice(),pr.getRetailGSTpercentage(),pr.getQuantity());
			pr.setTotalPrice(new SimpleDoubleProperty(totalPrice));
			
			double mrpPrice=priceService.getRetailGSTPrice(pr.getRetailPrice(), pr.getRetailGSTpercentage(), pr.getDiscount());
			pr.setMrpPrice(new SimpleDoubleProperty(mrpPrice));
			data.add(pr);
			int index=data.size()-1;
			pr.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(index));
			}
			productTable.setItems(data);
			calculateTotalAmount();
			invProductName.clear();
			invProductPrice.clear();
			invProductQuantity.clear();
			selectedProduct=null;
			calculateGST();
			
		}
	}
	
	
	
	private void calculateGST() {
		/**
		 * taxableAmt
		 * totalCGST
		 * totalSGST
		 * totalAmt
		 */
		double taxableAmount=0;
		double totalCGSTAmount=0;
		double totalSGSTAmount=0;
		double totalGSTAddedAmount=0;
		double subtractedAmont=0;
		for (Product row : productTable.getItems()) {
			taxableAmount=taxableAmount+row.getRetailPrice();
			totalGSTAddedAmount=totalGSTAddedAmount+row.getTotalPrice();
			subtractedAmont=totalGSTAddedAmount-taxableAmount;
			totalCGSTAmount=totalSGSTAmount=(subtractedAmont/2);
		    }
		taxableAmt.setText(Double.toString(taxableAmount));
		totalCGST.setText(Double.toString(totalCGSTAmount));
		totalSGST.setText(Double.toString(totalSGSTAmount));
		totalAmt.setText(Double.toString(totalGSTAddedAmount));
		
	}

	private void calculateTotalAmount() {
		double total=0;
		double taxableTotal=0;
		for (Product row : productTable.getItems()) {
			total=total+row.getTotalPrice();
			
		    }
		totalAmount.setText(String.valueOf(total));
		
	}

	private void deleteButtonClickedThroughHyperlink(int index) {
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
		invProductName.requestFocus();
	}
	
	public String trimCustomerID(String displayName) {
		String custID="0";
		if(null!=displayName) {
			int i=displayName.indexOf(' ');
			custID=displayName.substring(0, i);
			//System.out.println(custID);
		}
		return custID;
	}
	
	public Customer getCustomer(String custID) {
		Customer cust=	custList.stream()
		.filter(x -> (String.valueOf(x.getCustomerID())).equals(custID)).findAny()
		.orElse(null);
		return cust;
	}
	
	public boolean saveInvoice() {
		boolean invoiceSaved=false;
		Customer cust=getCustomer(trimCustomerID(invCustName.getText()));
		if(null==invDueDate.getValue()) {
			
			invDueDate.setValue(LocalDate.now());
		}
		Invoice inv=new Invoice();
		inv.setInvoiceID(Long.parseLong(invNO.getText()));
		inv.setCustomer(cust);
		inv.setAmountDue(Calculate.getNonEmptyDoubleValue(amountDue.getText()));
		inv.setFinalAmount(Calculate.getNonEmptyDoubleValue(finalAmount.getText()));
		inv.setPaidAmount(Calculate.getNonEmptyDoubleValue(paidAmount.getText()));
		inv.setProduct_sale_qty(data.size());
		inv.setLastAmountPaidDate(LocalDate.now());
		inv.setInvoiceDate(invIssueDate.getValue());
		inv.setInvoiceDueDate(LocalDate.now());
		invoiceSaved=invoiceService.save(inv);
		return invoiceSaved;
	}
	
	
	@FXML
	public void generateInvoiceSave(ActionEvent event) {
		System.out.println("generateInvoiceSave");
		if(saveInvoice()) {
			System.out.println("invoice saved");
			Popup.showAlert(Constants.INVOICE_TITLE,Constants.INVOICE_SUCCESSFULL_STATUS,AlertType.INFORMATION);
			clearAllFields();			
		}else {
			System.out.println("invoice not saved");
			Popup.showAlert(Constants.INVOICE_TITLE,Constants.INVOICE_UNSUCCESSFULL_STATUS,AlertType.INFORMATION);
		}
	}
	@FXML
	public void generateInvoiceSaveAndPrint(ActionEvent event) {
		System.out.println("generateInvoiceSaveAndPrint");
		
		if(saveInvoice()) {
			System.out.println("invoice saved");
			LOGGER.info("invoice saved");
						
		}else {
			System.out.println("invoice not saved");
			Popup.showAlert(Constants.INVOICE_TITLE,Constants.INVOICE_UNSUCCESSFULL_STATUS,AlertType.INFORMATION);
		}
		try {
		InvoiceTemplateA4 invoiceTemplate=generateInvoiceTemplateA4();
		LOGGER.info("InvoiceTemplateA4 generated");
		generatePDF.transformXSLToPDF(invoiceTemplate);
		LOGGER.info("transformXSLToPDF generated ");
		System.out.println("Invoice PDF generated");
		LOGGER.info("Invoice PDF generated");
		}
		catch(Exception e){
			LOGGER.info(e.getMessage()+ e.getCause()+ e.getClass());
		}
		
		clearAllFields();
	}

	private InvoiceTemplateA4 generateInvoiceTemplateA4() {
		InvoiceTemplateA4 invoiceTemplate=new InvoiceTemplateA4();
		invoiceTemplate.setInvoiceNO(invNO.getText());
		
		invoiceTemplate.setCompanyName("BillDiary.com");
		/*ImageView imageView=new ImageView();
		Image image=new Image(getClass().getResource(URLS.INVOICE_LOGO).getFile());
		imageView.setImage(image);
		invoiceTemplate.setLogo(imageView);*/
		invoiceTemplate.setCustomerName(invCustName.getText());
		invoiceTemplate.setCustomerAddress(invAddress.getText());
		invoiceTemplate.setAmountDue(amountDue.getText());
		invoiceTemplate.setFinalAmount(finalAmount.getText());
		List<Product> products=new ArrayList<>();
		data.forEach(product->{
			products.add(product);
		});
		invoiceTemplate.setProducts(products);
		invoiceTemplate.setDiscount(discount.getText());
		invoiceTemplate.setMobileNo(invMobileNo.getText());
		LocalDate today=LocalDate.now();
		invoiceTemplate.setToday(today);
		invoiceTemplate.setPaidAmount(paidAmount.getText());
		invoiceTemplate.setTotalAmount(totalAmount.getText());
		return invoiceTemplate;	
	}
	private void clearAllFields() {
		paidAmount.clear();
		totalAmount.clear();
		bigFinalAmount.clear();
		amountDue.clear();
		discount.clear();
		finalAmount.clear();
		invAddress.clear();
		invMobileNo.clear();
		invCustName.clear();
		invProductName.clear();
		invProductQuantity.clear();
		invProductPrice.clear();
		data.clear();
	}
	
	
}
