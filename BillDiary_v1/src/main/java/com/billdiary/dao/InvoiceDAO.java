package com.billdiary.dao;

import java.math.BigInteger;
import java.util.Random;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.InvoiceEntity;


@Repository
public class InvoiceDAO extends AbstractJpaDAO< InvoiceEntity >{

	
	public InvoiceDAO() {
		setClazz(InvoiceEntity.class);
	}
	
	/**
	 *Generation of InvoiceNo
	 * @return
	 */
	public Long generateInvoiceNO() {
		Long invoiceNo=null;
		Query query=entityManager.createNativeQuery("select max(invoice_id) from invoice");
		BigInteger result=(BigInteger) query.getSingleResult();
		System.out.println("result"+result);
		if(result==null)
		{
			invoiceNo=100L;
		}
		else
		{
			invoiceNo=result.longValue()+1;
		}
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
