package com.price.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.price.dao.PrizyPriceDao;
import com.price.model.Product;
import com.price.model.ProductListing;


@Service
public class PrizyPriceServiceImpl implements PrizyPriceService{
	
	
	@Autowired
	PrizyPriceDao prizyPriceDao;
	
	@Transactional
	public boolean createPriceEntry(String Barcode, double Price, String Store, String Notes){
		
		return prizyPriceDao.createPriceEntry(Barcode, Price, Store, Notes);
	}
	
	
	@Transactional
	public List<Product> listProducts(){
		
		return prizyPriceDao.listProducts();
	}
	
	
	@Transactional
	public ProductListing productDetailsFromBarcode(int Barcode){
		
		return prizyPriceDao.productDetailsFromBarcode(Barcode);
	}

}

