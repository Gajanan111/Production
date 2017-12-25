package com.billdiary.model;

import java.time.LocalDate;

public class Invoice {

	
	public Long invoiceID;
	private int customerID;
	private int product_sale_qty;
	private double finalAmount;
	private double paidAmount;
	private double amountDue;
	private LocalDate invoiceDate;
	private LocalDate invoiceDueDate;
	private LocalDate lastAmountPaidDate;
	
	public Long getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(Long invoiceID) {
		this.invoiceID = invoiceID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getProduct_sale_qty() {
		return product_sale_qty;
	}
	public void setProduct_sale_qty(int product_sale_qty) {
		this.product_sale_qty = product_sale_qty;
	}
	public double getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(double finalAmount) {
		this.finalAmount = finalAmount;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getAmountDue() {
		return amountDue;
	}
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
	}
	public LocalDate getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(LocalDate invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public LocalDate getInvoiceDueDate() {
		return invoiceDueDate;
	}
	public void setInvoiceDueDate(LocalDate invoiceDueDate) {
		this.invoiceDueDate = invoiceDueDate;
	}
	public LocalDate getLastAmountPaidDate() {
		return lastAmountPaidDate;
	}
	public void setLastAmountPaidDate(LocalDate lastAmountPaidDate) {
		this.lastAmountPaidDate = lastAmountPaidDate;
	}
}
