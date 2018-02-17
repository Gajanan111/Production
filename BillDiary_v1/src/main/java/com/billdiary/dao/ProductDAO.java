package com.billdiary.dao;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		List<UnitEntity> unitEntityList=new ArrayList<>();
		unitEntityList=entityManager.createQuery( "from " + UnitEntity.class.getName() )
			       .getResultList();
		return unitEntityList;
	}
	
	@Transactional
	public boolean addUnit(UnitEntity unitEntity) {
		boolean added=false;
		entityManager.persist(unitEntity);
		added=true;
		return added;
	}
	
	@Transactional
	public boolean updateProductStock(int id, double stock) {
		boolean updateStock=false;
		
		ProductEntity productEntity=entityManager.find(ProductEntity.class, id);
		productEntity.setStock(stock);
		entityManager.merge(productEntity);
		updateStock=true;
		return updateStock;
	}
	
	/**
	 * To get the total count of products from database
	 * @return
	 */
	public long getProductCount() {
		System.out.println("*********"+ "getProductCount");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(ProductEntity.class)));
		Long count = entityManager.createQuery(countQuery).getSingleResult();
		return count;
	}
	public List<ProductEntity> getProducts(int pages, int pageNumber,int rowsPerPage) {
		System.out.println("*********"+ "getProducts");
		List<ProductEntity> productEntities=null;
		try {
			if(pageNumber<0)
				return null;
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			/*CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
			countQuery.select(criteriaBuilder.count(countQuery.from(ProductEntity.class)));
			Long count = entityManager.createQuery(countQuery).getSingleResult();*/
			CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
			Root<ProductEntity> from = criteriaQuery.from(ProductEntity.class);
			CriteriaQuery<ProductEntity> select = criteriaQuery.select(from);
			TypedQuery<ProductEntity> typedQuery = entityManager.createQuery(select);
			typedQuery.setFirstResult((pageNumber*rowsPerPage));
		    typedQuery.setMaxResults(rowsPerPage);
		    productEntities=typedQuery.getResultList();
			/*while (pageNumber < count.intValue()) {
			    typedQuery.setFirstResult(pageNumber - 1);
			    typedQuery.setMaxResults(rowsPerPage);
			    //System.out.println("Current page: " + typedQuery.getResultList());
			    productEntities=typedQuery.getResultList();
			    pageNumber += rowsPerPage;
			}*/
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return productEntities;
	}
	
	
	
	
	
}