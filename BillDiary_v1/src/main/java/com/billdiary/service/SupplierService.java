package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.DAOUtility.EntityTOModelMapper;
import com.billdiary.DAOUtility.ModelTOEntityMapper;
import com.billdiary.dao.SupplierDAO;
import com.billdiary.entities.SupplierEntity;
import com.billdiary.model.Supplier;

@Service
public class SupplierService {

	@Autowired
	SupplierDAO supplierDAO;
	
	public void addNewSupplier(Supplier sup) {
		ModelTOEntityMapper mapper=new ModelTOEntityMapper();
		SupplierEntity supEnitity=mapper.getSupplierEntity(sup);
		boolean supAdded=supplierDAO.addNewSupplier(supEnitity);
		if(supAdded) {
			System.out.println("Suppplier added");
		}else {
			System.out.println("supplier not added");
		}
	}

	public List<Supplier> fetchSuppliers() {
		// TODO Auto-generated method stub
		List<Supplier> supplierList=new ArrayList<>();
		List<SupplierEntity> supplierEntityList=new ArrayList<>();
		supplierEntityList=supplierDAO.fetchSuppliers();
		EntityTOModelMapper mapper=new EntityTOModelMapper();
		
		supplierList=mapper.getSupplierModels(supplierEntityList);
		return supplierList;
	}
}
