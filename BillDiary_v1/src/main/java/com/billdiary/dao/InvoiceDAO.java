package com.billdiary.dao;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.InvoiceEntity;
import com.billdiary.entities.ProductEntity;

@Repository
public class InvoiceDAO extends AbstractJpaDAO< InvoiceEntity >{

	
	public InvoiceDAO() {
		setClazz(InvoiceEntity.class);
	}
	public void save(InvoiceEntity invEntity) {
		// TODO Auto-generated method stub
		create(invEntity);
	}

}
