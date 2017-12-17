package com.billdiary.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
	
}
