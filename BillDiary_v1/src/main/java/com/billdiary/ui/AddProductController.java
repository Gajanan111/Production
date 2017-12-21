package com.billdiary.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.billdiary.config.SpringFxmlLoader;
import com.billdiary.model.Product;
import com.billdiary.service.ProductService;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;


@Controller("AddProductController")
public class AddProductController  implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

	@Autowired
	private ProductService productService;
	@FXML
	TextField add_productName;
	@FXML
	TextField add_prodDesc;
	@FXML
	TextField add_retailPrice;

	@FXML
	TextField add_wholesalePrice;
	@FXML
	TextField add_Discount;
	@FXML
	TextField add_stock;

	@FXML
	TextField productCategory;
	@FXML
	public void addProduct(ActionEvent event){
		String productName=add_productName.getText();
		String productDesc=add_prodDesc.getText();
		Double retailPrice=Double.parseDouble(add_retailPrice.getText());
		Double wholesalePrice=Double.parseDouble(add_wholesalePrice.getText());
		Double discount=Double.parseDouble(add_Discount.getText());
		Integer stock=Integer.parseInt(add_stock.getText());
		if(productName!=null && productDesc!=null && retailPrice!=null && wholesalePrice!=null && discount!=null && stock!=null )
		{
			System.out.println(productName+" "+productDesc+" "+productDesc+" "+wholesalePrice+" "+discount+" "+stock);
			Product prod=new Product();
			prod.setName(new SimpleStringProperty(productName));
			prod.setDescription(new SimpleStringProperty(productDesc));
			prod.setRetailPrice(new SimpleDoubleProperty(retailPrice));
			prod.setWholesalePrice(new SimpleDoubleProperty(wholesalePrice));
			prod.setDiscount(new SimpleDoubleProperty(discount));
			prod.setStock(new SimpleIntegerProperty(stock)); 
			prod.setProductCategory(new SimpleStringProperty(productCategory.getText()));
			productService.addProduct(prod);
			/*getRefreshedTable();*/
			
		}	
		((Node)(event.getSource())).getScene().getWindow().hide();
		ApplicationContext applicationContext=SpringFxmlLoader.getApplicationcontext();
		ManageProductController  manageProductController=( ManageProductController)applicationContext.getBean("ManageProductController");
		manageProductController.getRefreshedTable();
		
	}
}
