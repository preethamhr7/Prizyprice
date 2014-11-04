package com.price.model;

/**
 * 
 * @author Preetham
 * 
 */


public class Product {
	
	private int id;
	
	private String ProductName;
	
	private String ProductDesc;
	
	private int BarCode;

	public int getBarCode() {
		return BarCode;
	}

	public void setBarCode(int barCode) {
		BarCode = barCode;
	}

	public String getProductDesc() {
		return ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		ProductDesc = productDesc;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
