package com.billdiary.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "invoice")
public class InvoiceEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "supplier_id")
	private Long invoiceID;
	
	private int customerID;
	private int product_sale_qty;
	private double finalAmount;
	private double paidAmount;
	private double amountDue;
	private LocalDate invoiceDate;
	private LocalDate invoiceDueDate;
	private LocalDate lastAmountPaidDate;
}
