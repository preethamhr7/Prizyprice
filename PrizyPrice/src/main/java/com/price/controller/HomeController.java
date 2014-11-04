package com.price.controller;


import java.util.List;
import java.util.Locale;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.price.model.Product;
import com.price.model.ProductListing;
import com.price.service.PrizyPriceService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	@Autowired
	PrizyPriceService prizyPriceService;
	
	
	Product product;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "home";
	}
	
	@RequestMapping(value = "/PriceEntryPage", method = RequestMethod.GET)
	public String priceEntryPage() {
	
		return "ProductLoader";
	}
	
	
	@RequestMapping(value = "/PricePage", method = RequestMethod.GET)
	public String pricePage() {

		return "Price";
	}
	
	@RequestMapping(value = "/PriceEntry", method = RequestMethod.GET)
	public String priceEntry(@RequestParam String Barcode, @RequestParam double Price, @RequestParam String Store, @RequestParam String Notes, Model model) {
		logger.info("Welcome price Entry! The Barcode we got is : "+ Barcode);
		
		boolean result = prizyPriceService.createPriceEntry(Barcode, Price, Store,  Notes);
		
		
		if(result){
			return "success";
		}
		
		model.addAttribute("error", "Could not save the price!! Please check the values entered!!!");
		return "failure";
	}
	
	
	@RequestMapping(value = "/ProductList", method = RequestMethod.GET)
	@ResponseBody
	public List<Product> productList(Model model) {
		
		List<Product> product = prizyPriceService.listProducts();
		return product;	
	}
	
	
	@RequestMapping(value = "/ProductPrice", method = RequestMethod.GET)
	public String productPrice(@RequestParam int Barcode, Model model) {
		
		
		ProductListing result = prizyPriceService.productDetailsFromBarcode(Barcode);
		if(result != null){
		model.addAttribute("BarCode", result.getBarCode());
		model.addAttribute("ProductDesc", result.getProductDesc());
		model.addAttribute("AveragePrice", result.getAveragePrice());
		model.addAttribute("LowerPrice", result.getLowerPrice());
		model.addAttribute("HighestPrice", result.getHighestPrice());
		model.addAttribute("IdealPrice", result.getIdealPrice());
		model.addAttribute("NumOfPricesCollected", result.getNumOfPricesCollected());
		
		
			return "PricePage";	
		}
		
		else{
			
			model.addAttribute("error", "Could not get the price!! There is no minimum number of prices entered Or BarCode entered by u is not correct!!!");
			return "failure";
		}
		
		
	}
	
	
}
