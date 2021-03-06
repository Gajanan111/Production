package com.billdiary.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


import com.billdiary.config.SpringFxmlLoader;

import com.billdiary.model.Product;
import com.billdiary.service.ProductService;

import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

@Controller("ManageProductController")
public class ManageProductController implements Initializable{
	@Autowired
	public LayoutController layoutController;
	
	@Autowired
	AddProductController addProductController;
	//final static Logger LOGGER = Logger.getLogger(ManageProductController.class);
	@Autowired
	private ProductService productService;
	
	List<Product> productList=new ArrayList<>();
	@FXML
	TextField productName;
	@FXML 
	TextField productId;
    private ObservableList < Product > data = FXCollections.observableArrayList();
 
    @FXML
	private TableView < Product > ProductTable;
    @FXML TableColumn<Product,Long>productCode;
	@FXML TableColumn<Product,Double>WholesalePrice;
	@FXML TableColumn<Product,Double>RetailPrice;
	@FXML TableColumn<Product,Double>Discount;
	@FXML TableColumn<Product,Double>Stock;
	
	@FXML Pagination pagination;
	private static int pages;
	private static int index=0;
	private static long count=0;
	//private static int rowsPerPage=10;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		try {
			System.out.println("Hi");
		//this.customerName.textProperty().bind(this.customer.getName());
		productCode.setCellFactory(TextFieldTableCell.<Product,Long>forTableColumn(new LongStringConverter()));
		RetailPrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		WholesalePrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		Discount.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		Stock.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		System.out.println("Inside Initialize");
		System.out.println("Hi");
		count=productService.getProductCount();
		System.out.println("Hi");
		Task<Void> showTable = new Task<Void>() {
		    @Override public Void call() {
		    	//count=productService.getProductCount();
        		pages=getPages(count);
        		System.out.println("pages:"+ pages+"count: "+count );
            	updateTable(pages,index,Constants.rowsPerPage);
		        return null;
		    }
		};
		new Thread(showTable).start();
		pagination.setPageCount(getPages(count));
		pagination.currentPageIndexProperty().addListener((obs, oldIndex, newIndex) -> 
			{
				//System.out.println("pages:"+ pages+"count: "+count );
				updateTable(pages, newIndex.intValue(),Constants.rowsPerPage);
	        
			}
		);	
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * 
	 * All pagination related funtions
	 * 
	 */
	
	public int getPages(long count) {
		return (int)(((count-1)/Constants.rowsPerPage)+1);
	}
	
	public void refreshPagination() {
		count=productService.getProductCount();
		pages=getPages(count);
		pagination.setPageCount(pages);
		System.out.println("refreshPagination completed");
	}
	
	public void updateTable(int pages, int index,int rowsPerPage) {
		List<Product> products=productService.getProducts(pages, index, rowsPerPage);	
		data.clear();
		if(data.isEmpty())
		{
	        for(Product prods:products)
	        {
	        	data.add(prods);
	        	int pid=prods.getProductId();
	        	int rowIndex=data.indexOf(prods);
	        	prods.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid,rowIndex));
	        	prods.getSave().setOnAction(e->editButtonClickedThroughHyperlink(pid,rowIndex));
	        }
	     }
		ProductTable.setItems(data);	
	}
		
	/**
	 * This function invoked when user press edit hyperlink from UI
	 * @param productId
	 * @param rowIndex
	 */
	public void editButtonClickedThroughHyperlink(int productId,int rowIndex) {
		
		Product product=data.get(rowIndex);
		product.setProductId(new SimpleIntegerProperty(productId));
	    addProductController.setProdModel(product);
		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		StackPane addShop = (StackPane) loader.load(URLS.ADD_PRODUCT_PAGE);
		BorderPane root = new BorderPane();
		root.setCenter(addShop);
		layoutController.loadWindow(root, "Edit Product Details", Constants.POPUP_WINDOW_WIDTH,
				Constants.POPUP_WINDOW_HEIGHT);
		
	}
	
	/**
	 * This function invoked when user clicks on delete hyperlink
	 * @param productId
	 * @param rowIndex
	 */
	@SuppressWarnings("restriction")
	public void deleteButtonClickedThroughHyperlink(int productId,int rowIndex)
	{
		System.out.println("Inside DeleteButtonClicked");
		productService.deleteProduct(productId);
		/*count=count-1;
		pages=getPages(count);
		pagination.setPageCount(pages);*/
		refreshPagination();
		if(rowIndex==0 && !(pagination.getCurrentPageIndex()==Constants.ZERO)) {
			pagination.setCurrentPageIndex(pagination.getCurrentPageIndex()-1);
			//updateTable(pages, pagination.getCurrentPageIndex()-1,Constants.rowsPerPage);
		}else {
			updateTable(pages, pagination.getCurrentPageIndex(),Constants.rowsPerPage);
		}
		
		//getRefreshedTable();
		
	}
	/**
	 * This funtion invoke onClick of Add Product Buttons
	 */
	@FXML public void addNewProduct()
	{
		addProductController.setProdModel(null);
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		StackPane addProduct=(StackPane)  loader.load(URLS.ADD_PRODUCT_PAGE);
		BorderPane root = new BorderPane();
		root.setCenter(addProduct);
		layoutController.loadWindow(root,"Add Product Details",Constants.POPUP_WINDOW_WIDTH,Constants.POPUP_WINDOW_HEIGHT);
	}
	
	@FXML public void deleteButtonClicked() throws Exception
	{
		
		System.out.println("Inside DeleteButtonClicked");
		ObservableList< Product> ListItems,SelectedListItem;
		ListItems=ProductTable.getItems();
		SelectedListItem=ProductTable.getSelectionModel().getSelectedItems();	
		int id=SelectedListItem.get(0).getProductId();
		boolean productDeleted=false;
		productDeleted=productService.deleteProduct(id);
		if(productDeleted)
		{
			System.out.println("Product Deleted");
		}
		else {
			System.out.println("Product not Deleted");
		}
		getRefreshedTable();
		
	}
	
	@FXML public void saveProduct() throws Exception
	{
		 ObservableList < Product> ObproductList =  ProductTable.getSelectionModel().getSelectedItems();
		System.out.println(ObproductList.get(0).getDescription());
		if(ObproductList!=null)
		{
			productService.saveProduct(ObproductList);
			productList.clear();
			data.clear();
			ProductTable.setItems(data);
			getRefreshedTable();	
		}
	}
	@FXML public void searchProduct()
	{
		
		System.out.println("Inside Search product");
		ObservableList<Product> data = FXCollections.observableArrayList();
		List<Product> obj = productService.fetchProducts();
		int size = obj.size();
		String ProdName = productName.getText();
		if(productId.getText().equals(""))
		{
			productId.setText("0");
		}
		Integer ProdId = Integer.parseInt(productId.getText());
		System.out.println("prod name is:"+productName.getText());
		boolean search=true;
		int count=0;
		
		for(Product pd:obj)
		{	
			count++;
			if(ProdName.equals("") && ProdId==0)
			{	System.out.println("inside if");
				initialize(null, null);
				search=false;
				break;
			}
			else if(!ProdName.equals("") && ProdId!=0)
			{
				search=false;
			}
			
			System.out.println(Integer.parseInt(productId.getText()));
			if(ProdId==(pd.getProductId()) && ProdName.toLowerCase().equals(pd.getName().toLowerCase()))
			{
				data.add(pd);
				ProductTable.setItems(data);
				int pid=pd.getProductId();
				int rowIndex=data.indexOf(pd);
				pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid,rowIndex));
				search=false;
				break;
			}
			if(count>=size)
			{
				System.out.println("inside else");
				ObservableList< Product> ListItems;
				ListItems=ProductTable.getItems();
				System.out.println(ListItems.size());
				while(ListItems.size()!=0)
				{
					ListItems.remove(0);
				}	
			}
		}	
		if(search == true)
		{	
			count = 0;
			for(Product pd:obj)
			{
				if(ProdId ==(pd.getProductId())|| ProdName.toLowerCase().equals(pd.getName().toLowerCase()))
				{
					data.add(pd);
					ProductTable.setItems(data);
					int pid=pd.getProductId();
					int rowIndex=data.indexOf(pd);
					pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid,rowIndex));
					count++;
					search=false;	
				}
				if(count>=obj.size()-1&& search ==true)
				{
					System.out.println("inside else");
					ObservableList< Product> ListItems;
					ListItems=ProductTable.getItems();
					System.out.println(ListItems.size());
					while(ListItems.size()!=0)
					{
						ListItems.remove(0);
					}
				}
			}
		}
		productId.setText("");
		productName.setText("");	
	}
	
	public void getRefreshedTable()
	{
		try {
			productList.clear();
			data.clear();
			refreshPagination();
			updateTable(pages, pagination.getCurrentPageIndex(),Constants.rowsPerPage);
		}catch(Exception e) {
			System.out.println("Exception occured at refreshProductTable");
		}
		//ProductTable.setItems(data);
		//populate(retrieveData());
	}
	
	
	
	@FXML private <T>void setEditedValue(CellEditEvent<Product,T> event)
	{
		if("ProductName".equals(event.getTableColumn().getId())) {
			String ProductName=event.getNewValue().toString();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setName(new SimpleStringProperty(ProductName));
		}
		if("ProductDesc".equals(event.getTableColumn().getId())) {
			String ProductDesc=event.getNewValue().toString();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setDescription(new SimpleStringProperty(ProductDesc));
		}
		if("WholesalePrice".equals(event.getTableColumn().getId())) {
			Double wholesalePrice=(Double)event.getNewValue();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setWholesalePrice(new SimpleDoubleProperty(wholesalePrice));
			WholesalePrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
			
		}
		if("RetailPrice".equals(event.getTableColumn().getId())) {
			Double retailPrice=(Double)event.getNewValue();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setRetailPrice(new SimpleDoubleProperty(retailPrice));
			RetailPrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		}
		if("Discount".equals(event.getTableColumn().getId())) {
			Double discount=(Double)event.getNewValue();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setDiscount(new SimpleDoubleProperty(discount));
			Discount.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));	
		
		}
		if("Stock".equals(event.getTableColumn().getId())) {
			Integer stock=(Integer)event.getNewValue();
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setStock(new SimpleDoubleProperty(stock));
			Stock.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));	
		}	
	}
	
	 @FXML private void createExcelFile()
	 {
		 
	 }
	 @FXML private void uploadExcelFile()
	 {
		 
	 }
	 /*private List<Product> retrieveData(){
		
		try 
		{
			if(productList.isEmpty())
			{
				productList=productService.fetchProducts();
			}
			System.out.println(productList.get(0).getDescription());
		return productList;
				
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return new ArrayList<Product>();
	}
		
	private void populate(final List < Product > products) 
	{
		try {
		System.out.println("inside populate");
		if(data.isEmpty())
		{
	        for(Product prods:products)
	        {
	        	data.add(prods);
	        	int pid=prods.getProductId();
	        	int index=data.indexOf(prods);
	        	prods.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid,index));
	        	prods.getSave().setOnAction(e->editButtonClickedThroughHyperlink(pid,index));
	        }
	      }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	*/
}