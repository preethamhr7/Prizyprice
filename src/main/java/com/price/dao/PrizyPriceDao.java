package com.price.dao;

import java.util.List;

import com.price.model.Product;
import com.price.model.ProductListing;

public interface PrizyPriceDao {
	
	
	public boolean createPriceEntry(String Barcode, double Price, String Store, String Notes);
	
	public List<Product> listProducts();
	
	public ProductListing productDetailsFromBarcode(int Barcode);

}
