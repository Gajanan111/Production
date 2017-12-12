package com.billdiary.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billdiary.dao.ProductDAO;
import com.billdiary.dao.ShopDAO;
import com.billdiary.entities.ShopEntity;
@Service
public class ShopService {
	@Autowired
	ShopDAO shopDAO;
	public void SaveShopDetails(ShopEntity addShopDetails) {
		// TODO Auto-generated method stub
		
		shopDAO.saveShopDetails(addShopDetails);
		
	}
	public List<ShopEntity> getShopDetails() {
		List<ShopEntity> shop = new ArrayList<>();
		shop = shopDAO.getShopDetails();
		
		
		return shop;
	}
	public void EditShopDetails(ShopEntity addShopDetails) {
		// TODO Auto-generated method stub
		shopDAO.EditShopDetails(addShopDetails);
	}

}
