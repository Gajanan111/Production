package com.billdiary.dao;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.ProductCategoryEntity;
import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.UnitEntity;

@Repository
public class ProductDAO extends AbstractJpaDAO< ProductEntity >{

	@PersistenceContext
	EntityManager entityManager;
	
	@Autowired 
	GenericDAO genericDAO;
	
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
	public ProductEntity updateProductStock(int id, double quantity) {
		
		
		ProductEntity productEntity=entityManager.find(ProductEntity.class, id);
		productEntity.setStock(productEntity.getStock()-quantity);
		entityManager.merge(productEntity);
		return productEntity;
	}
	
	@Transactional
	public ProductEntity purchaseProductStock(int id, double quantity) {
		
		
		ProductEntity productEntity=entityManager.find(ProductEntity.class, id);
		productEntity.setStock(productEntity.getStock()+quantity);
		entityManager.merge(productEntity);
		return productEntity;
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
		System.out.println("*********"+ "getProductCount: "+count);
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
		System.out.println("*********"+ "getProducts : end");
		return productEntities;
	}
	
	
	public boolean checkProductCode(long code) {
		boolean flag=false;
		try {
			Query query=entityManager.createNativeQuery("select count(*) from product where product_code="+code);
			BigInteger result=(BigInteger) query.getSingleResult();
			System.out.println("result"+result);
			if(result.intValue()>0) {
				flag=true;
			}
		}catch(Exception e) {
			System.out.println(e+ " "+e.getMessage());
		}
		return flag;
	}
	
	public long getProductCode() {
		long code=0;
		try {
			Query query=entityManager.createNativeQuery("select max(product_code) from product");
			BigInteger result=(BigInteger) query.getSingleResult();
			System.out.println("result"+result);
			code=result.longValue()+1;
		}catch(Exception e) {
			System.out.println(e+ " "+e.getMessage());
		}
		return code;
	}
	
	public List<ProductEntity> bulkUpdateProduct(List<ProductEntity> entities){
		List<ProductEntity> updatedEntities=(List<ProductEntity>) genericDAO.bulkUpdate(entities);
		return updatedEntities;
		
	}
	
	
	
}