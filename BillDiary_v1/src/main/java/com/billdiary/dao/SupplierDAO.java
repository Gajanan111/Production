package com.billdiary.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.SupplierEntity;

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
	
}
