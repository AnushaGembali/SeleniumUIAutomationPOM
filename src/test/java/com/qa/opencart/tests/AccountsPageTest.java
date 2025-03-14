package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest{
	
	@BeforeClass
	public void doAccountsPageSetup() {
		String pwd = System.getProperty("password") == null ? prop.getProperty("password") : System.getProperty("password");
		accountsPage = loginPage.doLogin(prop.getProperty("username"), pwd);
	}

	
	@Test
	public void verifyAccountsPageTitle() {
		String actualTitle = accountsPage.getAccountsPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void verifyAccountsPageURl() {
		String actualURL = accountsPage.getAccountsPageURL();
		Assert.assertTrue(actualURL.contains(AppConstants.ACCOUNT_PAGE_FRACTION_URL));
	}
	
	@Test
	public void isLogoutLinkExist() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}
	
	@Test
	public void accountsPageHeadersCountTest() {
		List<String> actualAccountsPageHeaders = accountsPage.getAccountsPageHeaders();
		Assert.assertEquals(actualAccountsPageHeaders.size(), AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_COUNT);
	}
	
	@Test
	public void accountsPageHeadersTest() {
		List<String> actualAccountsPageHeaders = accountsPage.getAccountsPageHeaders();
		Assert.assertEquals(actualAccountsPageHeaders, AppConstants.EXPECTED_ACCOUNTS_PAGE_HEADERS_LIST);
	}
	
	@Test(priority = Integer.MAX_VALUE)
	public void doSearch() {
		productResultsPage = accountsPage.doSearch("mac");
		String actualTitle = productResultsPage.getSearchPageTitle();
		String expectedTitle = AppConstants.SEARCH_PAGE_TITLE_AND_HEADER_FRACTION + " - " + productResultsPage.getSearchValue();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
}
