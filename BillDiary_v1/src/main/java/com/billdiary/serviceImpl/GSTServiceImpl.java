package com.billdiary.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.billdiary.service.GSTService;
import com.billdiary.utility.BasicCalculator;

public class GSTServiceImpl implements GSTService {

	@Autowired
	BasicCalculator calculator;
	
	private double gstPercentage;
	private double gstAmount;
	private double gstIncludedPrice;
	private double gstExcludedPrice;
	public double getGSTAmount(double amount,double percentage) {
		if(percentage!=0) {
			
		}
		return gstAmount;
	}
	public double getGSTPercentage(double oldamount,double newAmount) {
		return gstPercentage;
	}
	
	public double getGSTIncludedPrice(double price,double percentage) {
		return gstIncludedPrice;
	}
	public double getGSTExcludedPrice(double price,double percentage) {
		if(percentage!=0) {
			price=price*100;
			percentage=percentage+100;
			gstExcludedPrice=price/percentage;
		}
		return gstExcludedPrice;
	}
}
