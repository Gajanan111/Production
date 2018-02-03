package com.billdiary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.billdiary.service.GSTService;
import com.billdiary.service.PriceService;
import com.billdiary.utility.BasicCalculator;

/**
 * @author Gajanan Gaikwad
 * This class is used for calculation of all Retail and WholeSalePrice. 
 */

public class PriceServiceImpl implements PriceService {
	
	
	@Autowired
	BasicCalculator calculator;
	@Autowired
	GSTService gstService;
	
	double retailPrice;
	double wholeSalePrice;
	
	
	@Override
	public double getRetailPrice(double price, double gstPercentage, double discount) {
		if(discount!=0) {
			retailPrice=price-(calculator.getDiscountedValue(discount, price));
			retailPrice=gstService.getGSTExcludedPrice(retailPrice,gstPercentage);
		}
		return retailPrice;
	}

	@Override
	public double getWholeSalePrice(double price, double gstPercentage, double discount) {
		
		return wholeSalePrice;
	}

	@Override
	public double getRetailGSTPrice(double price, double gstPercentage, double discount) {
		
		return retailPrice;
	}

	@Override
	public double getWholeSaleGSTPrice(double price, double gstPercentage, double discount) {
		
		return wholeSalePrice;
	}

	
}
