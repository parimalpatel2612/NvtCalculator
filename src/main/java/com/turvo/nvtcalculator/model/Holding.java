package com.turvo.nvtcalculator.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Holding {
	private String date;
	private String security;
	private int quantity;
	private String portfolio;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSecurity() {
		return security;
	}
	public void setSecurity(String security) {
		this.security = security;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPortfolio() {
		return portfolio;
	}
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	@Override
	public String toString() {
		return "Holding [date=" + date + ", security=" + security + ", quantity=" + quantity + ", portfolio="
				+ portfolio + "]";
	}
	
}