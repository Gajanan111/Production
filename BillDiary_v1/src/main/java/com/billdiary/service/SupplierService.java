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
		System.out.println("fff");
		EntityTOModelMapper mapper=new EntityTOModelMapper();
		try {
		supplierList=mapper.getSupplierModels(supplierEntityList);
		}catch(Exception e) {
			System.out.println("here");
		}
		System.out.println("ffff");
		return supplierList;
	}

	public void removeSupplier(int supID) {
		// TODO Auto-generated method stub
		
		supplierDAO.removeSupplier(supID);
	}

	public Supplier updateSupplier(Supplier sup) {
		// TODO Auto-generated method stub
		ModelTOEntityMapper mapper= new ModelTOEntityMapper();
		EntityTOModelMapper mapper2=new EntityTOModelMapper();
		SupplierEntity supEntity=new SupplierEntity();
		supEntity=mapper.getSupplierEntity(sup);
		supEntity.setSupplierID(sup.getSupplierID());
		SupplierEntity updatedSupEntity=supplierDAO.updateSupplier(supEntity);
		sup=mapper2.getSupplierModel(updatedSupEntity);
		return sup;
	}
}
