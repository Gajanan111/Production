package com.billdiary.utility;

import org.springframework.stereotype.Component;

import com.billdiary.model.Product;

@Component
public class Calculate {

	
	public Double getTotalPrice(Product product)
	{
		Double price=0.0;
		price=product.getRetailPrice()*product.getQuantity();
		if(product.getDiscount()>0) {
			Double dis=product.getRetailPrice()*product.getQuantity()*(product.getDiscount()/100);
			price=price-dis;
		}
			
		return price;
	}
}
