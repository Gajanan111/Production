package com.billdiary.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.billdiary.entities.ProductEntity;
import com.billdiary.entities.ShopEntity;


import javafx.fxml.FXML;


@Repository
public class ShopDAO extends AbstractJpaDAO< ShopEntity >{
	@PersistenceContext
	EntityManager entityManager;

	
		public ShopDAO()
		{
			setClazz(ShopEntity.class);
		}
		@Transactional
		public void saveShopDetails(ShopEntity addShopDetails) {
			create(addShopDetails);
			System.out.println("Saved Details");
			
		
	}
		public List<ShopEntity> getShopDetails() {
			
			List<ShopEntity> shop =new ArrayList<>();
			shop=findAll();
			return shop;
		}
		@Transactional
		public void EditShopDetails(ShopEntity addShopDetails) {
			List<ShopEntity> shopDetails =new ArrayList<>();
			shopDetails=getShopDetails();
			int i=(int)shopDetails.get(0).getShopId();
			ShopEntity c=entityManager.find(ShopEntity.class, i);
			entityManager.remove(c);
			create(addShopDetails);
			System.out.println("Edited Details");
		}
	
		
}
