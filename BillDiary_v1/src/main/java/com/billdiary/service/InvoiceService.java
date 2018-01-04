package com.billdiary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.ModelTOEntityMapper;
import com.billdiary.dao.InvoiceDAO;
import com.billdiary.entities.InvoiceEntity;
import com.billdiary.model.Invoice;

@Service
public class InvoiceService {
	
	@Autowired
	InvoiceDAO invoiceDAO;

	public boolean save(Invoice inv) {
		// TODO Auto-generated method stub
		InvoiceEntity invEntity=new InvoiceEntity();
		ModelTOEntityMapper mapper=new ModelTOEntityMapper();
		invEntity=mapper.getInvoiceEntity(inv);
		return invoiceDAO.save(invEntity);
		
		
	}

	public String generateInvoiceNO() {
		String invoiceNo=null;
		invoiceNo=String.valueOf(invoiceDAO.generateInvoiceNO());
		
		return invoiceNo;
	}

}
