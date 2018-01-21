package com.billdiary.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.javafxUtility.Popup;
import com.billdiary.model.Product;
import com.billdiary.model.Supplier;
import com.billdiary.service.ProductService;
import com.billdiary.service.SupplierService;
import com.billdiary.utility.Calculate;
import com.billdiary.utility.Constants;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;


@Controller("AddProductController")
public class AddProductController  implements Initializable {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private SupplierService supplierService;
	Product prodModel;
	@FXML
	TextField add_productName;
	@FXML
	TextField add_prodDesc;
	@FXML
	ComboBox<String> supplierComboList;
	@FXML
	TextField add_PrdHSNCodes;
	@FXML
	TextField add_retailPrice;
	@FXML
	CheckBox retailGST;
	@FXML
	TextField add_wholesalePrice;
	@FXML
	CheckBox wholeSaleGST;
	@FXML
	TextField add_Discount;
	@FXML
	TextField add_stock;
	@FXML
	TextField initialStock;
	@FXML
	TextField  lowStock;
	@FXML
	TextField productCategory;
	@FXML
	ComboBox<String> wholeSaleGSTpercentage;
	@FXML
	ComboBox<String> retailGSTpercentage;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		supplierComboList.setVisibleRowCount(5);
		getSupplierList();
		
		
		if(getProdModel()!=null) {
			Product pro=getProdModel();
			add_Discount.setText(Double.toString(pro.getDiscount()));
			add_prodDesc.setText(pro.getDescription());
			add_productName.setText(pro.getName());
		    initialStock.setText(String.valueOf(pro.getStock()));
		    productCategory.setText(pro.getProductCategory()); 
		    add_PrdHSNCodes.setText(pro.getProductHSNCode());
		    if(null!=pro.getRetailGST() && "Y".equals(pro.getRetailGST())){
		    	add_retailPrice.setText(Double.toString(Calculate.getRetailWithGST(pro.getRetailPrice(),pro.getRetailGSTpercentage())));
		    	retailGST.setSelected(true);
		    	retailGSTpercentage.setValue(pro.getRetailGSTpercentage()+"%");
		    }else {
		    	retailGST.setSelected(false);
		    	add_retailPrice.setText(Double.toString(pro.getRetailPrice()));
		    }
		    if(null!=pro.getWholeSaleGST() && "Y".equals(pro.getWholeSaleGST())) {
		    	add_wholesalePrice.setText(Double.toString(Calculate.getWholeSaleWithGST(pro.getWholesalePrice(),pro.getWholeSaleGSTpercentage())));
		    	wholeSaleGST.setSelected(true);
		    	wholeSaleGSTpercentage.setValue(pro.getWholeSaleGSTpercentage()+"%");
		    }else {
		    	wholeSaleGST.setSelected(false);
		    	add_wholesalePrice.setText(Double.toString(pro.getWholesalePrice()));
		    }
		   
		}
	}

	
	private void getSupplierList() {
		List<Supplier> supplierList=supplierService.fetchSuppliers();
		supplierList.forEach(supplier->{
			supplierComboList.getItems().add(supplier.getSupplierID()+" "+supplier.getSupplierName());
		});
		
	}

	@FXML
	public void addProduct(ActionEvent event){
		
		
		
		Product prod=new Product();
		if(validateProduct(add_productName.getText())) {
			
		String productName=add_productName.getText();
		String productDesc=add_prodDesc.getText();
		Double retailPrice=Calculate.getNonEmptyDoubleValue(add_retailPrice.getText());
		Double wholesalePrice=Calculate.getNonEmptyDoubleValue(add_wholesalePrice.getText());
		Double discount=Calculate.getNonEmptyDoubleValue(add_Discount.getText());
		if(discount>100.00) {
			discount=0.00;
		}
		Integer stock=Calculate.getNonEmptyIntegerValue(add_stock.getText());
		stock=stock+Calculate.getNonEmptyIntegerValue(initialStock.getText());
		String wholeSaleGSTper=wholeSaleGSTpercentage.getValue();
		String retailGSTper=retailGSTpercentage.getValue();
		if(null==wholeSaleGSTper) {
			wholeSaleGSTper="0%";
		}
		if(null==retailGSTper) {
			retailGSTper="0%";
		}
		
		if(wholeSaleGST.isSelected()) {
			wholesalePrice=Calculate.getWholeSalePrice(wholesalePrice,wholeSaleGSTper);
			System.out.println(wholesalePrice);
			prod.setWholeSaleGST(new SimpleStringProperty("Y"));
			prod.setWholeSaleGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(wholeSaleGSTper))));
		}else {
			prod.setWholeSaleGST(new SimpleStringProperty("N"));
			prod.setWholeSaleGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(wholeSaleGSTper))));
		}
		if(retailGST.isSelected()) {
			retailPrice=Calculate.getRetailPrice(retailPrice,retailGSTper);
			System.out.println(retailPrice);
			prod.setRetailGST(new SimpleStringProperty("Y"));
			prod.setRetailGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(retailGSTper))));
		}else{
			prod.setRetailGST(new SimpleStringProperty("N"));
			prod.setRetailGSTpercentage(new SimpleDoubleProperty(Double.parseDouble(Calculate.trimPercentage(retailGSTper))));
			
		}
		
		//System.out.println(productName+" "+productDesc+" "+productDesc+" "+wholesalePrice+" "+discount+" "+stock);
			
		prod.setName(new SimpleStringProperty(productName));
		prod.setDescription(new SimpleStringProperty(productDesc));
		prod.setRetailPrice(new SimpleDoubleProperty(retailPrice));
		prod.setWholesalePrice(new SimpleDoubleProperty(wholesalePrice));
		prod.setDiscount(new SimpleDoubleProperty(discount));
		prod.setStock(new SimpleIntegerProperty(stock)); 
		prod.setProductCategory(new SimpleStringProperty(productCategory.getText()));
		prod.setProductHSNCode(new SimpleStringProperty(add_PrdHSNCodes.getText()));
		
		
		if(getProdModel()!=null)
		{
			prod.setProductId(new SimpleIntegerProperty(getProdModel().getProductId()));
			productService.updateProduct(prod);
		}
		else
		productService.addProduct(prod);
		/*getRefreshedTable();*/
			
		
		((Node)(event.getSource())).getScene().getWindow().hide();
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		ManageProductController  manageProductController=( ManageProductController)applicationContext.getBean("ManageProductController");
		manageProductController.getRefreshedTable();
		}
		
	}
	private boolean validateProduct(String text) {
		if(null==text) {
			Popup.showErrorAlert(Constants.ERROR_TITLE,Constants.ERROR_COMMON_VALIDATION,AlertType.ERROR);
			return false;
		}
		else if(null!=text && text.isEmpty()) {
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
