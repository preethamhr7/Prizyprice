package com.price.model;


/**
 * 
 * @author Preetham
 * 
 */

public class ProductPriceEntry {
	
	private int BarCode;
	
	private String Store;
	
	private double Price;
	
	private String Notes;
	
	public int getBarCode() {
		return BarCode;
	}

	public void setBarCode(int barCode) {
		BarCode = barCode;
	}

	public String getStore() {
		return Store;
	}

	public void setStore(String store) {
		Store = store;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	public String getNotes() {
		return Notes;
	}

	public void setNotes(String notes) {
		Notes = notes;
	}


}
