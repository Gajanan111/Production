package com.billdiary.dao;

import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.InvoiceEntity;


@Repository
public class InvoiceDAO extends AbstractJpaDAO< InvoiceEntity >{

	
	public InvoiceDAO() {
		setClazz(InvoiceEntity.class);
	}
	public Long generateInvoiceNO() {
		
		long range = 1234567L;
		Random r = new Random();
		Long invoiceNo= (long)(r.nextDouble()*range);
		return invoiceNo;
	}
	
	@Transactional
	public boolean save(InvoiceEntity invEntity) {
		// TODO Auto-generated method stub
		boolean invoiceSaved=false;
		create(invEntity);
		invoiceSaved=true;
		return invoiceSaved;
	}

}
