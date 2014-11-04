package com.price.service;

import java.util.List;

import com.price.model.Product;
import com.price.model.ProductListing;

public interface PrizyPriceService {
	
	
	public boolean createPriceEntry(String Barcode, double Price, String Store, String Notes);
	
	public List<Product> listProducts();
	
	public ProductListing productDetailsFromBarcode(int Barcode);

}
