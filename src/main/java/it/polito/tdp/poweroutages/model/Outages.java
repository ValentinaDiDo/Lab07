package it.polito.tdp.poweroutages.model;

import java.sql.Date;

public class Outages {

	private int customers;
	private Date startDate;
	private Date endDate;
	private int span;
	private int anno;
	
	public Outages(int customers, Date startDate, Date endDate, int span, int anno) {
		super();
		this.customers = customers;
		this.startDate = startDate;
		this.endDate = endDate;
		this.span = span;
		this.anno = anno;
	}
	public int getCustomers() {
		return customers;
	}
	public Date getStartDate() {
		return startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public int getSpan() {
		return span;
	}
	public int getAnno() {
		return anno;
	}
	@Override
	public String toString() {
		return "Outages: customers: " + customers + ", startDate: " + startDate + ", endDate: " + endDate + ", span: "
				+ span + ", anno: " + anno;
	}
	

	
}
