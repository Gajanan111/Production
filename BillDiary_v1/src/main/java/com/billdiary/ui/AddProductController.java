package com.billdiary.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.javafxUtility.Popup;
import com.billdiary.model.Product;
import com.billdiary.model.Unit;
import com.billdiary.service.PriceService;
import com.billdiary.service.ProductService;

import com.billdiary.utility.Calculate;
import com.billdiary.utility.Constants;
import com.billdiary.utility.URLS;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


@Controller("AddProductController")
public class AddProductController  implements Initializable {

	@Autowired
	private ProductService productService;
	
	@Autowired 
	private PriceService priceService;
	
	@Autowired
	private LayoutController layoutController;
	
	Product prodModel;
	@FXML TextField add_productName;
	@FXML TextField productCode;
	@FXML TextField add_prodDesc;
	@FXML TextField productCategory;
	@FXML TextField add_PrdHSNCodes;
	@FXML TextField add_retailPrice;
	@FXML CheckBox retailGST;
	@FXML TextField add_wholesalePrice;
	@FXML CheckBox wholeSaleGST;
	@FXML TextField add_Discount;
	@FXML TextField add_stock;
	@FXML TextField initialStock;
	@FXML TextField  lowStock;
	@FXML ComboBox<String> wholeSaleGSTpercentage;
	@FXML ComboBox<String> retailGSTpercentage;
	@FXML ComboBox<String> units;
	
	List<String> categoryList=new ArrayList<String>();
	List<Unit> unitList=new ArrayList<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getProductCategoryList();
		getUnitList();
		units.getSelectionModel().selectedIndexProperty().addListener((v,oldValue,newValue)->{
			handleUnitSelection();
		});
		//TextFields.bindAutoCompletion(units.getEditor(),units.getItems()).setVisibleRowCount(3);
		TextFields.bindAutoCompletion(productCategory,categoryList).setVisibleRowCount(3);
		
		if(getProdModel()!=null) {
			Product pro=getProdModel();
			add_Discount.setText(Double.toString(pro.getDiscount()));
			add_prodDesc.setText(pro.getDescription());
			add_productName.setText(pro.getName());
			productCode.setText(Long.toString(pro.getProductCode()));
		    initialStock.setText(String.valueOf(pro.getStock()));
		    productCategory.setText(pro.getProductCategory()); 
		    add_PrdHSNCodes.setText(pro.getProductHSNCode());
		    units.setValue(pro.getUnit().getUnitName());
		    if(null!=pro.getRetailGST() && "Y".equals(pro.getRetailGST())){
		    	double retailGSTPrice=priceService.getRetailGSTPrice(pro.getRetailPrice(), pro.getRetailGSTpercentage(), pro.getDiscount());
		    	
		    	add_retailPrice.setText(Double.toString(retailGSTPrice));
		    	retailGST.setSelected(true);
		    	retailGSTpercentage.setValue(pro.getRetailGSTpercentage()+"%");
		    }else {
		    	retailGST.setSelected(false);
		    	add_retailPrice.setText(Double.toString(pro.getRetailPrice()));
		    }
		    if(null!=pro.getWholeSaleGST() && "Y".equals(pro.getWholeSaleGST())) {
		    	double wholeSaleGSTPrice=priceService.getWholeSaleGSTPrice(pro.getWholesalePrice(), pro.getWholeSaleGSTpercentage(), pro.getDiscount());
		    	
		    	add_wholesalePrice.setText(Double.toString(wholeSaleGSTPrice));
		    	wholeSaleGST.setSelected(true);
		    	wholeSaleGSTpercentage.setValue(pro.getWholeSaleGSTpercentage()+"%");
		    }else {
		    	wholeSaleGST.setSelected(false);
		    	add_wholesalePrice.setText(Double.toString(pro.getWholesalePrice()));
		    }	   
		}
	}

	public void getUnitList() {
		unitList=productService.getUnitList();
		units.getItems().clear();
		if(units.getItems().isEmpty()) {
			units.getItems().add("Add Unit");
		    unitList.forEach(unit->{
			                        units.getItems().add(unit.getUnitName());
		                     });
		}
	}

	private void getProductCategoryList() {
		categoryList=productService.getCategoryList();
	}

	
	public void handleUnitSelection() {
		if("Add Unit".equals(units.getValue())) {
			SpringFxmlLoader loader = SpringFxmlLoader.getInstance();
			StackPane addUnit = (StackPane) loader.load(URLS.ADD_UNIT);
			BorderPane root = new BorderPane();
			root.setCenter(addUnit);
			StackPane stk=new StackPane();
			stk.getChildren().add(addUnit);
			Stage stage = new Stage();
		    Scene scene = new Scene(stk, Constants.POPUP_UNIT_WINDOW_WIDTH, Constants.POPUP_UNIT_WINDOW_HEIGHT); 
	        stage.setTitle("Add Units");
	        stage.setScene(scene);
	        stage.showAndWait();
		}
	}

	@FXML
	public void addProduct(ActionEvent event){		
		Product prod=new Product();
		if(validateProduct()) {
			
		String productName=add_productName.getText();
		String productDesc=add_prodDesc.getText();
		Double retailPrice=Calculate.getNonEmptyDoubleValue(add_retailPrice.getText());
		Double wholesalePrice=Calculate.getNonEmptyDoubleValue(add_wholesalePrice.getText());
		Double discount=Calculate.getNonEmptyDoubleValue(add_Discount.getText());
		if(discount>100.00) {
			discount=0.00;
		}
		double stock=Calculate.getNonEmptyDoubleValue(add_stock.getText());
		stock=stock+Calculate.getNonEmptyDoubleValue(initialStock.getText());
		String wholeSaleGSTper=wholeSaleGSTpercentage.getValue();
		String retailGSTper=retailGSTpercentage.getValue();
		if(null==wholeSaleGSTper) {
			wholeSaleGSTper="0%";
		}
		if(null==retailGSTper) {
			retailGSTper="0%";
		}
		
		if(wholeSaleGST.isSelected()) {
			//wholesalePrice=Calculate.getWholeSalePrice(wholesalePrice,wholeSaleGSTper);
			wholesalePrice=priceService.getWholeSalePrice(wholesalePrice, Double.parseDouble(Calculate.trimPercentage(wholeSaleGSTper)), discount);
			System.out.println(wholesalePrice);
			prod.setWholeSaleGST(new SimpleStringProperty("Y"));
			prod.setWholeSaleGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(wholeSaleGSTper))));
		}else {
			prod.setWholeSaleGST(new SimpleStringProperty("N"));
			prod.setWholeSaleGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(wholeSaleGSTper))));
		}
		if(retailGST.isSelected()) {
			//retailPrice=Calculate.getRetailPrice(retailPrice,retailGSTper,discount);
			retailPrice=priceService.getRetailPrice(retailPrice, Double.parseDouble(Calculate.trimPercentage(retailGSTper)), discount);
			System.out.println(retailPrice);
			prod.setRetailGST(new SimpleStringProperty("Y"));
			prod.setRetailGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(retailGSTper))));
		}else{
			prod.setRetailGST(new SimpleStringProperty("N"));
			prod.setRetailGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(retailGSTper))));
			
		}			
		prod.setName(new SimpleStringProperty(productName));
		prod.setProductCode(new SimpleLongProperty(Long.parseLong(productCode.getText())));
		prod.setDescription(new SimpleStringProperty(productDesc));
		prod.setRetailPrice(new SimpleDoubleProperty(retailPrice));
		prod.setWholesalePrice(new SimpleDoubleProperty(wholesalePrice));
		prod.setDiscount(new SimpleDoubleProperty(discount));
		prod.setStock(new SimpleDoubleProperty(stock)); 
		prod.setProductCategory(new SimpleStringProperty(productCategory.getText()));
		prod.setProductHSNCode(new SimpleStringProperty(add_PrdHSNCodes.getText()));	
		Unit pUnit=unitList.stream()
		.filter(unit -> (unit.getUnitName()).equals(units.getValue())).findAny()
		.orElse(null);
		prod.setUnit(pUnit);
		if(getProdModel()!=null)
		{
			prod.setProductId(new SimpleIntegerProperty(getProdModel().getProductId()));
			productService.updateProduct(prod);
		}
		else
		productService.addProduct(prod);
		((Node)(event.getSource())).getScene().getWindow().hide();
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		ManageProductController  manageProductController=( ManageProductController)applicationContext.getBean("ManageProductController");
		manageProductController.getRefreshedTable();
		}
		
	}
	private boolean validateProduct() {

		try{
			long code=Long.parseLong(productCode.getText());
		}catch(Exception e) {
			Popup.showErrorAlert(Constants.ERROR_TITLE,"Please enter a product code as a number",AlertType.ERROR);
			return false;
		}
		
		if(null==add_productName.getText() || null== units.getValue() || null==productCode.getText()) {
			Popup.showErrorAlert(Constants.ERROR_TITLE,Constants.ERROR_COMMON_VALIDATION,AlertType.ERROR);
			return false;
		}
		else if(add_productName.getText().isEmpty() || productCode.getText().isEmpty()) {
			Popup.showErrorAlert(Constants.ERROR_TITLE,Constants.ERROR_COMMON_VALIDATION,AlertType.ERROR);
			return false;
		}
		return true;
	}


	public Product getProdModel() {
		return prodModel;
	}


	public void setProdModel(Product prodModel) {
		this.prodModel = prodModel;
	}
}
