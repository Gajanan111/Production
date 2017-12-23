package com.billdiary.utility;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;


import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.billdiary.model.Product;

@Component
public class Calculate {

	static StringBuffer buffer=new StringBuffer();
	static DecimalFormat df=new DecimalFormat("0.00");
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
	public static double getWholeSalePrice(double price,String percentageString) {
		double wholeSalePrice=0.0;
		if(null!=percentageString) {
			
			percentageString=percentageString.replace('%', ' ');
			percentageString=percentageString.trim();
			if(StringUtils.isNumeric(percentageString)){
				
				if(!("0".equals(percentageString))) {
				double percentage=Double.parseDouble(percentageString);
				price=price*100.00;
				percentage=percentage+100.00;
				wholeSalePrice=price/percentage;
				}else {
					wholeSalePrice=price;
				}
			}
		}else {
			wholeSalePrice=price;
		}
		
		String formate = df.format(wholeSalePrice); 
		try {
			wholeSalePrice=Double.valueOf(df.parse(formate).doubleValue());
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wholeSalePrice;
	}
	public static double getRetailPrice(double price,String percentageString){
		double  retailPrice=0.0;
		if(null!=percentageString) {
			percentageString=percentageString.replace('%', ' ');
			percentageString=percentageString.trim();
			if(StringUtils.isNumeric(percentageString)){
				if(!("0".equals(percentageString))) {
				double percentage=Double.parseDouble(percentageString);
				price=price*100.00;
				percentage=percentage+100.00;
				retailPrice=price/percentage;
				
				}else {
					retailPrice=price;
				}
			}
		}else {
			retailPrice=price;
		}
		
		String formate = df.format(retailPrice); 
		try {
			retailPrice=Double.valueOf(df.parse(formate).doubleValue());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retailPrice;
	}
	
	
	
	public static String trimPercentage(String percentage) {
		
		percentage=percentage.replace('%', ' ');
		percentage=percentage.trim();
		return percentage;
		
	}
}
