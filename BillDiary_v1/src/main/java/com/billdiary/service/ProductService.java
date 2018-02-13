package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.dao.ProductDAO;
import com.billdiary.dao.UnitDAO;
import com.billdiary.daoUtility.EntityTOModelMapper;
import com.billdiary.daoUtility.ModelTOEntityMapper;
import com.billdiary.entities.ProductCategoryEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.UnitEntity;
import com.billdiary.model.Product;
import com.billdiary.model.Unit;

import javafx.collections.ObservableList;

@Service
public class ProductService {
	@Autowired
	ProductDAO productDAO;
	@Autowired
	UnitDAO unitDAO;
	
	@Autowired
	EntityTOModelMapper entityTOModelMapper;
	
	@Autowired
	ModelTOEntityMapper modelTOEntityMapper;
	
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
		productList=entityTOModelMapper.getProductModels(productEntityList);
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
	public List<Product> saveProduct(ObservableList<Product> obproductList) {
		List<ProductEntity>  productEntityList = modelTOEntityMapper.getProdEntitiesFromObservableList(obproductList);
		List<ProductEntity> updatedProdEntities = new ArrayList<>();
		updatedProdEntities=productDAO.saveCustomer(productEntityList);
		List<Product> productList =new ArrayList<>();
		productList=entityTOModelMapper.getProductModels(updatedProdEntities);
		return productList;	
	}
	public boolean addProduct(Product prod) {
		boolean productAdded=false;
		ProductEntity prodEntity=modelTOEntityMapper.getProductEntity(prod);
		productAdded=productDAO.addProduct(prodEntity);
		return productAdded;
		
	}
	
	public Product updateProduct(Product product) {
		Product updatedProduct=null;
		ProductEntity updatedProEnitity=null;
		ModelTOEntityMapper mapper=new ModelTOEntityMapper();
		ProductEntity proEntity=mapper.getProductEntity(product);
		proEntity.setId(product.getProductId());
		updatedProEnitity=productDAO.updateProduct(proEntity);
		updatedProduct=entityTOModelMapper.getProductModel(updatedProEnitity);
		return updatedProduct;
	}
	public List<String> getCategoryList() {
		List<String> categoryList=new ArrayList<>();
		List<ProductCategoryEntity> categoryEntityList=productDAO.getCategoryList();
		categoryList=entityTOModelMapper.getProductCategoryList(categoryEntityList);
		return categoryList;
	}
	public List<Unit> getUnitList() {
		List<Unit> unitList=new ArrayList<>();
		List<UnitEntity> unitEntityList=productDAO.getUnitList();
		unitList=entityTOModelMapper.getUnitList(unitEntityList);
		return unitList;
	}
	public boolean addUnit(String unitName) {
		UnitEntity unitEntity=new UnitEntity();
		unitEntity.setName(unitName);
		unitEntity.setUnitId(0);
		boolean flag=productDAO.addUnit(unitEntity);
		return flag;
	}
	public boolean deleteUnit(long unitId) {
		boolean deleted=false;
		deleted=unitDAO.deleteUnit(unitId);
		return deleted;
	}
	public boolean updateProductStock(int id,double stock) {
		boolean updateStock=productDAO.updateProductStock(id,stock);
		return updateStock;
	}
	
	
}
