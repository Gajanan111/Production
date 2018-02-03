package com.billdiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.dao.InvoiceDAO;
import com.billdiary.daoUtility.ModelTOEntityMapper;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.model.Invoice;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceDAO invoiceDAO;
	
	@Autowired
	ModelTOEntityMapper modelTOEntityMapper;

	public boolean save(Invoice inv) {
		InvoiceEntity invEntity=new InvoiceEntity();
		invEntity=modelTOEntityMapper.getInvoiceEntity(inv);
		return invoiceDAO.save(invEntity);	
	}

	public String generateInvoiceNO() {
		String invoiceNo=null;
		invoiceNo=String.valueOf(invoiceDAO.generateInvoiceNO());
		return invoiceNo;
	}

}
