package com.billdiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.dao.InvoiceDAO;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.model.Invoice;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceDAO invoiceDAO;

	public void save(Invoice inv) {
		// TODO Auto-generated method stub
		InvoiceEntity invEntity=new InvoiceEntity();
		invoiceDAO.save(invEntity);
		
		
	}

}
