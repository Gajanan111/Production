package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.billdiary.DAOUtility.Mapper;
import com.billdiary.dao.ProductDAO;
import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.model.Customer;
import com.billdiary.model.Product;

import javafx.collections.ObservableList;

@Service
public class ProductService {
	@Autowired
	ProductDAO productDAO;
	
	public List<Product> fetchProducts()
	{
		List<Product> productList=new ArrayList<>();
		List<ProductEntity> productEntityList=new ArrayList<>();
		try {
			productEntityList=productDAO.fetchProducts();
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		Mapper m=new Mapper();
		productList=m.getProductModels(productEntityList);
		
		return productList;
	}
	public boolean deleteProduct(int productId)
	{
		boolean productDeleted=false;
		try {
			productDeleted=productDAO.deleteProduct(productId);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return productDeleted;
	}
	public List<Product> saveCustomer(ObservableList<Product> obproductList) {
		// TODO Auto-generated method stub
		Mapper m=new Mapper();
		List<ProductEntity>  productEntityList = m.getProdEntitiesFromObservableList(obproductList);
		List<ProductEntity> updatedProdEntities = new ArrayList<>();
		updatedProdEntities=productDAO.saveCustomer(productEntityList);
		List<Product> productList =new ArrayList<>();
		productList=m.getProductModels(updatedProdEntities);
		
		return productList;	
	}
	public boolean addProduct(Product prod) {
		Mapper m=new Mapper();
		boolean productAdded=false;
		ProductEntity prodEntity=m.getProductEntity(prod);
		productAdded=productDAO.addProduct(prodEntity);
		return productAdded;
		
	}
	
}
