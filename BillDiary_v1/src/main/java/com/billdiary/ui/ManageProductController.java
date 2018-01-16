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
import com.billdiary.service.ProductService;

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
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

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
	@FXML TableColumn<Product,Double>WholesalePrice;
	@FXML TableColumn<Product,Double>RetailPrice;
	@FXML TableColumn<Product,Double>Discount;
	@FXML TableColumn<Product,Integer>Stock;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{	
		//this.customerName.textProperty().bind(this.customer.getName());
		RetailPrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		WholesalePrice.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		Discount.setCellFactory(TextFieldTableCell.<Product,Double>forTableColumn(new DoubleStringConverter()));
		Stock.setCellFactory(TextFieldTableCell.<Product,Integer>forTableColumn(new IntegerStringConverter()));
		System.out.println("Inside Initialize");
		ProductTable.setItems(data);
		populate(retrieveData());	
	}
		
		
	private List<Product> retrieveData(){
		
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
	        	prods.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
	        	prods.getSave().setOnAction(e->editButtonClickedThroughHyperlink(pid,index));
	        }
	      }
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	
	}
	
	
	
	public void editButtonClickedThroughHyperlink(int productId,int index) {
		
		Product product=data.get(index);
		product.setProductId(new SimpleIntegerProperty(productId));
	    addProductController.setProdModel(product);
		SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
		StackPane addShop = (StackPane) loader.load(URLS.ADD_PRODUCT_PAGE);
		BorderPane root = new BorderPane();
		root.setCenter(addShop);
		layoutController.loadWindow(root, "Edit Product Details", Constants.POPUP_WINDOW_WIDTH,
				Constants.POPUP_WINDOW_HEIGHT);
		
	}
	@FXML public void addNewProduct()
	{
		addProductController.setProdModel(null);
		SpringFxmlLoader loader=SpringFxmlLoader.getInstance();
		StackPane addProduct=(StackPane)  loader.load(URLS.ADD_PRODUCT_PAGE);
		BorderPane root = new BorderPane();
		root.setCenter(addProduct);
		layoutController.loadWindow(root,"Add Product Details",Constants.POPUP_WINDOW_WIDTH,Constants.POPUP_WINDOW_HEIGHT);
		}
	
	public void deleteButtonClickedThroughHyperlink(int productId)
	{
		System.out.println("Inside DeleteButtonClicked");
		productService.deleteProduct(productId);
		getRefreshedTable();
		
	}
	@FXML public void deleteButtonClicked()
	{
		
		System.out.println("Inside DeleteButtonClicked");
		ObservableList< Product> ListItems,SelectedListItem;
		ListItems=ProductTable.getItems();
		SelectedListItem=ProductTable.getSelectionModel().getSelectedItems();	
		int id=SelectedListItem.get(0).getProductId();
		//SelectedListItem.forEach(ListItems::remove);
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
			event.getTableView().getItems().get(event.getTablePosition().getRow()).setStock(new SimpleIntegerProperty(stock));
			Stock.setCellFactory(TextFieldTableCell.<Product,Integer>forTableColumn(new IntegerStringConverter()));	
		}	
	}
	
	@FXML public void saveProduct()
	{
		ObservableList < Product> ObproductList;
		
		ObproductList =  ProductTable.getSelectionModel().getSelectedItems();
		System.out.println(ObproductList.get(0).getDescription());
		if(ObproductList!=null)
		{
			productService.saveProduct(ObproductList);
			productList.clear();
			data.clear();
			ProductTable.setItems(data);
			populate(retrieveData());
			
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
				pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
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
					pd.getDelete().setOnAction(e->deleteButtonClickedThroughHyperlink(pid));
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
		productList.clear();
		data.clear();
		ProductTable.setItems(data);
		populate(retrieveData());
	}
	
	 @FXML private void createExcelFile()
	 {
		 
	 }
	 @FXML private void uploadExcelFile()
	 {
		 
	 }
}