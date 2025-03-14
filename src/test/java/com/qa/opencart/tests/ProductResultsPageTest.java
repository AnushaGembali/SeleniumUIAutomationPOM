package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class ProductResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		String pwd = System.getProperty("password")== null ? System.getProperty("password") : System.getProperty("password");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), pwd);
	}
	
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyProdResultsTitle(String searchProductName, String childProdName) {
		productResultsPage = accountsPage.doSearch(searchProductName);
		String actualTitle = productResultsPage.getSearchPageTitle();
		String expectedTitle = AppConstants.SEARCH_PAGE_TITLE_AND_HEADER_FRACTION + " - " + productResultsPage.getSearchValue();
		Assert.assertEquals(actualTitle, expectedTitle);	
	}
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyProdResultsURL(String searchProductName, String childProdName) {
		productResultsPage = accountsPage.doSearch(searchProductName);
		String actualURL = productResultsPage.getSearchPageURL();
		String expectedURL = AppConstants.SEARCH_PAGE_URL_FRACTION + productResultsPage.getSearchValue();
		Assert.assertTrue(actualURL.contains(expectedURL));
	}
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyProdResultsPageHeader(String searchProductName, String childProdName) {
		productResultsPage = accountsPage.doSearch(searchProductName);
		String actualHeader = productResultsPage.getSearchHeader();
		String expectedHeader = AppConstants.SEARCH_PAGE_TITLE_AND_HEADER_FRACTION + " - " + productResultsPage.getSearchValue();
		Assert.assertEquals(actualHeader, expectedHeader);
	}
	
	@Test(dataProvider = "getDataSearchProdCount", dataProviderClass = ProductDataProvider.class)
	public void verifyProdResultsCount(String searchProductName, int prodResultCount) {
		productResultsPage = accountsPage.doSearch(searchProductName);
		int actualProdResultsCount = productResultsPage.getSearchResultCount();
		Assert.assertEquals(actualProdResultsCount, prodResultCount);
	}

	
	@Test(dataProvider = "dataForSelectingProd" , dataProviderClass = ProductDataProvider.class)
	public void verifySelectProduct(String parentProd, String childProd) {
		    productResultsPage = accountsPage.doSearch(parentProd);		
			productPage = productResultsPage.selectProduct(childProd);
			String actualTitle = productPage.getProductPageTitle().toLowerCase();
			String expectedTitle = productPage.getProductName().toLowerCase();
			Assert.assertEquals(actualTitle, expectedTitle);
	}

}
