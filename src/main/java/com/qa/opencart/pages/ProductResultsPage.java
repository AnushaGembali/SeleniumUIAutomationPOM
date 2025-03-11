package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.BrowserUtil;
import com.qa.opencart.utils.ElementUtil;

public class ProductResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	private BrowserUtil brUtil;
	
	private String searchValue;
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public ProductResultsPage(WebDriver driver, String searchValue) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		brUtil = new BrowserUtil(driver);
		this.searchValue = searchValue;
	}
	
	private By searchHeaderLoc = By.tagName("h1");
	private By searchCriteriaHeader = By.cssSelector("div#content h2");
	private By searchResults = By.cssSelector("div.product-thumb");
	
	public String getSearchPageTitle() {
		String actualTitle = brUtil.doGetPageTitleContains(AppConstants.SEARCH_PAGE_TITLE_AND_HEADER_FRACTION,AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		return actualTitle;
	}
	
	public String getSearchPageURL() {
		String actualURL = brUtil.doGetPageURLContains(AppConstants.SEARCH_PAGE_URL_FRACTION,AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		return actualURL;
	}
	
	public String getSearchHeader() {
		String searchHeader = eleUtil.doGetElementText(searchHeaderLoc, AppConstants.DEFAULT_MEDIUM_TIMEOUT);
		return searchHeader;		
	}
	
	public int getSearchResultCount() {
		int count = eleUtil.doGetElementsListCount(searchResults);
		return count;
	}
	
	public ProductPage selectProduct(String productName) {
		
		try {
			eleUtil.doClick(By.linkText(productName), AppConstants.DEFAULT_MEDIUM_TIMEOUT);
			return new ProductPage(driver, productName);
		}
		catch(NoSuchElementException e) {
			return null;
		}	
	}
}
