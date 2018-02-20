package com.billdiary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.CustomerEntity;
import com.billdiary.entities.SupplierEntity;
import com.billdiary.model.Supplier;

@Repository
public class SupplierDAO extends AbstractJpaDAO< SupplierEntity > {

	
	public SupplierDAO() {
		setClazz(SupplierEntity.class);
	}
	@Transactional
	public boolean addNewSupplier(SupplierEntity supEntity) {
		boolean supAdded=false;
		create(supEntity);
		supAdded=true;
		return supAdded;
	}
	public List<SupplierEntity> fetchSuppliers() {
		// TODO Auto-generated method stub
		List<SupplierEntity> supplierEntityList=new ArrayList<>();
		supplierEntityList=findAll();
		return supplierEntityList;
	}
	@Transactional
	public void removeSupplier(int id) {
		// TODO Auto-generated method stub
		SupplierEntity supEntity=findEntity(id);
		delete(supEntity);
	}
	@Transactional
	public SupplierEntity updateSupplier(SupplierEntity supEntity) {
		// TODO Auto-generated method stub
		SupplierEntity updatedSupEntity=new SupplierEntity();
		updatedSupEntity=update(supEntity);
		return updatedSupEntity;
		
	}
	
	public long getSupplierCount() {
		System.out.println("*********"+ "getSupplierCount");
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
		countQuery.select(criteriaBuilder.count(countQuery.from(SupplierEntity.class)));
		Long count = entityManager.createQuery(countQuery).getSingleResult();
		System.out.println("*********"+ "getSupplierCount: "+count);
		return count;
	}
	public List<SupplierEntity> getSuppliers(int pages, int pageNumber,int rowsPerPage) {
		System.out.println("*********"+ "getSuppliers");
		List<SupplierEntity> supplierEntities=null;
		try {
			if(pageNumber<0)
				return null;
			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			/*CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
			countQuery.select(criteriaBuilder.count(countQuery.from(ProductEntity.class)));
			Long count = entityManager.createQuery(countQuery).getSingleResult();*/
			CriteriaQuery<SupplierEntity> criteriaQuery = criteriaBuilder.createQuery(SupplierEntity.class);
			Root<SupplierEntity> from = criteriaQuery.from(SupplierEntity.class);
			CriteriaQuery<SupplierEntity> select = criteriaQuery.select(from);
			TypedQuery<SupplierEntity> typedQuery = entityManager.createQuery(select);
			typedQuery.setFirstResult((pageNumber*rowsPerPage));
		    typedQuery.setMaxResults(rowsPerPage);
		    supplierEntities=typedQuery.getResultList();
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
		System.out.println("*********"+ "getSuppliers : end");
		return supplierEntities;
	}
	
}
