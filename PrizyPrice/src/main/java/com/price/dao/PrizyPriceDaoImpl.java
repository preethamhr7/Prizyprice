package com.price.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.price.controller.HomeController;
import com.price.model.BarCode;
import com.price.model.Product;
import com.price.model.ProductListing;
import com.price.model.ProductPriceEntry;



@Repository
@Component
public class PrizyPriceDaoImpl implements PrizyPriceDao{
	
	
	private static final Logger logger = LoggerFactory.getLogger(PrizyPriceDaoImpl.class);

	@Autowired
	private NamedParameterJdbcTemplate jdbc;
	 
	
	@Autowired
	 private JdbcTemplate jdbcTemplateObject;
	
	@Value("${price.highestRemoval}")
    private int NumOfHighest;
	
	@Value("${price.lowestRemoval}")
    private int NumOfLowest;
	
	@Value("${price.addPercent}")
    private int AddPercentage;
	
	
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}
	
	
	public boolean createPriceEntry(String Barcode, double Price, String Store, String Notes){
		
		String saveProductPrice = "Insert into ProductPriceEntries (BarCode , Price, Store , Notes ) Values (?, ?, ?, ?);";
		
		int id = jdbcTemplateObject.update(saveProductPrice, Barcode, Price, Store, Notes);
		
		if(id!= 0){
			return true;	
		}
		
		return false;
	}
	
	
	
	public List<Product> listProducts(){
		
		String getAllProducts = "Select * from Product;"; 
		Map<String, Object> params = new HashMap<String, Object>();
		List<Product> products = jdbc.query(getAllProducts, params, new BeanPropertyRowMapper<Product>(Product.class));
		
		if(products != null){
		
		return products;
		}
		
		return null;
	}
	
	
	public ProductListing productDetailsFromBarcode(int Barcode){
		
		String getProductsForBarcode = "Select * from ProductPriceEntries where BarCode =:BarCode;";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("BarCode", Barcode);
		
		List<ProductPriceEntry> products = jdbc.query(getProductsForBarcode, params, new BeanPropertyRowMapper<ProductPriceEntry>(ProductPriceEntry.class));
		logger.debug(products.toString());
		
		
		
		if(products != null && ! products.isEmpty()){
			
			if(sum(NumOfLowest, NumOfHighest) <  products.size()){
			
			ProductListing product = calcPrice(products);
			
			logger.info("returned product" + product.getBarCode());
			return product;
			}else{
				return null;
			}
			}
			
			return null;
	}
	
	
	public ProductListing calcPrice(List<ProductPriceEntry> productPriceEntry){
		
		
		
		ProductListing product = new ProductListing();
		int productEntryCount = productPriceEntry.size();
		double average = 0;
		double sum = 0;
		int count = 0;
		int barCodeId = productPriceEntry.get(0).getBarCode();
		logger.info("Bar code id is : "+barCodeId);
	
		String BarCode = getBarCodeById(barCodeId);
		String ProductDesc = getProductDesc(BarCode);
		
		logger.info("Bar code from id is "+BarCode+" And product  Desc  is :"+ProductDesc);
		product.setProductDesc(ProductDesc);
		product.setBarCode(BarCode);
		product.setNumOfPricesCollected(productEntryCount);
		
		List<Double> priceList = new ArrayList<Double>();
		
		for(ProductPriceEntry productPrice : productPriceEntry){
			priceList.add(productPrice.getPrice());
		}
		
		logger.info("Highest  Price is : "+ Collections.max(priceList));
		product.setHighestPrice(Collections.max(priceList));
		product.setLowerPrice(Collections.min(priceList));
		logger.info("Lower  Price is : "+ Collections.min(priceList));
		for(int i=0; i<NumOfHighest ; i++){
		
			double maxPrice = Collections.max(priceList);
			int index = priceList.indexOf(maxPrice);
			priceList.remove(index);
			logger.info("removed : "+maxPrice);
		}
		
		
		for(int i=0; i<NumOfLowest ; i++){
			
			double minPrice = Collections.min(priceList);
			int index = priceList.indexOf(minPrice);
			priceList.remove(index);
			logger.info("removed : "+minPrice);
		}
		
		for(double price: priceList){
			logger.info("prices left : "+price);
		}
		
		
		for(double price: priceList){
			sum = sum + price;
			count++;
		}
		
		average = sum/count;
		
		logger.info("Average is "+average);
		
		product.setAveragePrice(average);
		
		double result = AddPercentage * sum / 100;
		
		product.setIdealPrice(result + average);
		
		return product;
	}
	
	
	public String getProductDesc(String BarCode){
		
		
		String getProductForBarCode = "Select * from Product where BarCode=:BarCode;"; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("BarCode", BarCode);
		List<Product> products = jdbc.query(getProductForBarCode, params, new BeanPropertyRowMapper<Product>(Product.class));
		
		if(products != null && ! products.isEmpty()){
		
		return products.get(0).getProductDesc();
		}
		return null;	
	}
	
	public String getBarCodeById(int BarCode){
		
		
		String getBarCode = "Select * from BarCode where id=:BarCode;"; 
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("BarCode", BarCode);
		List<BarCode> products = jdbc.query(getBarCode, params, new BeanPropertyRowMapper<BarCode>(BarCode.class));
		
		if(products != null && ! products.isEmpty()){
		
		return products.get(0).getBarCode();
		}
		return null;	
	}
	
	public int sum(int Lowest , int highest){
		int res = Lowest + highest;
		
		return res;
	}

}
