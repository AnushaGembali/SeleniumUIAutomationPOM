package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class ProductPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;
	
	private String productName;
	public String getProductName() {
		return productName;
	}

	public ProductPage(WebDriver driver, String productName) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
		this.productName = productName;
	}
	
	private By productNameHeader = By.tagName("h1");
	private By productPrice = By.xpath("//h2[contains(text(),'$')]");
	private By addToCartButton = By.id("button-cart");
	private By productQuantity = By.id("input-quantity");
//	private By successMessage = By.cssSelector("div#product-product>ul+div");
	private By prodMetaData = By.cssSelector("#content ul.list-unstyled:nth-child(3)>li");
	private By prodPriceData = By.cssSelector("#content ul.list-unstyled:nth-child(4)>li");
	
	
	public String getProductPageTitle() {
		String actualTitle = brUtil.doGetPageTitleContains(productName, AppConstants.DEFAULT_SHORT_TIMEOUT);
		return actualTitle;
	}
	
	public String getProductPageHeader() {
		String actualHeader = eleUtil.doGetElementText(productNameHeader, AppConstants.DEFAULT_SHORT_TIMEOUT);
		return actualHeader;
	}
	
	public boolean isQtyTextFieldEnabled() {
		boolean isQtyTextFieldEnabled = eleUtil.doIsElementEnabled(productQuantity);
		return isQtyTextFieldEnabled;
	}
	
	public boolean isAddToCartBtnEnabled() {
		boolean isBtnEnabled = eleUtil.doIsElementEnabled(addToCartButton);
		return isBtnEnabled;
	}
	
	public void addQuantity(int quantity) {
		eleUtil.doSendKeys(productQuantity, Integer.toString(quantity));
	} 
	
	public void doClickOnAddToCartButton() {
		eleUtil.doClick(addToCartButton);
	}
	
	private void getProductMetaData(Map<String,String> productData) {
		List<String> productMetaData = eleUtil.doGetElementsText(prodMetaData);
		for(String text : productMetaData) {
			String[] textFields = text.split(":");
			String key = String.join("",textFields[0].trim().split(" "));
			String value = textFields[1].trim();
			productData.put(key, value);
		}		
	}
	
	private void getProductPriceData(Map<String,String> productData) {
		List<String> productPriceData = eleUtil.doGetElementsText(prodPriceData);
		productData.put("prodPrice", productPriceData.get(0));
		String[] taxPriceData = productPriceData.get(1).split(": ");
		productData.put("exTax", taxPriceData[1].trim());
	}
	
	public Map<String, String> getProductData() {
		Map<String, String> productData = new HashMap<String, String>(); //No Order
//		Map<String, String> productData = new LinkedHashMap<String, String>(); // Maintains order the elements added to the map
//		Map<String, String> productData = new TreeMap<String, String>();		// Maintains the elements in alphabetical order
		getProductMetaData(productData);
		getProductPriceData(productData);
		System.out.println("The Product Data =======> " + productData);
		return productData;
	}
	

}
