package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageTest extends BaseTest{
	
	@BeforeClass
	public void doLogin() {
		String pwd = System.getProperty("password")== null ? prop.getProperty("password") : System.getProperty("password");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), pwd);
	}
	
	private void navigateToProductPage(String searchProductName, String childProdName) {
		productResultsPage = accountsPage.doSearch(searchProductName);
		productPage = productResultsPage.selectProduct(childProdName);
	}

	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyProductPageTitle(String searchProductName, String childProdName) {
		navigateToProductPage(searchProductName, childProdName);
		String actualTitle = productPage.getProductPageTitle().toLowerCase();
		String expectedTitle = productPage.getProductName().toLowerCase();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyProductPageHeader(String searchProductName, String childProdName) {
		navigateToProductPage(searchProductName, childProdName);
		String actualHeader = productPage.getProductPageHeader().toLowerCase();
		String expectedHeader = productPage.getProductName().toLowerCase();
		Assert.assertEquals(actualHeader, expectedHeader);
	}
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyQtyFieldEnabled(String searchProductName, String childProdName) {
		navigateToProductPage(searchProductName, childProdName);
		boolean isQtyTextFieldEnabled= productPage.isQtyTextFieldEnabled();
		Assert.assertTrue(isQtyTextFieldEnabled);
	}
	
	@Test(dataProvider = "getSearchProductname", dataProviderClass = ProductDataProvider.class)
	public void verifyCartToBtnEnabled(String searchProductName, String childProdName) {
		navigateToProductPage(searchProductName, childProdName);
		boolean isCartToBtnEnabled= productPage.isAddToCartBtnEnabled();
		Assert.assertTrue(isCartToBtnEnabled);
	}
	
	@Test
	public void addProductToCartTest() {
		navigateToProductPage("mac", "MacBook Pro");
		productPage.addQuantity(5);
		productPage.doClickOnAddToCartButton();
	}
	
	@DataProvider
	public Object[][] getProductData(){
		return new Object[][] {
			{"mac", "MacBook Pro", "Apple", "Product 18", "800", "In Stock", "$2,000.00", "$2,000.00"}
		};
	}
	
	@Test(dataProvider = "getProductData")
	public void verifyProductDataTest(String parentProductName, String subProductName, String Brand, String ProductCode, String RewardPoints, String Availability, String prodPrice, String exTax) {
		navigateToProductPage(parentProductName, subProductName);
		Map<String, String> productData = productPage.getProductData();
		softAssert.assertEquals(productData.get("Brand"), Brand);
		softAssert.assertEquals(productData.get("ProductCode"), ProductCode);
		softAssert.assertEquals(productData.get("RewardPoints"), RewardPoints);
		softAssert.assertEquals(productData.get("Availability"), Availability);
		softAssert.assertEquals(productData.get("prodPrice"), prodPrice);
		softAssert.assertEquals(productData.get("exTax"), exTax);
		softAssert.assertAll();
	}
}
