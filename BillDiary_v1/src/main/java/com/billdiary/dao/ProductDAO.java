package com.billdiary.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.ProductCategoryEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.UnitEntity;

@Repository
public class ProductDAO extends AbstractJpaDAO< ProductEntity >{

	@PersistenceContext
	EntityManager entityManager;
	
	public ProductDAO()
	{
		setClazz(ProductEntity.class );
	}
	public List<ProductEntity> fetchProducts()
	{
		List<ProductEntity> p=findAll();
		return p;
	}
	@Transactional
	public boolean deleteProduct(int productId)
	{
		boolean productDeleted=false;
		//deleteById(id);
		int i=(int)productId;
		ProductEntity c=entityManager.find(ProductEntity.class, i);
		entityManager.remove(c);
		productDeleted=true;
		return productDeleted;
	}
	@Transactional
	public List<ProductEntity> saveCustomer(List<ProductEntity> productEntityList) {
		List<ProductEntity> updatedProdEntities = new ArrayList<>();
		for(ProductEntity prodEntity:productEntityList)
		{
			ProductEntity productEntity=entityManager.merge(prodEntity);
			updatedProdEntities.add(productEntity);
		}
		return updatedProdEntities;
	}
	
	@Transactional
	public boolean addProduct(ProductEntity prodEntity) {
		boolean productAdded=false;
		create(prodEntity);
		productAdded=true;
		return productAdded;
	}
	
	@Transactional
	public ProductEntity updateProduct(ProductEntity proEntity) {
		// TODO Auto-generated method stub
		ProductEntity updatedProduct=null;
		updatedProduct=update(proEntity);
		return updatedProduct;
	}
	@SuppressWarnings("unchecked")
	public List<ProductCategoryEntity> getCategoryList() {
		List<ProductCategoryEntity> categoryListEntity=new ArrayList<>();
		categoryListEntity=entityManager.createQuery( "from " + ProductCategoryEntity.class.getName() )
	       .getResultList();
		return categoryListEntity;
	}
	@SuppressWarnings("unchecked")
	public List<UnitEntity> getUnitList() {
		List<UnitEntity> UnitEntityList=new ArrayList<>();
		UnitEntityList=entityManager.createQuery( "from " + UnitEntity.class.getName() )
			       .getResultList();
		return UnitEntityList;
	}
	
	@Transactional
	public boolean addUnit(UnitEntity unitEntity) {
		boolean added=false;
		entityManager.persist(unitEntity);
		added=true;
		return added;
	}
	
	
	
	
	
}