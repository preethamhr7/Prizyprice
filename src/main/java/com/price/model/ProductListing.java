package com.price.model;

/**
 * 
 * @author Preetham
 * 
 */
public class ProductListing {
	
	
	private String BarCode;
	
	private String ProductDesc;
	
	private double AveragePrice;
	
	private double LowerPrice;
	
	private double HighestPrice;
	
	private double IdealPrice;
	
	private int NumOfPricesCollected;
	
	

	public int getNumOfPricesCollected() {
		return NumOfPricesCollected;
	}

	public void setNumOfPricesCollected(int numOfPricesCollected) {
		NumOfPricesCollected = numOfPricesCollected;
	}

	public String getBarCode() {
		return BarCode;
	}

	public void setBarCode(String barCode) {
		BarCode = barCode;
	}

	public String getProductDesc() {
		return ProductDesc;
	}

	public void setProductDesc(String productDesc) {
		ProductDesc = productDesc;
	}

	public double getAveragePrice() {
		return AveragePrice;
	}

	public void setAveragePrice(double averagePrice) {
		AveragePrice = averagePrice;
	}

	public double getLowerPrice() {
		return LowerPrice;
	}

	public void setLowerPrice(double lowerPrice) {
		LowerPrice = lowerPrice;
	}

	public double getHighestPrice() {
		return HighestPrice;
	}

	public void setHighestPrice(double highestPrice) {
		HighestPrice = highestPrice;
	}

	public double getIdealPrice() {
		return IdealPrice;
	}

	public void setIdealPrice(double idealPrice) {
		IdealPrice = idealPrice;
	}

}
